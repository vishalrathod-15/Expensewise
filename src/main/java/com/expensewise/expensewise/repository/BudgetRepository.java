package com.expensewise.expensewise.repository;

import com.expensewise.expensewise.entity.Budget;
import com.expensewise.expensewise.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(Long userId);
    Optional<Budget> findByUserIdAndCategory(Long userId, Category category);
}