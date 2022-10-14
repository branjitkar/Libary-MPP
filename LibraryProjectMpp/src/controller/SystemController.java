package controller;

import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import business.Address;
import business.Author;
import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.LibraryException;
import business.LibraryMember;
import common.IOUtil;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController {
	private static DataAccess dao = new DataAccessFacade();

	public static Auth login(String username, String password) {
		HashMap<String, User> allUsers = dao.readUserMap();
		User user = allUsers.get(username);
		if (user == null)
			return null;
		if (user.getPassword().equals(password))
			return user.getAuthorization();
		return null;
	}

	/**
	 * Method to checkout book
	 * 
	 * @param isbn
	 * @param memberId
	 * @throws LibraryException
	 */
	public void checkoutBook(String isbn, String memberId) throws LibraryException {
		Book book = dao.getBookByIsbn(isbn);
		LibraryMember libraryMember = dao.getMemberById(memberId);

		// check if ISBN is valid.
		if (book == null)
			throw new LibraryException(String.format("Book with ISBN %s does not exist.", isbn));

		// check if memberId is valid
		if (libraryMember == null)
			throw new LibraryException(String.format("Library member with id %s does not exist.", memberId));

		BookCopy bookCopy = book.getAvailableCopy();
		// check if a book copy is available
		if (bookCopy == null)
			throw new LibraryException(String.format("No copies available for this book.", memberId));

		CheckoutEntry entry = libraryMember.checkoutBook(bookCopy);
		bookCopy.setAvailable(false);

		dao.updateMember(libraryMember);
		dao.updateBook(book);
		
		IOUtil.printMessage("Book checked out sucessfully !!!\n\n" + entry.toString());
	}

	// The code block below is to add a new book
	public void addBook(String isbn, String title, int maxCheckoutDay, List<Author> authors) throws LibraryException {
		Book book = dao.getBookByIsbn(isbn);
		if (book != null) {
			throw new LibraryException(String.format("Book with ISBN %s already exists.", isbn));
		}
		// List<Author> authors = new ArrayList<>();
		dao.addBook(new Book(isbn, title, maxCheckoutDay, authors));
	}

	// The code block below is to add a book copy to an existing book object.
	public void addBookCopy(String isbn) throws LibraryException {
		Book book = dao.getBookByIsbn(isbn);
		if (book == null) {
			throw new LibraryException(String.format("Book with ISBN %s does not exist.", isbn));
		}
		book.addBookCopy();
		dao.updateBook(book);
	}

	public void showOverdueBookCopies(String isbn) throws LibraryException {
		Book book = dao.getBookByIsbn(isbn);
		if (book == null) {
			throw new LibraryException(String.format("Book with ISBN %s does not exist.", isbn));
		}
		if (book.getNumberOfCopy() == 0)
			return;
		System.out.println();
		System.out.println(" ISBN | Title | Copy Num | Member Id | Due Date | Is Overdue");
		showAvailableBookCopies(book);
		showCheckedoutBookCopies(book);

	}

	private void showAvailableBookCopies(Book book) {
		for (BookCopy bc : book.getBookCopies()) {
			if (bc.isAvailable()) {
				printBookCopy(bc, "", "", false);
			}
		}
	}

	private void showCheckedoutBookCopies(Book book) {
		HashMap<String, LibraryMember> allMembers = dao.readMemberMap();
		for (LibraryMember member : allMembers.values()) {
			String memberId = member.getMemberId();
			for (CheckoutEntry entry : member.getCheckoutRecord().getCheckoutEntries()) {
				BookCopy bc = entry.getBookCopy();
				LocalDate dueDate = entry.getDueDate();
				long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
				boolean isOverdue = daysRemaining < 0;
				if (entry.getBookCopy().getBook().equals(book)) {
					printBookCopy(bc, memberId, dueDate.toString(), isOverdue);
				}
			}
		}
	}

	private void printBookCopy(BookCopy bc, String memberId, String dueDate, boolean isOverdue) {
		System.out.printf(" %s | %s | %s | %s | %s | %s \n", bc.getBook().getIsbn(), bc.getBook().getTitle(),
				bc.getCopyId(), memberId, dueDate, String.valueOf(isOverdue));
	}
	
	public void addLibraryMember(LibraryMember member) throws LibraryException {
		dao.addMember(member);
		IOUtil.printMessage("Member Has Been Added Successfully !!! \n\n" + member);
		
	}
}
