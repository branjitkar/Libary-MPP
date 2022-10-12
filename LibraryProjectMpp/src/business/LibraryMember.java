package business;

public class LibraryMember extends Person {
	private String memberId;

	public LibraryMember(String memberId, String firstname, String lastname, String phone, Address address) {
		super(firstname, lastname, phone, address);
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

}
