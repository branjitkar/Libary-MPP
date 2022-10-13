package controller;

import java.util.HashMap;

import business.Book;
import business.BookCopy;
import business.LibraryException;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class SystemController {
	private static DataAccess dao = new DataAccessFacade();

	public void checkoutBook(String isbn, String memberId) throws LibraryException {
		HashMap<String, Book> allBooks = dao.readBooksMap();
		HashMap<String, LibraryMember> allMembers = dao.readMemberMap();

		Book book = allBooks.get(isbn);
		// check if ISBN is valid.
		if (book == null)
			throw new LibraryException(String.format("Book with ISBN %s does not exist.", isbn));

		LibraryMember libraryMember = allMembers.get(memberId);
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
}
