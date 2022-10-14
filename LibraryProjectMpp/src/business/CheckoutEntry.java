package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {
	private static final long serialVersionUID = -4752564480152440616L;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookCopy;

	public CheckoutEntry(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

	public BookCopy getBookCopy() {
		// TODO: return unmodifiable object
		return bookCopy;
	}

	public LocalDate getCheckoutDate() {
		// TODO: return unmodifiable object
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		// TODO: return unmodifiable object
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Book ISBN: " + bookCopy.getBook().getIsbn() + "\n" 
				+ "Copy Number: " + bookCopy.getCopyId() + "\n" 
				+ "Checkout Date" + checkoutDate + "\n"
				+ "Due Date: " + dueDate;
	}
}
