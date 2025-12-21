package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "approval_actions")
public class ApprovalAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int level;

    private String action;

    private String comment;

    @ManyToOne
    private ApprovalRequest approvalRequest;

    public ApprovalAction() {
    }

    public Long getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ApprovalRequest getApprovalRequest() {
        return approvalRequest;
    }

    public void setApprovalRequest(ApprovalRequest approvalRequest) {
        this.approvalRequest = approvalRequest;
    }
}
