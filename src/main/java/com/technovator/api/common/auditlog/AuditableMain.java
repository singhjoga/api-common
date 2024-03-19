package com.technovator.api.common.auditlog;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AuditableMain extends Auditable{
	@JsonIgnore
	String getName();
}
