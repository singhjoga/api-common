package com.thetechnovator.api.common.controllers;

import java.io.Serializable;

import com.thetechnovator.api.common.domain.IdentifiableEntity;

public interface CrudWithHistoryController<T extends IdentifiableEntity<ID>, ID extends Serializable> extends CrudController<T, ID>,History<T, ID> {

}
