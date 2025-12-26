package com.example.demo.service.impl;

import com.example.demo.model.AuditLogRecord;
import com.example.demo.repository.AuditLogRecordRepository;
import com.example.demo.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    
    private final AuditLogRecordRepository auditLogRecordRepository;
    
    @Autowired
    public AuditLogServiceImpl(AuditLogRecordRepository auditLogRecordRepository) {
        this.auditLogRecordRepository = auditLogRecordRepository;
    }
    
    @Override
    public AuditLogRecord createAuditLog(AuditLogRecord auditLog) {
        if (auditLog.getLoggedAt() == null) {
            auditLog.setLoggedAt(LocalDateTime.now());
        }
        return auditLogRecordRepository.save(auditLog);
    }
}