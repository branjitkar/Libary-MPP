package business;

public class Author extends Person {
	private String credential;

	public Author(String firstname, String lastname, String phone, String credential) {
		super(firstname, lastname, phone);
		this.credential = credential;
	}

	public String getCredential() {
		return credential;
	}

}
