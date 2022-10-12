package business;

public abstract class Person {
	private String firstname;

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPhone() {
		return phone;
	}

	private String lastname;
	private String phone;

	public Person(String firstname, String lastname, String phone) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
	}
}
