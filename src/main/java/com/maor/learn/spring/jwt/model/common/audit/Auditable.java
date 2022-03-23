package com.maor.learn.spring.jwt.model.common.audit;

public interface Auditable {

    AuditSection getAuditSection();

    void setAuditSection(AuditSection audit);
}
