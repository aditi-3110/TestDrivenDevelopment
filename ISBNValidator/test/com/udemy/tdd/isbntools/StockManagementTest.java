package com.udemy.tdd.isbntools;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

class StockManagementTest {
	
	@Test
	public void getCorrectLocatorCode() {
		ExternalISBNDataInterface testWebService = new ExternalISBNDataInterface() {		
			@Override
			public Book lookup(String ISBN) {
				return new Book(ISBN, "Think Like a Monk", "Jay Shetty");
			}
		};		
		
		ExternalISBNDataInterface testDatabaseService = new ExternalISBNDataInterface() {
			@Override   
			public Book lookup(String ISBN) {
				return null;
			}
		};
		
		String isbn = "0140449116";
		StockManager stockmanager = new StockManager();
		stockmanager.setWebService(testWebService);
		stockmanager.setDatabaseService(testDatabaseService);
		
		String locatorCode = stockmanager.getLocatorCode(isbn); // last 4 digits of ISBN + Author initial + No. of words in the title
		assertEquals("9116J4", locatorCode);
		
	}
	
	@Test
	public void databaseIsUsedIfDataIsPresentinDB() {
		ExternalISBNDataInterface databaseService = mock(ExternalISBNDataInterface.class);
		ExternalISBNDataInterface webservice = mock(ExternalISBNDataInterface.class);
		
		when(databaseService.lookup("0140449116")).thenReturn(new Book("0140449116","abc","abcd"));

		StockManager stockmanager = new StockManager();
		stockmanager.setWebService(webservice);
		stockmanager.setDatabaseService(databaseService);
		
		String isbn = "0140449116";
		String locatorCode = stockmanager.getLocatorCode(isbn); // last 4 digits of ISBN + Author initial + No. of words in the title
		//assertEquals("9116J4", locatorCode);
		
		verify(databaseService, times(1)).lookup("0140449116");
		verify(webservice, times(0)).lookup(anyString());
		
		
	}
	
	@Test
	public void webServiceIsUsedIfDataIsNotPresentInDB() {
		ExternalISBNDataInterface databaseService = mock(ExternalISBNDataInterface.class);
		ExternalISBNDataInterface webservice = mock(ExternalISBNDataInterface.class);
		
		when(databaseService.lookup("0140449116")).thenReturn(null);
		when(webservice.lookup("0140449116")).thenReturn(new Book("0140449116","abc","abcd"));

		StockManager stockmanager = new StockManager();
		stockmanager.setWebService(webservice);
		stockmanager.setDatabaseService(databaseService);
		
		String isbn = "0140449116";
		String locatorCode = stockmanager.getLocatorCode(isbn); // last 4 digits of ISBN + Author initial + No. of words in the title
		//assertEquals("9116J4", locatorCode);
		
		verify(databaseService, times(1)).lookup("0140449116");
		verify(webservice, times(1)).lookup(anyString());
		
	}

}
