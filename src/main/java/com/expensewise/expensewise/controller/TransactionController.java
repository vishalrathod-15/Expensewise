package com.expensewise.expensewise.controller;

import com.expensewise.expensewise.entity.Transaction;
import com.expensewise.expensewise.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/user/{userId}")
    public Transaction createTransaction(@PathVariable Long userId, @RequestBody Transaction transaction) {
        return transactionService.createTransaction(userId, transaction);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactions(@PathVariable Long userId) {
        return transactionService.getTransactionsByUser(userId);
    }
}