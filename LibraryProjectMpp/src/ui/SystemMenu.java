package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Address;
import business.Author;
import business.LibraryException;
import common.IOUtil;
import controller.SystemController;
import dataaccess.Auth;

public class SystemMenu {
	private static SystemController sc = new SystemController();
	private Auth auth;

	public SystemMenu() {
	}

	public void showMenu(Auth auth) {
		IOUtil.printTitle("Main Menu", 15);
		this.auth = auth;
		try {
			switch (auth) {
			case ADMIN:
				openAdminMenu();
				break;
			case LIBRARIAN:
				openLibrarianMenu();
				break;
			case BOTH:
				openBothMenu();
				break;
			}
		} catch (LibraryException le) {
			IOUtil.printExceptionMessage(le.getMessage());
		}
	}

	private void openAdminMenu() throws LibraryException {
		HashMap<String, String> options = new HashMap<>();
		options.put("0", "Exit");
		options.put("1", "Add Library Member");
		options.put("2", "Add Book");
		options.put("3", "Add Book Copy");

		String selectedOption = IOUtil.getSelectedOption(options);

		switch (selectedOption) {
		case "0":
			break;
		case "1":
			addLibraryMember();
			break;
		case "2":
			addBook();
			break;
		case "3":
			addBookCopy();
			break;
		}
	}

	private void openLibrarianMenu() throws LibraryException {
		HashMap<String, String> options = new HashMap<>();
		options.put("0", "Exit");
		options.put("1", "Checkout book");

		String selectedOption = IOUtil.getSelectedOption(options);

		switch (selectedOption) {
		case "0":
			break;
		case "1":
			checkoutBook();
		}
	}

	private void openBothMenu() throws LibraryException {
		HashMap<String, String> options = new HashMap<>();
		options.put("0", "Exit");
		options.put("1", "Add Library Member");
		options.put("2", "Add Book");
		options.put("3", "Add Book Copy");
		options.put("4", "Checkout Book");

		String selectedOption = IOUtil.getSelectedOption(options);

		switch (selectedOption) {
		case "0":
			break;
		case "1":
			addLibraryMember();
			break;
		case "2":
			addBook();
			break;
		case "3":
			addBookCopy();
			break;
		case "4":
			checkoutBook();
		}
	}

	// TODO: UseCase1 - Utsab
	public void addLibraryMember() {

	}

	// UseCase2 Checkout - Bipul
	public void checkoutBook() throws LibraryException {
		IOUtil.printTitle("Checkout Book", 15);
		String isbn = IOUtil.getInput("Enter ISBN");
		String memberId = IOUtil.getInput("Enter Member Id");

		sc.checkoutBook(isbn, memberId);
	}

	// TODO: UseCase3 - Antonedei
	public void addBookCopy() throws LibraryException {
		IOUtil.printTitle("Add Book Copy", 15);
		String isbn = IOUtil.getInput("Enter Book ISBN");
		
		sc.addBookCopy(isbn);

		IOUtil.printSuccessMessage("Operation Successful");
		showMenu(auth);
	}
	
	public void addBook() throws LibraryException {
		IOUtil.printTitle("Add Book", 15);
		String isbn = IOUtil.getInput("Enter Book ISBN");
		String title = IOUtil.getInput("Enter Book Title");
		int maxCheckOutDay = Integer.parseInt(IOUtil.getInput("Enter Maximum Checkout Day"));
		List<Author> authors = new ArrayList<>();
		while(true){
			String firstname = IOUtil.getInput("Enter Author's Firstname");
			String lastname = IOUtil.getInput("Enter Author's Lastname");
			String phone = IOUtil.getInput("Enter Author's Phone Number");
			//getting address
			String address = IOUtil.getInput("Enter Author's Address in this format: (Street, City, State, Zip)");
			String[] addressArray = address.split(", ");
			Address actualAddress = new Address(addressArray[0],addressArray[1],addressArray[2],addressArray[3]);
			//done getting address
			String credential = IOUtil.getInput("Enter Author's credentials");
			authors.add(new Author(firstname,lastname,phone,actualAddress,credential));

			String check = IOUtil.getInput("Do you want to add more authors? (Y/N)");
			if (check.toUpperCase().equals("N")) break;
		}

		sc.addBook(isbn, title, maxCheckOutDay,authors);
		IOUtil.printSuccessMessage("Operation Successful");
		showMenu(auth);
	}

}
