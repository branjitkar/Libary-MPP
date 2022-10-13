package ui;

import java.util.HashMap;

import business.LibraryException;
import common.IOUtil;
import controller.SystemController;
import dataaccess.Auth;
import dataaccess.User;

public class SystemMenu {
	private static SystemController sc = new SystemController();

	public SystemMenu() {
	}

	public void showMenu(User u) {
		HashMap<String, String> options = new HashMap<>();
		options.put("1", "Add library member");
		options.put("2", "Add book copy");
		options.put("3", "Checkout book");
		options.put("0", "Exit");

		String selectedOption = IOUtil.getSelectedOption(options);

		try {
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
				checkoutBook();
			}
		} catch (LibraryException le) {
			IOUtil.printExceptionMessage(le.getMessage());
		}
	}

	// TODO: UseCase1 - Utsab
	public void addLibraryMember() {

	}

	// UseCase2 Checkout - Bipul
	public void checkoutBook() throws LibraryException {
		IOUtil.printTitle("Checkout Book");
		String isbn = IOUtil.getInput("Enter ISBN");
		String memberId = IOUtil.getInput("Enter Member Id");

		sc.checkoutBook(isbn, memberId);
	}

	// TODO: UseCase3 - Antonedei
	public void addBook() {
		// initially focus on adding a copy to an already existing book
	}

}
