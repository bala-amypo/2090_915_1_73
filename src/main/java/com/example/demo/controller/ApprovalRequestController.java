package com.example.demo.controller;

import com.example.demo.model.ApprovalRequest;
import com.example.demo.service.ApprovalRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
@Tag(name = "Approval Requests", description = "Approval request management")
public class ApprovalRequestController {
    
    private final ApprovalRequestService approvalRequestService;
    
    @Autowired
    public ApprovalRequestController(ApprovalRequestService approvalRequestService) {
        this.approvalRequestService = approvalRequestService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new approval request")
    public ResponseEntity<ApprovalRequest> createRequest(@RequestBody ApprovalRequest request) {
        ApprovalRequest created = approvalRequestService.createRequest(request);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    @Operation(summary = "Get all approval requests")
    public ResponseEntity<List<ApprovalRequest>> getAllRequests(@RequestParam(required = false) Long requesterId) {
        if (requesterId != null) {
            List<ApprovalRequest> requests = approvalRequestService.getRequestsByRequester(requesterId);
            return ResponseEntity.ok(requests);
        } else {
            List<ApprovalRequest> requests = approvalRequestService.getAllRequests();
            return ResponseEntity.ok(requests);
        }
    }
}