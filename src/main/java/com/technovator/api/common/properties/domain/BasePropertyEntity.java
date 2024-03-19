package com.technovator.api.common.properties.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.technovator.api.common.constants.OperationGroups;
import com.technovator.api.common.constants.Views;
import com.technovator.api.common.domain.AbstractResource;

import io.swagger.v3.oas.annotations.media.Schema;

@MappedSuperclass
public abstract class BasePropertyEntity<T extends BasePropertyEntity<T>> extends AbstractResource{
	@Column(name="NAME")
	@Size(min = 1, max = 500)
	@NotNull(groups=OperationGroups.Add.class)
	@Schema(description = "Property name", required=true)
	@JsonView(value= {Views.Allways.class})
	private String name;
	
	@Size(max = 1000)
	@Schema(description = "Long description")
	@JsonView(value= {Views.Allways.class})
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="TYPE_CODE")
	@Size(min = 1, max = 20)
	@NotNull(groups=OperationGroups.Add.class)
	@Schema(description = "Type of property e.g. STR, BOOL, NUM, UID, PWD etc.", required=true, example="STR")
	@JsonView(value= {Views.Allways.class})	
	private String typeCode; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public abstract T create();
	public T copy() {
		T copy = create();
		copyTo(copy);
		return copy;
	}
		
	public T copyTo(T copyTo) {
		copyTo.setDescription(description);
		copyTo.setName(name);
		copyTo.setTypeCode(typeCode);
		
		return copyTo;
	}
}