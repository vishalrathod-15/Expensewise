package com.expensewise.expensewise.service;

import com.expensewise.expensewise.entity.Transaction;
import com.expensewise.expensewise.entity.User;
import com.expensewise.expensewise.repository.TransactionRepository;
import com.expensewise.expensewise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(Long userId, Transaction transaction) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
}