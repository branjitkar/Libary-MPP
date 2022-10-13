package business;

import java.io.Serializable;

public class BookCopy implements Serializable {
	private static final long serialVersionUID = -7525240684445327911L;
	private Book book;
	private String copyId;
	private boolean isAvailable;

	public BookCopy(Book book, String copyId) {
		this.book = book;
		this.copyId = copyId;
		this.isAvailable = true;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getCopyId() {
		return copyId;
	}

	public Book getBook() {
		return book;
	}

}
