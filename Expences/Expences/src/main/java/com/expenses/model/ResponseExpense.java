package com.expenses.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseExpense {
	private ExpenseDto expensesDto;
	private UserDto userDto;

}
