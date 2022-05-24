package com.udemy.tdd.isbntools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateISBNtest {

	@Test
	void checkValidISBN() {
		ValidateISBN validate = new ValidateISBN();
		boolean result = validate.checkISBN("0140449116");
		assertTrue(result);
	}
	
	@Test
	void ISBNlastIndexasX() {
		ValidateISBN validate = new ValidateISBN();
		boolean result = validate.checkISBN("043942089X");
		assertTrue(result);
				
	}
	
	@Test
	void checkInValidISBN() {
		ValidateISBN validate = new ValidateISBN();
		boolean result = validate.checkISBN("0140449117");
		assertFalse(result);
	}
	
	@Test
	void checkNineDigit() {
		ValidateISBN validate = new ValidateISBN();
		assertThrows(NumberFormatException.class,
				() -> {validate.checkISBN("140449116");});
	}
	
	@Test
	void checkStringISBN() {
		ValidateISBN validate = new ValidateISBN();
		assertThrows(NumberFormatException.class,
				() -> {validate.checkISBN("helloworld");});
	}
	
	@Test
	void checkThirteenDigitISBN() {
	ValidateISBN validate = new ValidateISBN();
	boolean result = validate.checkISBN("9781782116820");
	assertTrue(result, "13 digit number");
		
	}
}

