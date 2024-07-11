package com.thetechnovator.api.common.validation;

import com.thetechnovator.api.common.annotations.EntityReference;
import com.thetechnovator.api.common.domain.IdentifiableEntity;

public class EntityReferenceValidator extends BaseEntityReferenceValidator<EntityReference>{

	@Override
	protected Class<? extends IdentifiableEntity<?>> getValue(EntityReference annotation) {
		return annotation.value();
	}

}
