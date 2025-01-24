package com.expenses.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExpenseDto {
	private Long expenseId;

	private String expenseName;

	private Double amount;

	private Date date;

	private String description;

	private Long userId;
}
