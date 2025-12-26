package com.example.demo.service.impl;

import com.example.demo.model.ApprovalAction;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;
import com.example.demo.service.ApprovalActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ApprovalActionServiceImpl implements ApprovalActionService {
    
    private final ApprovalActionRepository approvalActionRepository;
    private final ApprovalRequestRepository approvalRequestRepository;
    
    @Autowired
    public ApprovalActionServiceImpl(ApprovalActionRepository approvalActionRepository,
                                   ApprovalRequestRepository approvalRequestRepository) {
        this.approvalActionRepository = approvalActionRepository;
        this.approvalRequestRepository = approvalRequestRepository;
    }
    
    @Override
    public ApprovalAction recordAction(ApprovalAction action) {
        if (action.getActionDate() == null) {
            action.setActionDate(LocalDateTime.now());
        }
        return approvalActionRepository.save(action);
    }
}