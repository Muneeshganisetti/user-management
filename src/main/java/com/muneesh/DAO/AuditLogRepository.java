package com.muneesh.DAO;

import com.muneesh.Entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository <AuditLog, Long> {
    // Additional query methods can be defined here if needed
}
