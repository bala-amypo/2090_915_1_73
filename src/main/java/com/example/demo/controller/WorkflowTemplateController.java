package com.example.demo.controller;

import com.example.demo.model.WorkflowTemplate;
import com.example.demo.service.WorkflowTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/templates")
@Tag(name = "Workflow Templates", description = "Workflow template management")
public class WorkflowTemplateController {
    
    private final WorkflowTemplateService workflowTemplateService;
    
    @Autowired
    public WorkflowTemplateController(WorkflowTemplateService workflowTemplateService) {
        this.workflowTemplateService = workflowTemplateService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new workflow template")
    public ResponseEntity<WorkflowTemplate> createTemplate(@RequestBody WorkflowTemplate template) {
        WorkflowTemplate created = workflowTemplateService.createTemplate(template);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get workflow template by ID")
    public ResponseEntity<WorkflowTemplate> getTemplate(@PathVariable Long id) {
        return workflowTemplateService.getTemplateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update workflow template")
    public ResponseEntity<WorkflowTemplate> updateTemplate(@PathVariable Long id, @RequestBody WorkflowTemplate template) {
        WorkflowTemplate updated = workflowTemplateService.updateTemplate(id, template);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping
    @Operation(summary = "Get all workflow templates")
    public ResponseEntity<List<WorkflowTemplate>> getAllTemplates() {
        List<WorkflowTemplate> templates = workflowTemplateService.getAllTemplates();
        return ResponseEntity.ok(templates);
    }
}