package com.technovator.api.common.auditlog;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuditLogRepository extends CrudRepository<AuditLog, String>{
	List<AuditLog> findByObjectTypeAndObjectIdOrderByDateDesc(String resource, String resourceId);
}
