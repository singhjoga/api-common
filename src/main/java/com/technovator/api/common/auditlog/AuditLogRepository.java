package com.technovator.api.common.auditlog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, String>{
	List<AuditLog> findByObjectTypeAndObjectIdOrderByDateDesc(String resource, String resourceId);
}
