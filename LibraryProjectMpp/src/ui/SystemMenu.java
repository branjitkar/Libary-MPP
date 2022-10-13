package ui;

import java.util.HashMap;

import business.LibraryException;
import common.IOUtil;
import controller.SystemController;
import dataaccess.Auth;

public class SystemMenu {
	private static SystemController sc = new SystemController();

	public SystemMenu() {
	}

	public void showMenu(Auth auth) {
		IOUtil.printTitle("Main Menu", 15);
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
		options.put("1", "Add library member");
		options.put("2", "Add Book");
		options.put("3", "Add book copy");

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
		options.put("1", "Add library member");
		options.put("2", "Add Book");
		options.put("3", "Add book copy");
		options.put("4", "Checkout book");

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

		IOUtil.printExceptionMessage("Operation Successful");
	}
	
	public void addBook() {
		// initially focus on adding a copy to an already existing book
	}

}
