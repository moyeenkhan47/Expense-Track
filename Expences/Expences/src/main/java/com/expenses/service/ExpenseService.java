package com.expenses.service;

import java.util.List;
import java.util.Optional;

import com.expenses.entity.Expenses;

public interface ExpenseService {
	 public List<Expenses> getAllExpensesByUser(String username);
	 public Optional<Expenses> getExpenseById(Long id);
	  public Expenses createExpense(Expenses expense);
	  public Expenses updateExpense(Long id, Expenses expenseDetails);
	  public void deleteExpense(Long id);
}
