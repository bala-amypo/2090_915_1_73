package com.example.demo.controller;

import com.example.demo.model.WorkflowConfig;
import com.example.demo.service.WorkflowConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflow-config")
public class WorkflowConfigController {

    @Autowired
    private WorkflowConfigService workflowConfigService;

    @PostMapping
    public WorkflowConfig create(@RequestBody WorkflowConfig config) {
        return workflowConfigService.save(config);
    }

    @GetMapping("/template/{templateId}")
    public List<WorkflowConfig> getByTemplate(@PathVariable Long templateId) {
        return workflowConfigService.getByTemplateId(templateId);
    }
}
