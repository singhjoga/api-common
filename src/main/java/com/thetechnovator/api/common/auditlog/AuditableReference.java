package com.thetechnovator.api.common.auditlog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thetechnovator.api.common.domain.AppObect;

public interface AuditableReference<PARENT_ID, REF_ID> extends AppObect{
	@JsonIgnore
	PARENT_ID getParentId();
	@JsonIgnore
	Class<? extends AuditableParent> getParentEntity();
	
	@JsonIgnore
	REF_ID getReferenceId();
	@JsonIgnore
	Class<? extends AuditableParent> getReferenceEntity();
}
