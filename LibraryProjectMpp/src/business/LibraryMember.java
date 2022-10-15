package business;

import java.util.List;

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
	
	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}
	
	/**
	 * Adds book copy to checkoutRecord of member
	 * @param bookCopy
	 */
	public CheckoutEntry checkoutBook(BookCopy bookCopy) {
		return this.checkoutRecord.addCheckoutEntry(bookCopy);
	}
	
	@Override
	public String toString() {
		return "Member Id: "+ memberId+"\n"+
				"First Name: "+super.getFirstname()+"\n"+
				"Last Name: "+super.getLastname()+"\n"+
				"Phone Number: "+super.getPhone()+"\n"+
				"Address: "+super.getAddress();		
	} 
	
	public List<CheckoutEntry> getCheckOutEntries() {
		return this.checkoutRecord.getCheckoutEntries();
	}

}
