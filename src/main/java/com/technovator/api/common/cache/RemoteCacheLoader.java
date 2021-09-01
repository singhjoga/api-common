package com.technovator.api.common.cache;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.technovator.api.common.config.SystemConfiguration;
import com.technovator.api.common.dto.Language;
import com.technovator.api.common.webclient.WebClientService;
import com.thetechnovator.common.java.utils.StringMap;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class RemoteCacheLoader implements SystemCache {
	private boolean loading = false;
	@Autowired
	private WebClientService webClient;

	private Set<String> languages;
	private SystemConfiguration systemConfig;
	
	@Override
	public Set<String> getLanguages() {
		return languages;
	}

	@Override
	public String getDefaultLanguageId() {
		return systemConfig.defaultLanguage();
	}

	@Override
	@Async
	public void load() {
		loading=true;
		log.info("Loading remote cache");
		log.info("Loading languages");
		Language[] list = webClient.getSyncTillSuccess("http://system/v1/languages", Language[].class);
		Set<String> langSet = new HashSet<>();
		for (Language lang : list) {
			langSet.add(lang.getId());
		}
		this.languages = langSet;

		log.info("Loading system properties");
		StringMap props = webClient.getSyncTillSuccess("http://system/v1/properties", StringMap.class);
		systemConfig = new SystemConfiguration(props);
		
		log.info("Remote cache loaded");

		loading = false;
	}

}
