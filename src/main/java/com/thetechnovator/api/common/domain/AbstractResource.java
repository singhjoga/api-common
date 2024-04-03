package com.thetechnovator.api.common.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.thetechnovator.api.common.annotations.AutoGeneratedUUID;
import com.thetechnovator.api.common.annotations.LifecycleStatus;
import com.thetechnovator.api.common.constants.Views;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@MappedSuperclass
public abstract class AbstractResource<ID extends Serializable> implements IdentifiableEntity<ID> {

	@Id
	@Column(name="ID")
	@Schema(description = "Internal ID", required=true)
	@JsonView(value = {Views.List.class})
	@AutoGeneratedUUID
	private ID id;
	
	@Column(name="CREATE_DATE")
	@JsonView(value=Views.List.class)
	@Schema(description = "Date when this record was created", accessMode = AccessMode.READ_ONLY)
	private Date createDate;
	
	@Column(name="CREATE_USER")
	@JsonView(value=Views.List.class)
	@Size(min = 1, max = 50)
	@Schema(description = "User who created this record.", accessMode = AccessMode.READ_ONLY)
	private String createUser;

	@Column(name="IS_DISABLED")
	@Schema(description = "'true' if the entry is disabled i.e. not in use",example = "false")
	@JsonView(value= {Views.Update.class,Views.List.class})
	@LifecycleStatus	
	private Boolean isDisabled;	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public Boolean getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

}
