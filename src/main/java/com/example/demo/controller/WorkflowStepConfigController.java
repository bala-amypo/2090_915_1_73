package com.example.demo.controller;

import com.example.demo.model.WorkflowStepConfig;
import com.example.demo.service.WorkflowStepConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/steps")
@Tag(name = "Workflow Steps", description = "Workflow step configuration management")
public class WorkflowStepConfigController {
    
    private final WorkflowStepConfigService workflowStepConfigService;
    
    @Autowired
    public WorkflowStepConfigController(WorkflowStepConfigService workflowStepConfigService) {
        this.workflowStepConfigService = workflowStepConfigService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new workflow step")
    public ResponseEntity<WorkflowStepConfig> createStep(@RequestBody WorkflowStepConfig step) {
        WorkflowStepConfig created = workflowStepConfigService.createStep(step);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/template/{templateId}")
    @Operation(summary = "Get workflow steps for template")
    public ResponseEntity<List<WorkflowStepConfig>> getStepsForTemplate(@PathVariable Long templateId) {
        List<WorkflowStepConfig> steps = workflowStepConfigService.getStepsForTemplate(templateId);
        return ResponseEntity.ok(steps);
    }
}