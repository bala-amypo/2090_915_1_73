package com.example.demo.service;

import com.example.demo.model.ApprovalRequest;
import com.example.demo.repository.ApprovalRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalRequestService {

    private final ApprovalRequestRepository repository;

    public ApprovalRequestService(ApprovalRequestRepository repository) {
        this.repository = repository;
    }

    // Create or Update Approval Request
    public ApprovalRequest save(ApprovalRequest approvalRequest) {
        return repository.save(approvalRequest);
    }

    // ✅ FIXED: Get Approval Request by ID
    public ApprovalRequest getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Get all approval requests
    public List<ApprovalRequest> getAll() {
        return repository.findAll();
    }

    // Delete approval request
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
