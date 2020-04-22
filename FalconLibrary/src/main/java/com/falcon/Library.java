package com.falcon;

import java.util.List;

public class Library {
	
	AuthorWrap authorWrap;
	
	List<Book> books;

	public AuthorWrap getAuthorWrap() {
		return authorWrap;
	}

	public void setAuthorWrap(AuthorWrap authorWrap) {
		this.authorWrap = authorWrap;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Library [AuthorWrap=" + authorWrap + ", Books=" + books + "]";
	}
 
	

}
