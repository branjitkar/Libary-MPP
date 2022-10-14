package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryException;
import business.LibraryMember;

public interface DataAccess {
	public HashMap<String, Book> readBooksMap();

	public HashMap<String, User> readUserMap();

	public HashMap<String, LibraryMember> readMemberMap();

	public Book getBookByIsbn(String isbn);

	public void addBook(Book book) throws LibraryException;

	public void updateBook(Book book);

	public LibraryMember getMemberById(String id);

	public void addMember(LibraryMember member) throws LibraryException;

	public void updateMember(LibraryMember member);
}
