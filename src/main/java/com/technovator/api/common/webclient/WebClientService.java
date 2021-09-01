package com.technovator.api.common.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Log4j2
@Component
public class WebClientService {
	private WebClient http;

	@Autowired
	public WebClientService(WebClient http) {
		super();
		this.http = http;
	}

	public <T> Mono<T> get(String url, Class<T> returnType) {
		return http.get().uri(url).retrieve().onStatus(HttpStatus::isError, response -> {
			// logTraceResponse(response);
			return Mono.error(getException(response));

		}).bodyToMono(returnType);
	}
	public <T> T getSync(String url, Class<T> returnType) {
		return getSyncRetry(url, returnType, 0);
	}
	public <T> T getSyncTillSuccess(String url, Class<T> returnType) {
		return getSyncRetry(url, returnType, -1);
	}
	public <T> T getSyncRetry(String url, Class<T> returnType, int retries) {
		int tries = 0;
		while (true) {
			tries++;
			ServerErrorException lastException;
			try {
				T result = get(url, returnType).block();
				return result;
			}catch (ServerErrorException e) {
				log.warn(String.format("Service '%s' unavailable or has errors. %s", url, e.getMessage()));
				lastException=e;
			}catch (Exception e) {
				log.warn(String.format("Service '%s' unavailable or has errors. %s", url, e.getMessage()));
				throw new IllegalStateException(e);
			}
			if (retries >= 0 && tries >= retries) {
				throw lastException;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.warn("Get thread interrupted. Existing.");
			}
		}
	}
	private Exception getException(ClientResponse response) {
		if (response.statusCode().is5xxServerError()) {
			return new ServerErrorException(
					response.statusCode().value() + " " + response.bodyToMono(String.class).block());
		} else {
			return new ClientErrorException(
					response.statusCode().value() + " " + response.bodyToMono(String.class).block());
		}
	}

	public static void logTraceResponse(ClientResponse response) {

            log.info("Response status: {}", response.statusCode());
            log.info("Response headers: {}", response.headers().asHttpHeaders());
            response.bodyToMono(String.class)
                    .publishOn(Schedulers.boundedElastic())
                    .subscribe(body -> log.info("Response body: {}", body));

    }
}
