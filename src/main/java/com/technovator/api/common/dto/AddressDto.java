package com.technovator.api.common.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.technovator.api.common.constants.Views;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Addresses
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Address")
@JsonView(value = Views.Allways.class)
public class AddressDto {
  @JsonProperty("area")
  private String area = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("countryId")
  private String countryId = null;

  @JsonProperty("createDate")
  private OffsetDateTime createDate = null;

  @JsonProperty("createUser")
  private String createUser = null;

  @JsonProperty("houseNo")
  private String houseNo = null;

  @JsonProperty("isDefault")
  private Boolean isDefault = null;

  @JsonProperty("isDisabled")
  private Boolean isDisabled = null;

  @JsonProperty("latitude")
  private BigDecimal latitude = null;

  @JsonProperty("longitude")
  private BigDecimal longitude = null;

  @JsonProperty("objectId")
  private String objectId = null;

  @JsonProperty("state")
  private String state = null;

  @JsonProperty("street")
  private String street = null;

  @JsonProperty("zipCode")
  private String zipCode = null;
  
}
