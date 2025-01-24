package com.expenses.service;

import java.util.List;
import java.util.Optional;

import com.expenses.entity.Expenses;
import com.expenses.model.Dto;
import com.expenses.model.ResponseExpense;

public interface ExpenseService {
	 public List<Expenses> getAllExpensesByUser();
	 public Optional<Expenses> getExpenseById(Long expenseId);
	  public Expenses createExpense(Expenses expense);
	  public Expenses updateExpense(Long expenseId, Expenses expenseDetails);
	  public void deleteExpense(Long expenseId);
	  public Dto getAllExpensesWithUser(Long userId,Long expenseId);
}
