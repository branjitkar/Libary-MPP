package business;

import java.io.Serializable;

public abstract class Person implements Serializable {
	private static final long serialVersionUID = -227203443064664514L;
	private String firstname;
	private String lastname;
	private String phone;
	private Address address;

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPhone() {
		return phone;
	}

	public Address getAddress() {
		return address;
	}

	public Person(String firstname, String lastname, String phone, Address address) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.address = address;
	}
}
