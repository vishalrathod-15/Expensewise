package com.expensewise.expensewise.controller;

import com.expensewise.expensewise.dto.BudgetReportDTO;
import com.expensewise.expensewise.entity.Budget;
import com.expensewise.expensewise.service.BudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/user/{userId}")
    public Budget createBudget(@PathVariable Long userId, @RequestBody Budget budget) {
        return budgetService.createBudget(userId, budget);
    }

    @GetMapping("/user/{userId}")
    public List<Budget> getBudgets(@PathVariable Long userId) {
        return budgetService.getBudgetsByUser(userId);
    }
    @GetMapping("/user/{userId}/report")
    public List<BudgetReportDTO> getBudgetReport(@PathVariable Long userId) {
        return budgetService.getBudgetReport(userId);
    }
}