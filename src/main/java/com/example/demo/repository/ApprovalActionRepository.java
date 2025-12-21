package com.example.demo.repository;

import com.example.demo.model.ApprovalAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovalActionRepository extends JpaRepository<ApprovalAction, Long> {

    List<ApprovalAction> findByLevelNumberAndAction(int levelNumber, String action);

    // Alias used by tests
    default List<ApprovalAction> findByLevelAndAction(int level, String action) {
        return findByLevelNumberAndAction(level, action);
    }

    @Query("select a from ApprovalAction a where a.approverId = ?1")
    List<ApprovalAction> findByApproverId(Long approverId);
}
