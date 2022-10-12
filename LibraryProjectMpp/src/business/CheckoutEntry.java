package business;

import java.time.LocalDate;

public class CheckoutEntry {
	private LocalDate checkoutDate;
	private LocalDate dueDate;

	public CheckoutEntry(LocalDate checkoutDate, LocalDate dueDate) {
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
}
