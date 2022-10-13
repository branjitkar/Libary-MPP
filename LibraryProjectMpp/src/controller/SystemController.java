package controller;

import java.util.List;
import java.util.HashMap;

import business.Author;
import business.Book;
import business.BookCopy;
import business.LibraryException;
import business.LibraryMember;
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

		libraryMember.checkoutBook(bookCopy);

		dao.updateMember(libraryMember);
		dao.updateBook(book);
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

	// The code block below is to add authors to an existing book
//	public void getAuthor(String isbn, String firstname, String lastname, String phone, Address address,
//			String credential) throws LibraryException {
//		Book book = dao.getBookByIsbn(isbn);
//		if (book == null) {
//			throw new LibraryException(String.format("Book with ISBN %s does not exist.", isbn));
//		}
//		book.addAuthor(firstname, lastname, phone, address, credential);
//		dao.updateBook(book);
//	}

	// The code block below is to add a book copy to an existing book object.
	public void addBookCopy(String isbn) throws LibraryException {
		Book book = dao.getBookByIsbn(isbn);
		if (book == null) {
			throw new LibraryException(String.format("Book with ISBN %s does not exist.", isbn));
		}
		book.addBookCopy();
		dao.updateBook(book);
	}
}
