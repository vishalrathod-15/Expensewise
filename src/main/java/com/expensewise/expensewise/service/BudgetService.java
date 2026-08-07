package com.expensewise.expensewise.service;

import com.expensewise.expensewise.dto.BudgetReportDTO;
import com.expensewise.expensewise.entity.Budget;
import com.expensewise.expensewise.entity.User;
import com.expensewise.expensewise.repository.BudgetRepository;
import com.expensewise.expensewise.repository.TransactionRepository;
import com.expensewise.expensewise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository,
                         TransactionRepository transactionRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public Budget createBudget(Long userId, Budget budget) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        budget.setUser(user);
        return budgetRepository.save(budget);
    }

    public List<Budget> getBudgetsByUser(Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    public List<BudgetReportDTO> getBudgetReport(Long userId) {
        List<Budget> budgets = budgetRepository.findByUserId(userId);

        return budgets.stream().map(budget -> {
            BigDecimal spent = transactionRepository.getTotalSpentByCategory(userId, budget.getCategory());
            BigDecimal remaining = budget.getMonthlyLimit().subtract(spent);
            boolean overBudget = spent.compareTo(budget.getMonthlyLimit()) > 0;

            return new BudgetReportDTO(
                    budget.getCategory(),
                    budget.getMonthlyLimit(),
                    spent,
                    remaining,
                    overBudget
            );
        }).collect(Collectors.toList());
    }
}