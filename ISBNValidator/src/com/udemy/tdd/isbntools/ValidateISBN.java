package com.udemy.tdd.isbntools;

public class ValidateISBN {

	private static final int SHORT_ISBN_MULTIPLIER = 11;
	private static final int LONG_ISBN_MULTIPLIER = 10;
	private static final int SHORT_ISBN_LENGTH = 10;
	private static final int LONG_ISBN_LENGTH = 13;

	public boolean checkISBN(String isbn) {

		// For 10 digit ISBN
		if (isbn.length() == SHORT_ISBN_LENGTH) {
			return isThisaValidShortISBN(isbn);
		}
		// For 13 digit ISBN
		else if (isbn.length() == LONG_ISBN_LENGTH) {
			return isThisaValidLongISBN(isbn);
		} 

		throw new NumberFormatException("ISBN must be 10 or 13 digits long");
	}

	private boolean isThisaValidLongISBN(String isbn) {
		int total = 0;
		int evenTotal = 0, oddTotal = 0;
		for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
			if (i % 2 == 0) {
				evenTotal += Character.getNumericValue(isbn.charAt(i));
			} else
				oddTotal += Character.getNumericValue(isbn.charAt(i));
			total = evenTotal + oddTotal;
		}
		return (total % LONG_ISBN_MULTIPLIER == 0);
	}

	private boolean isThisaValidShortISBN(String isbn) {
		int total = 0;
		for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
			if (!Character.isDigit(isbn.charAt(i))) {
				if (i == SHORT_ISBN_LENGTH-1 && isbn.charAt(i) == 'X') {
					total += 10;
				} else {
					throw new NumberFormatException("ISBN is digit only");
				}
			}	
			total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
		}
		return (total % SHORT_ISBN_MULTIPLIER == 0) ;
	}

}
