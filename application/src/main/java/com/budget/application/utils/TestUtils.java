package com.budget.application.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.*;
import java.time.format.*;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;

import com.budget.application.model.Expense;
import com.budget.application.model.Tag;


//metody do testow junit

public class TestUtils {

	public String getRequestParamStringFromArray(String paramName, List<String> paramValues, int maximumAmountOfParams) {
		StringBuilder sb = new StringBuilder();
		List<String> targetList = new ArrayList<>(paramValues);
		
		for(int i=0; i<maximumAmountOfParams; i++) {
			String currentParamValue = targetList.get(i);
			sb.append(paramName + "=" + currentParamValue + "&");
		}
		String result = sb.toString();
		if(result.endsWith("&")) {
			result = result.substring(0,result.length()-1);
		}
		return result;
	}
	
	public String getRandomTextFromUUID() {
		return UUID.randomUUID().toString().replace("-","").substring(0,5);
		
	}
	
	//dodanie do listy parametrow
	
	public <T> List<T> append(List<T> list, T... args){
		return Stream.concat(list.stream(), Stream.of(args)).collect(Collectors.toList());
	}
	
	public byte[]  getBytesArrayFromJSON(JSONObject object) throws IOException {
		byte[] bytes = object.toString().getBytes();
		return bytes;
	}
	
	// @param - amountOfTagsToGenerate i expenseDate - @return
	
	public Expense generateTestExpense(int amountOfTagsToGenerate, LocalDateTime expenseDate) {
		Expense expense = new Expense();
		if(expenseDate == null) {
			expense.setCreationDate(LocalDateTime.now());
		} else {
			expense.setCreationDate(expenseDate);
		}
		expense.setTags(generateTestTags(amountOfTagsToGenerate, false));
		expense.setValue(getRandomDoubleFromGivenRange(0.0,10000));
		return expense;
	}
	
	public List<Tag> generateTestTags(int amountOfTagsToGenerate, boolean createIds) {
		List<Tag> randomTags = new ArrayList<Tag>();
		for(int i=0; i<amountOfTagsToGenerate; i++) {
			randomTags.add(new Tag(UUID.randomUUID().toString().substring(0,10)));
		} 
		return randomTags;		
	}
	
	public double getRandomDoubleFromGivenRange(double rangeMin, double rangeMax){
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax- rangeMin) * r.nextDouble();
		return randomValue;
		
	}
	
	public List<Expense> generateGivenAmountOfTestExpenseObjects(int amountOfExpenses, int amountOfTagsInEachExpense,
			Timestamp expenseDate){
		List<Expense> expenses = new ArrayList<Expense>();
		for(int i=0;i<amountOfExpenses;i++) {
			expenses.add(generateTestExpense(amountOfTagsInEachExpense, LocalDateTime.now()));
		}
		return expenses;
	}
	
	//zwraca Timestamp date Object from given date string in given format
	//@param - date format (yyyy-MMM-dd) - @return
	
	public Timestamp getDateFromGivenFormat(String dateInProperFormat, String format) throws ParseException {
		DateFormat formatter = new SimpleDateFormat();
		Date date = formatter.parse(dateInProperFormat);
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
						
	}
	
	public LocalDateTime getLocalDateTimeFromGivenFormat(String dateInProperFormat, String format) {
		//DateTimeFormatter formatter = new DateTimeFormatter.ofPattern(format);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		
		LocalDateTime dateTime = LocalDateTime.parse(dateInProperFormat, formatter);
		
		return dateTime;
		
	} 
	
	
}









