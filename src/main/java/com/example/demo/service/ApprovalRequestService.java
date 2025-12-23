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

    public ApprovalRequest createRequest(ApprovalRequest request) {
        return repository.save(request);
    }

    public ApprovalRequest getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<ApprovalRequest> getAllRequests() {
        return repository.findAll();
    }
}
