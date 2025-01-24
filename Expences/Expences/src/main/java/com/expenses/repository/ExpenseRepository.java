package com.expenses.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenses.entity.Expenses;
@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, Long> {


	Optional<Expenses> findByUserId(Long userId);




}
