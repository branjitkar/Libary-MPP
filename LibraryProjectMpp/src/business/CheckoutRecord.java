package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = -5601691040492716164L;
	private List<CheckoutEntry> checkoutEntries;

	public CheckoutRecord() {
		this.checkoutEntries = new ArrayList<>();
	}

	public List<CheckoutEntry> getCheckoutEntries() {
		// TODO: return unmodifiable list
		return checkoutEntries;
	}

	public CheckoutEntry addCheckoutEntry(BookCopy bookCopy) {
		LocalDate checkoutDate = LocalDate.now();
		LocalDate dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutDay());
		CheckoutEntry checkoutEntry = new CheckoutEntry(bookCopy, checkoutDate, dueDate);
		checkoutEntries.add(checkoutEntry);
		bookCopy.setAvailable(false);
		return checkoutEntry;
	}
}
