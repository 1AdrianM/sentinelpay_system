package com.github.sentinel.pay.domain.repository;

import com.github.sentinel.pay.domain.entity.audit.AuditLog;

public interface AuditAppenderRepository {
    AuditLog append(AuditLog auditLog);
}
