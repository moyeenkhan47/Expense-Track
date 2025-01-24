package com.expenses.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.entity.Expenses;
import com.expenses.model.Dto;
import com.expenses.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

	public ExpenseController(ExpenseService expenseService) {
		super();
		this.expenseService = expenseService;
	}

	private final ExpenseService expenseService;

	// Get all expenses for a specific user
	@GetMapping("/all")
	public ResponseEntity<List<Expenses>> getAllExpenses() {
		List<Expenses> expenses = expenseService.getAllExpensesByUser();
		return ResponseEntity.ok(expenses);
	}

	@GetMapping("/userWithExpense/{userId}/{expenseId}")
	public ResponseEntity<Dto> getExpenseWithUser(@PathVariable Long userId, @PathVariable Long expenseId) {
		Dto responseExpense = expenseService.getAllExpensesWithUser(userId, expenseId);
		return ResponseEntity.ok(responseExpense);
	}

	// Get a specific expense by its ID
	@GetMapping("/{expenseId}")
	public ResponseEntity<Expenses> getExpenseById(@PathVariable Long expenseId) {
		return expenseService.getExpenseById(expenseId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Add a new expense
	@PostMapping("/add")
	public ResponseEntity<Expenses> createExpense(@RequestBody Expenses expense) {
		Expenses createdExpense = expenseService.createExpense(expense);
		return ResponseEntity.ok(createdExpense);
	}

	// Update an existing expense
	@PutMapping("/{expenseId}")
	public ResponseEntity<Expenses> updateExpense(@PathVariable Long expenseId, @RequestBody Expenses expenseDetails) {
		Expenses updatedExpense = expenseService.updateExpense(expenseId, expenseDetails);
		return ResponseEntity.ok(updatedExpense);
	}

	// Delete an expense by its ID
	@DeleteMapping("/{expenseId}")
	public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
		expenseService.deleteExpense(expenseId);
		return ResponseEntity.noContent().build();
	}
}
