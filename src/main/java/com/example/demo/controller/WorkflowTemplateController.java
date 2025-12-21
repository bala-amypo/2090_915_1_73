package com.example.demo.controller;

import com.example.demo.model.WorkflowTemplate;
import com.example.demo.service.WorkflowTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflow-templates")
public class WorkflowTemplateController {

    @Autowired
    private WorkflowTemplateService workflowTemplateService;

    @PostMapping
    public WorkflowTemplate create(@RequestBody WorkflowTemplate template) {
        return workflowTemplateService.create(template);
    }

    @GetMapping("/{id}")
    public WorkflowTemplate getById(@PathVariable Long id) {
        return workflowTemplateService.getById(id);
    }

    @GetMapping
    public List<WorkflowTemplate> getAll() {
        return workflowTemplateService.getAll();
    }

    @PutMapping("/{id}/active")
    public WorkflowTemplate activate(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return workflowTemplateService.setActive(id, active);
    }
}
