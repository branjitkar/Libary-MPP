package business;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord {
	private List<CheckoutEntry> checkoutEntries;

	public CheckoutRecord() {
		this.checkoutEntries = new ArrayList<>();
	}

	public List<CheckoutEntry> getCheckoutEntries() {
		// TODO: return unmodifiable list
		return checkoutEntries;
	}
}
