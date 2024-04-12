package com.thetechnovator.api.common.controllers;

import java.io.Serializable;

import com.thetechnovator.api.common.domain.IdentifiableEntity;


public interface CrudControllerChild<T extends IdentifiableEntity<ID>, ID extends Serializable, PARENT_ID extends Serializable> extends Add<T, ID>,Update<T, ID>,Delete<T, ID>, Read<T, ID>,ReadAllChild<T, ID, PARENT_ID> {

}