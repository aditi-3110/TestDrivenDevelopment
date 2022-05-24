package com.udemy.tdd.isbntools;

public class Book {
	private String ISBN;
	private String title;
	private String author;
	public Book(String iSBN, String title, String author) {
		super();
		ISBN = iSBN;
		this.title = title;
		this.author = author;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	

}
