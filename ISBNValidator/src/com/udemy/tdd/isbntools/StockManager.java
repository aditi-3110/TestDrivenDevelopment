package com.udemy.tdd.isbntools;

public class StockManager {
	private ExternalISBNDataInterface webService;
	public ExternalISBNDataInterface databaseService;


	public void setDatabaseService(ExternalISBNDataInterface databaseService) {
		this.databaseService = databaseService;
	}

	public void setWebService(ExternalISBNDataInterface service) {
		this.webService = service;
	}

	public String getLocatorCode(String ISBN) {
		Book book = databaseService.lookup(ISBN);
		if (book == null) book = webService.lookup(ISBN);
		StringBuilder locator = new StringBuilder();
		locator.append(ISBN.substring(ISBN.length()-4));
		locator.append(book.getAuthor().substring(0,1));
		locator.append(book.getTitle().split(" ").length);
		return locator.toString();
	}

}
