package controller;

import business.Book;
import business.BookCopy;
import business.LibraryException;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class SystemController {
	private static DataAccess dao = new DataAccessFacade();

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
	//The code block below is to add a new book
	public void addBook(String isbn, String title, int maxCheckoutDay) throws LibraryException {
		Book book = dao.getBookByIsbn(isbn);
		if (book != null){
			throw new LibraryException(String.format("Book with ISBN %s already exists.", isbn));
		}
		List<Author> authors = new ArrayList<>();
		dao.addBook(new Book(isbn, title, maxCheckoutDay, authors));
	}
	//The code block below is to add authors to an existing book
	public void addAuthor(String isbn, String firstname, String lastname, String phone, Address address, String credential) throws LibraryException {
		Book book = dao.getBookByIsbn(isbn);
		if (book == null){
			throw new LibraryException(String.format("Book with ISBN %s does not exist.", isbn));
		}
		book.addAuthor(String firstname, String lastname, String phone, Address address, String credential);
		dao.updateBook(book);
	}
	//The code block below is to add a book copy to an existing book object.
	public void addBookCopy(String isbn) throws LibraryException {
		Book book = dao.getBookByIsbn(isbn);
		if (book == null){
			throw new LibraryException(String.format("Book with ISBN %s does not exist.", isbn));
		}
		book.addBookCopy();
		dao.updateBook(book);
	}
}
