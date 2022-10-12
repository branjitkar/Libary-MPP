package business;

import java.time.LocalDate;

public class CheckoutEntry {
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
}
