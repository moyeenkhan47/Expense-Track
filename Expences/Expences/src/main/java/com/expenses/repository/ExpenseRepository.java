package com.expenses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.entity.Expenses;

public interface ExpenseRepository extends JpaRepository<Expenses, Long> {

	List<Expenses> getAllByUserId(String username);



}
