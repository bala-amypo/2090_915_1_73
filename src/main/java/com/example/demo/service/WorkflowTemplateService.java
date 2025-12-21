package com.example.demo.service;

import com.example.demo.model.WorkflowTemplate;
import com.example.demo.repository.WorkflowTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkflowTemplateService {

    @Autowired
    private WorkflowTemplateRepository templateRepository;

    public WorkflowTemplate createTemplate(WorkflowTemplate template) {
        if (template.getActive() == null) {
            template.setActive(true);
        }
        return templateRepository.save(template);
    }

    public Optional<WorkflowTemplate> getTemplateById(Long id) {
        return templateRepository.findById(id);
    }

    public WorkflowTemplate updateTemplate(Long id, WorkflowTemplate updated) {
        WorkflowTemplate existing =
                templateRepository.findById(id).orElseThrow();

        existing.setTemplateName(updated.getTemplateName());
        existing.setDescription(updated.getDescription());
        existing.setTotalLevels(updated.getTotalLevels());

        return templateRepository.save(existing);
    }

    public WorkflowTemplate activateTemplate(Long id, boolean active) {
        WorkflowTemplate template =
                templateRepository.findById(id).orElseThrow();
        template.setActive(active);
        return templateRepository.save(template);
    }

    public List<WorkflowTemplate> getAllTemplates() {
        return templateRepository.findAll();
    }
}
