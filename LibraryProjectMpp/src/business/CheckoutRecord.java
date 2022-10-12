package business;

import java.io.Serializable;
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
}
