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
		while (true) {
			boolean exit = false;
			IOUtil.printTitle("Main Menu", 15);
			try {
				switch (auth) {
				case ADMIN:
					exit = openAdminMenu();
					break;
				case LIBRARIAN:
					exit = openLibrarianMenu();
					break;
				case BOTH:
					exit = openBothMenu();
					break;
				}
				if (exit) {
					IOUtil.printSuccessMessage("Exit Successful. Bye!");
					return;
				}

				IOUtil.printSuccessMessage("Operation Successful. Press enter to continue.");
				IOUtil.pauseConsole();
			} catch (LibraryException le) {
				IOUtil.printExceptionMessage(le.getMessage() + " Press enter to continue.");
				IOUtil.pauseConsole();
			}
		}
	}

	private boolean openAdminMenu() throws LibraryException {
		HashMap<String, String> options = new HashMap<>();
		options.put("1", "Add library member");
		options.put("2", "Add book");
		options.put("3", "Add book copy");
		options.put("9", "Exit");

		String selectedOption = IOUtil.getSelectedOption(options);

		switch (selectedOption) {
		case "9":
			return true;
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
		return false;
	}

	private boolean openLibrarianMenu() throws LibraryException {
		HashMap<String, String> options = new HashMap<>();
		options.put("1", "Checkout book");
		options.put("2", "Overdue report");
		options.put("9", "Exit");

		String selectedOption = IOUtil.getSelectedOption(options);

		switch (selectedOption) {
		case "9":
			return true;
		case "1":
			checkoutBook();
			break;
		case "2":
			showOverdueBookCopies();
			break;
		}
		return false;
	}

	private boolean openBothMenu() throws LibraryException {
		HashMap<String, String> options = new HashMap<>();
		options.put("1", "Add library member");
		options.put("2", "Add book");
		options.put("3", "Add book copy");
		options.put("4", "Checkout book");
		options.put("5", "Overdue report");
		options.put("9", "Exit");

		String selectedOption = IOUtil.getSelectedOption(options);

		switch (selectedOption) {
		case "9":
			return true;
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
			break;
		case "5":
			showOverdueBookCopies();
			break;
		}
		return false;
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
	}

	public void addBook() {
		// initially focus on adding a copy to an already existing book
	}

	// UseCase 6: get list of bookcopies and overdue report by book isbn
	public void showOverdueBookCopies() throws LibraryException {
		IOUtil.printTitle("Show Overdue Report", 15);
		String isbn = IOUtil.getInput("Enter Book ISBN");

		sc.showOverdueBookCopies(isbn);
	}

}
