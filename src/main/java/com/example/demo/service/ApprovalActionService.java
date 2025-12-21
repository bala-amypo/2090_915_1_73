package com.example.demo.service;

import com.example.demo.model.ApprovalAction;
import com.example.demo.repository.ApprovalActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalActionService {

    @Autowired
    private ApprovalActionRepository approvalActionRepository;

    public ApprovalAction save(ApprovalAction action) {
        return approvalActionRepository.save(action);
    }

    public List<ApprovalAction> findByLevelAndAction(int level, String action) {
        return approvalActionRepository.findByLevelAndAction(level, action);
    }

    public List<ApprovalAction> findByApprover(Long approverId) {
        return approvalActionRepository.findByApproverId(approverId);
    }
}
