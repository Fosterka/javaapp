package com.budget.application.service1;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.budget.application.model.Expense;
import com.budget.application.model.ExpensesSearchCriteria;
import com.budget.application.model.Tag;
import com.budget.application.repository.ExpenseRepository;
import com.budget.application.service.ExpensesService;
import com.budget.application.utils.TestUtils;





@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpensesServiceIntegrationImplTest {

	@Autowired
	private ExpensesService expensesService;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	private TestUtils testUtils;
	
	private Expense testExpense;
	
	@BeforeEach
	void setUp() throws Exception{
		testUtils = new TestUtils();
		testExpense = testUtils.generateTestExpense(1, LocalDateTime.now());
		for(int i=0; i<10; i++) {
			expensesService.createExpense(testUtils.generateTestExpense(1, LocalDateTime.now()));
			
		}
		
	}
	
	@Test
	void testCreateExpense() {
		Expense expense = expensesService.createExpense(testExpense);
		assertEquals(expense.getValue(),testExpense.getValue());
		
	}
	
	@Test
	void testDeleteExpense() {
		Long expenseId = expensesService.getAllExpenses().get().get(0).getId();
		expensesService.deleteExpense(expenseId);
		Optional<Expense> foundById = expenseRepository.findById(expenseId);
		assertFalse(foundById.isPresent());
		
	}
	
	@Test
	void testGetAllExpenses() {
		List<Expense> allExpenses = expensesService.getAllExpenses().get();
		assertTrue(allExpenses.size()>10);
		
	}
	
	@Test
	void testGetExpensesByCriteriaWithTagsSettedOnly() {
		ExpensesSearchCriteria expensesSearchCriteria = new ExpensesSearchCriteria();
		List<String> tagNames = expensesService.getAllExpenses().get().get(0).getTags().stream().map(Tag::getName).collect(Collectors.toList()); 
		expensesSearchCriteria.setTagNames(tagNames);
		Optional<List<Expense>> expensesRetreivedByCriteria = expensesService.getExpensesBySearchCriteria(expensesSearchCriteria);
		assertTrue(expensesRetreivedByCriteria.isPresent());
		assertTrue(expensesRetreivedByCriteria.get().size()>0);		
		
		
	}
	
	@Test
	void testGetExpensesByCriteriaWithFromDateSettedOnly() {
		ExpensesSearchCriteria expensesSearchCriteria = new ExpensesSearchCriteria();
		LocalDateTime fromDate = expensesService.getAllExpenses().get().get(0).getCreationDate();
		fromDate = fromDate.minusSeconds(1);
		expensesSearchCriteria.setFromDate(Timestamp.valueOf(fromDate));
		Optional<List<Expense>> expensesRetreivedByCriteria = expensesService.getExpensesBySearchCriteria(expensesSearchCriteria);
		assertTrue(expensesRetreivedByCriteria.isPresent());
		assertTrue(expensesRetreivedByCriteria.get().size()>0);		
		
		
	}
	
	@Test
	void testGetExpensesByCriteriaWithToDateSettedOnly() {
		ExpensesSearchCriteria expensesSearchCriteria = new ExpensesSearchCriteria();
		LocalDateTime toDate = expensesService.getAllExpenses().get().get(0).getCreationDate();
		toDate = toDate.plusSeconds(5);
		expensesSearchCriteria.setToDate(Timestamp.valueOf(toDate));
		Optional<List<Expense>> expensesRetreivedByCriteria = expensesService.getExpensesBySearchCriteria(expensesSearchCriteria);
		assertTrue(expensesRetreivedByCriteria.isPresent());
		assertTrue(expensesRetreivedByCriteria.get().size()>0);		
		
		
	}
	
	@Test
	void testGetExpensesByCriteriaWithBothDateSettedOnly() {
		ExpensesSearchCriteria expensesSearchCriteria = new ExpensesSearchCriteria();
		LocalDateTime creationDate = expensesService.getAllExpenses().get().get(0).getCreationDate();
		LocalDateTime fromDate = creationDate.minusSeconds(5);
		LocalDateTime toDate = creationDate.plusSeconds(5);
		expensesSearchCriteria.setFromDate(Timestamp.valueOf(fromDate));
		expensesSearchCriteria.setToDate(Timestamp.valueOf(toDate));
		Optional<List<Expense>> expensesRetreivedByCriteria = expensesService.getExpensesBySearchCriteria(expensesSearchCriteria);
		assertTrue(expensesRetreivedByCriteria.isPresent());
		assertTrue(expensesRetreivedByCriteria.get().size()>0);		
		
		
	}
	
	@Test
	void testGetExpensesByCriteriaWithBothDateAndTagsSetted() {
		ExpensesSearchCriteria expensesSearchCriteria = new ExpensesSearchCriteria();
		Expense retreivedExpense = expensesService.getAllExpenses().get().get(0);
		LocalDateTime creationDate = retreivedExpense.getCreationDate();
		LocalDateTime fromDate = creationDate.minusSeconds(5);
		LocalDateTime toDate = creationDate.plusSeconds(5);
		expensesSearchCriteria.setFromDate(Timestamp.valueOf(fromDate));
		expensesSearchCriteria.setToDate(Timestamp.valueOf(toDate));
		List<String> tagNames = retreivedExpense.getTags().stream().map(Tag::getName).collect(Collectors.toList());
		expensesSearchCriteria.setTagNames(tagNames);
		Optional<List<Expense>> expensesRetreivedByCriteria = expensesService.getExpensesBySearchCriteria(expensesSearchCriteria);
		assertTrue(expensesRetreivedByCriteria.isPresent());
		assertTrue(expensesRetreivedByCriteria.get().size()>0);		
		
		
	}
}




