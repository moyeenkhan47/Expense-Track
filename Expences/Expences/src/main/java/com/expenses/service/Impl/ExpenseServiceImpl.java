package com.expenses.service.Impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenses.entity.Expenses;
import com.expenses.entity.User;
import com.expenses.model.Dto;
import com.expenses.model.ExpenseDto;
import com.expenses.model.ResponseExpense;
import com.expenses.model.UserDto;
import com.expenses.repository.ExpenseRepository;
import com.expenses.repository.UserRepository;
import com.expenses.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	public List<Expenses> getAllExpensesByUser() {
		return expenseRepository.findAll();
	}

	public Dto getAllExpensesWithUser(Long userId, Long expenseId) {
		// Fetch the user by userId and map to UserDto
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		UserDto userDto = modelMapper.map(user, UserDto.class);

		// Fetch the expense by expenseId and map to ExpenseDto
		Expenses expense = expenseRepository.findById(expenseId)
				.orElseThrow(() -> new RuntimeException("Expense not found with ID: " + expenseId));
		ExpenseDto expenseDto = modelMapper.map(expense, ExpenseDto.class);

		// Combine the UserDto and ExpenseDto into ResponseExpense
		ResponseExpense responseExpense = new ResponseExpense();
		responseExpense.setUserDto(userDto);
		responseExpense.setExpensesDto(expenseDto); // Update ResponseExpense to support a single ExpenseDto
		Dto dto = new Dto();
		dto.setResponseExpense(responseExpense);
		return dto;
	}

	public Optional<Expenses> getExpenseById(Long expenseId) {
		return expenseRepository.findById(expenseId);
	}

	public Expenses createExpense(Expenses expense) {
		return expenseRepository.save(expense);
	}

	public Expenses updateExpense(Long id, Expenses expenseDetails) {
		Expenses expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
		expense.setExpenseName(expenseDetails.getExpenseName());
		expense.setAmount(expenseDetails.getAmount());
		expense.setDate(expenseDetails.getDate());
		expense.setDescription(expenseDetails.getDescription());
		return expenseRepository.save(expense);
	}

	public void deleteExpense(Long expenseId) {
		expenseRepository.deleteById(expenseId);
	}

}