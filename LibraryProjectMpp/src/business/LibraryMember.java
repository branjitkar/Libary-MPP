package business;

public class LibraryMember extends Person {
	private int memberId;

	public LibraryMember(String firstname, String lastname, String phone, int memberId) {
		super(firstname, lastname, phone);
		this.memberId = memberId;
	}

	public int getMemberId() {
		return memberId;
	}

}
