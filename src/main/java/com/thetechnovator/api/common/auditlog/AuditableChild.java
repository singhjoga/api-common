package com.thetechnovator.api.common.auditlog;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AuditableChild<PARENT_ID> extends Auditable{
	@JsonIgnore
	String getName();
	@JsonIgnore
	PARENT_ID getParentId();
	@JsonIgnore
	Class<? extends AuditableParent> getParentEntity();
}
