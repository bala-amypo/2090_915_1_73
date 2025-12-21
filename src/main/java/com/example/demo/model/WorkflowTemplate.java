package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "workflow_templates")
public class WorkflowTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean active;

    @OneToMany(mappedBy = "workflowTemplate", cascade = CascadeType.ALL)
    private List<WorkflowStepConfig> steps;

    public WorkflowTemplate() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<WorkflowStepConfig> getSteps() {
        return steps;
    }

    public void setSteps(List<WorkflowStepConfig> steps) {
        this.steps = steps;
    }
}
