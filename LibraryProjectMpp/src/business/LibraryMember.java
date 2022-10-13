package business;

public class LibraryMember extends Person {
	private static final long serialVersionUID = -5194813788730605865L;
	private String memberId;
	private CheckoutRecord checkoutRecord;

	public LibraryMember(String memberId, String firstname, String lastname, String phone, Address address) {
		super(firstname, lastname, phone, address);
		this.memberId = memberId;
		this.checkoutRecord = new CheckoutRecord();
	}

	public String getMemberId() {
		return memberId;
	}
	
	/**
	 * Adds book copy to checkoutRecord of member
	 * @param bookCopy
	 */
	public void checkoutBook(BookCopy bookCopy) {
		this.checkoutRecord.addCheckoutEntry(bookCopy);
	}

}
