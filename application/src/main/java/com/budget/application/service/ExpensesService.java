package com.budget.application.service;

import java.util.List;
import java.util.Optional;

import com.budget.application.model.Expense;
import com.budget.application.model.ExpensesSearchCriteria;

public interface ExpensesService {
	
	public Optional<List<Expense>> getExpensesBySearchCriteria(ExpensesSearchCriteria criteria);

	public Expense createExpense(Expense expense);

	public void deleteExpense(Long expenseId);
	
	public Optional<List<Expense>> getAllExpenses();
	
	
	
	
	
	
}
