package com.budget.application.response.provider;

import java.util.List;

import com.budget.application.model.Expense;

public class ExpensesList {
	
	private List<Expense> expenses;

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public ExpensesList(List<Expense> expenses) {
		super();
		this.expenses = expenses;
	}

	public ExpensesList() {
		super();
	}
	

}
