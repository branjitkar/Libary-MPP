package ui;

import business.LibraryException;
import controller.SystemController;
import dataaccess.Auth;
import dataaccess.User;

public class SystemMenu {
	private static SystemController sc = new SystemController();

	public SystemMenu() {
	}

	public void showMenu(User u) {

	}

	// TODO: UseCase1 - Utsab
	public void addLibraryMember() {

	}

	// TODO: UseCase2 - Bipul
	public void checkoutBook() throws LibraryException {
		// TODO: ask input for book isbn and memberId
		String isbn = "test";
		String memberId = "1001";
		sc.checkoutBook(isbn, memberId);
	}

	// TODO: UseCase3 - Antonedei
	public void addBook() {
		// initially focus on adding a copy to an already existing book
	}

}
