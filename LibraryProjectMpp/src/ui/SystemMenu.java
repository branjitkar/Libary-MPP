package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Address;
import business.Author;
import business.LibraryException;
import business.LibraryMember;
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
		options.put("1", "Add Library Member");
		options.put("2", "Add Book");
		options.put("3", "Add Book Copy");
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
		options.put("3", "Get Member Checkout History");
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
		case "3":
			getMemberCheckoutRecord();
			break;
		}
		return false;
	}

	private boolean openBothMenu() throws LibraryException {
		HashMap<String, String> options = new HashMap<>();
		options.put("1", "Add Library Member");
		options.put("2", "Add Book");
		options.put("3", "Add Book Copy");
		options.put("4", "Checkout Book");
		options.put("5", "Overdue report");
		options.put("6", "Get Member Checkout History");
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
		case "6":
			getMemberCheckoutRecord();
			break;
		}
		return false;
	}

	// UseCase 1 - Utsab
	private void addLibraryMember() throws LibraryException {
		IOUtil.printTitle("Add A New Member", 15);
		String memberId = IOUtil.getInput("Enter Member Id");

		String fname = IOUtil.getInput("Enter First Name");
		String lname = IOUtil.getInput("Enter Last Name");
		String phone = IOUtil.getInput("Enter Phone Number");

		String street = IOUtil.getInput("Enter Street");
		String city = IOUtil.getInput("Enter City");
		String state = IOUtil.getInput("Enter State");
		String zip = IOUtil.getNumberInput("Enter Zip");

		Address address = new Address(street, city, state, zip);
		LibraryMember libMem = new LibraryMember(memberId, fname, lname, phone, address);

		sc.addLibraryMember(libMem);
	}

	// UseCase2 Checkout - Bipul
	private void checkoutBook() throws LibraryException {
		IOUtil.printTitle("Checkout Book", 15);
		String isbn = IOUtil.getInput("Enter ISBN");
		String memberId = IOUtil.getInput("Enter Member Id");

		sc.checkoutBook(isbn, memberId);
	}

	// UseCase3 - Antonedei
	private void addBookCopy() throws LibraryException {
		IOUtil.printTitle("Add Book Copy", 15);
		String isbn = IOUtil.getInput("Enter Book ISBN");
		sc.addBookCopy(isbn);
	}

	private void addBook() throws LibraryException {
		IOUtil.printTitle("Add Book", 15);
		String isbn = IOUtil.getInput("Enter Book ISBN");
		String title = IOUtil.getInput("Enter Book Title");
		int maxCheckOutDay = Integer.parseInt(IOUtil.getNumberInput("Enter Maximum Checkout Day"));
		List<Author> authors = new ArrayList<>();
		while (true) {
			String firstname = IOUtil.getInput("Enter Author's Firstname");
			String lastname = IOUtil.getInput("Enter Author's Lastname");
			String phone = IOUtil.getInput("Enter Author's Phone Number");

			// getting address
			String street = IOUtil.getInput("Enter Street");
			String city = IOUtil.getInput("Enter City");
			String state = IOUtil.getInput("Enter State");
			String zip = IOUtil.getNumberInput("Enter Zip");
			Address address = new Address(street, city, state, zip);

			String credential = IOUtil.getInput("Enter Author's credentials");

			authors.add(new Author(firstname, lastname, phone, address, credential));

			String check = IOUtil.getInput("Do you want to add more authors? (Y/N)");
			if (!check.toUpperCase().equals("Y"))
				break;
		}
		sc.addBook(isbn, title, maxCheckOutDay, authors);
	}

	// UseCase 6: get list of bookcopies and overdue report by book isbn
	private void showOverdueBookCopies() throws LibraryException {
		IOUtil.printTitle("Show Overdue Report", 15);
		String isbn = IOUtil.getInput("Enter Book ISBN");

		sc.showOverdueBookCopies(isbn);
	}

	// UseCase 6: get list of bookcopies and overdue report by book isbn
	public void getMemberCheckoutRecord() throws LibraryException {
		IOUtil.printTitle("Get Member Checkout Record", 15);
		sc.showMemberRecord();
	}

}
