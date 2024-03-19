package com.technovator.api.common.refdata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.technovator.api.common.constants.OperationGroups;
import com.technovator.api.common.constants.RegEx;
import com.technovator.api.common.constants.Views;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity(name="REF_DATA_VAL")
@Schema(name="RefDataValue", description = "System reference data values")
@IdClass(value = RefDataValueId.class)
public class RefDataValue{

	@Id
	@Column(name="CODE")
	@Size(min = 3, max = 20, groups=OperationGroups.Always.class)
	@Schema(description = "Reference Code. Use as a Key other places for the references", required=true)
	@Pattern(regexp = RegEx.NAME,groups=OperationGroups.Always.class)
	@NotNull(groups=OperationGroups.Add.class)
	@JsonView(value = {Views.List.class, Views.Add.class})
	private String code;

	@Column(name="LANG_CODE")
	@Id
	@Size(min = 1, max = 50, groups=OperationGroups.Always.class)
	@Schema(description = "Reference type code. One of the 'UserRefData' type code in System reference data", required=true)
	@NotNull(groups=OperationGroups.Add.class)
	@JsonView(value= {Views.Allways.class})
	private String languageCode;

	@Column(name="VALUE")
	@Size(min = 1, max = 50, groups=OperationGroups.Always.class)
	@Schema(description = "Reference type value i.e short discription", required=true)
	@NotNull(groups=OperationGroups.Add.class)
	@JsonView(value= {Views.Allways.class})
	private String value;
	
	@Column(name="DESCRIPTION")
	@Size(min = 1, max = 50, groups=OperationGroups.Always.class)
	@Schema(description = "Long description", required=true)
	@NotNull(groups=OperationGroups.Add.class)
	@JsonView(value= {Views.Allways.class})
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
