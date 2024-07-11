package com.thetechnovator.api.common.auditlog;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AuditableParent extends Auditable{
	@JsonIgnore
	String getName();
}
