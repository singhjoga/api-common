package com.technovator.api.common.auditlog;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technovator.api.common.domain.AbstractResource;
import com.technovator.api.common.services.BaseService;
import com.technovator.api.common.utils.CommonUtil;

@Service
public class AuditLogService extends BaseService{
	@Autowired
	private AuditLogRepository repo;
	public String add(String action, AuditableMain auditable, Object id, String details) {
		return add(action,auditable, id, details, null);
	}
	public String add(String action, AuditableMain auditable, Object id, String details, String filter) {
		if (StringUtils.isEmpty(details)) {
			return null;
		}
		AuditLog obj = new AuditLog();
		obj.setId(CommonUtil.genUUID());
		obj.setAction(action);
		obj.setDate(new Date());
		obj.setDetails(details);
		obj.setObjectType(auditable.getClass().getSimpleName());
		obj.setObjectId(id.toString());
		obj.setObjectName(auditable.getName());
		obj.setUser(getLoggedUser());
		obj.setFilterValue(filter);
		repo.save(obj);
		
		return obj.getId();
	}
	
	public List<AuditLog> find(String entity, String entityId) {
		return repo.findByObjectTypeAndObjectIdOrderByDateDesc(entity, entityId);
	}
	public List<AuditLog> find(AuditableMain auditable, Object id) {
		return find(auditable,id, null);
	}
	public List<AuditLog> find(AuditableMain auditable, Object id, List<AuditLog> childAuditables) {
		List<AuditLog> history = repo.findByObjectTypeAndObjectIdOrderByDateDesc(auditable.getClass().getSimpleName(), id.toString());
	/*	addToResult(history, auditable);
		if (childAuditables != null && !childAuditables.isEmpty()) {
			Comparator<ChangeHistory> comp = Comparator.comparing(ChangeHistory::getDate).reversed();
			history.addAll(childAuditables);
			history.sort(comp);
		}
		*/
		return history; 
	}
	
	private void addToResult(List<AuditLog> history, AuditableMain auditable, Object id) {
		//add the added record, which is not part of the history to preserve space
		AuditLog add = new AuditLog();
		if (auditable instanceof AbstractResource) { //this the case always
			AbstractResource common = (AbstractResource) auditable;
			if (common.getCreateDate()==null) {
				return;
			}
			add.setDate(common.getCreateDate());
			add.setUser(common.getCreateUser());
		}
		add.setAction("Add");
		add.setDetails("Added");
		add.setObjectId(id.toString());
		add.setObjectName(auditable.getName());
		add.setObjectType(auditable.getClass().getSimpleName());
		history.add(add);
	}
}
