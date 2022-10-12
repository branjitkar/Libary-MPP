package business;

public class LibraryMember extends Person {
	private static final long serialVersionUID = -5194813788730605865L;
	private String memberId;

	public LibraryMember(String memberId, String firstname, String lastname, String phone, Address address) {
		super(firstname, lastname, phone, address);
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

}
