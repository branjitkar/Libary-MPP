package business;

public class Author extends Person {
	private String credential;

	public Author(String firstname, String lastname, String phone, Address address, String credential) {
		super(firstname, lastname, phone, address);
		this.credential = credential;
	}

	public String getCredential() {
		return credential;
	}

}
