package com.nagarro.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Books 
{
	
	private int bookCode;
	
	private String bookName;
	
	private Date createdAt;
	
	private Authors author;

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Authors getAuthor() {
		return author;
	}

	public void setAuthor(Authors author) {
		this.author = author;
	}
	
	public String getAuthorName() {
		return this.author.getAuthorName();
	}
	
	public String getFormattedDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
		String strDate = formatter.format(this.getCreatedAt());
		return strDate;
	}

	@Override
	public String toString() {
		return "Books [bookCode=" + bookCode + ", bookName=" + bookName + ", createdAt=" + createdAt + ", author="
				+ author + "]";
	}
	
	
}
