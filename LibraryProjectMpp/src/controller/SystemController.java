package controller;

import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import business.Author;
import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.LibraryException;
import business.LibraryMember;
import common.IOUtil;
import common.Util;
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
		List<String> columnHeaders = Arrays.asList(
				new String[] { "Book ISBN", "Book Title", "Book Copy Num", "Member ID", "Due Date", "Is Overdue" });

		List<List<String>> rows = new ArrayList<List<String>>();
		rows.add(columnHeaders);

		rows.addAll(getAvailableBookCopiesDetailsForOverdueReport(book));
		rows.addAll(getCheckedoutBookCopiesDetailsForOverdueReport(book));

		Util.printTable(rows);

	}

	private List<List<String>> getAvailableBookCopiesDetailsForOverdueReport(Book book) {
		List<List<String>> rows = new ArrayList<>();
		for (BookCopy bc : book.getBookCopies()) {
			if (bc.isAvailable()) {
				List<String> row = new ArrayList<String>();
				row.add(bc.getBook().getIsbn());
				row.add(bc.getBook().getTitle());
				row.add(bc.getCopyId());
				row.add("");
				row.add("");
				row.add("false");
				rows.add(row);
			}
		}
		return rows;
	}

	private List<List<String>> getCheckedoutBookCopiesDetailsForOverdueReport(Book book) {
		List<List<String>> rows = new ArrayList<>();
		HashMap<String, LibraryMember> allMembers = dao.readMemberMap();
		for (LibraryMember member : allMembers.values()) {
			String memberId = member.getMemberId();
			for (CheckoutEntry entry : member.getCheckoutRecord().getCheckoutEntries()) {
				BookCopy bc = entry.getBookCopy();
				LocalDate dueDate = entry.getDueDate();
				long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
				boolean isOverdue = daysRemaining < 0;
				if (entry.getBookCopy().getBook().equals(book)) {
					List<String> row = new ArrayList<String>();
					row.add(bc.getBook().getIsbn());
					row.add(bc.getBook().getTitle());
					row.add(bc.getCopyId());
					row.add(memberId);
					row.add(entry.getDueDate().toString());
					row.add(String.valueOf(isOverdue));
					rows.add(row);
				}
			}
		}
		return rows;
	}

//	private void printBookCopy(BookCopy bc, String memberId, String dueDate, boolean isOverdue) {
//		System.out.printf(" %s | %s | %s | %s | %s | %s \n", bc.getBook().getIsbn(), bc.getBook().getTitle(),
//				bc.getCopyId(), memberId, dueDate, String.valueOf(isOverdue));
//	}

	public void addLibraryMember(LibraryMember member) throws LibraryException {
		dao.addMember(member);
		IOUtil.printMessage("Member Has Been Added Successfully !!! \n\n" + member);

	}

	public void showMemberRecord() throws LibraryException {

		String memberId = IOUtil.getInput("Enter Member ID");
		LibraryMember mem = dao.getMemberById(memberId);
		while (mem == null) {
			System.out.println(
					IOUtil.ANSI_RED + "\nMember Doesn't Exists. Please Enter Valid MemberID." + IOUtil.ANSI_RESET);
			memberId = IOUtil.getInput("Enter Member ID");
			mem = dao.getMemberById(memberId);
		}
		IOUtil.printMessage(" Please choose the following option for the user " + mem.getFirstname());

		HashMap<String, String> options = new HashMap<>();
		options.put("1", "Show Member Details");
		options.put("2", "Print CheckOut Records");

		String selectedOption = IOUtil.getSelectedOption(options);

		switch (selectedOption) {
		case "1":
			System.out.println(mem);
			break;
		case "2":
			showMemberCheckoutRecord(mem);
			break;
		}
	}

	public void showMemberCheckoutRecord(LibraryMember mem) {
//		LocalDate checkoutDate = new LocalDate();
		List<CheckoutEntry> entries = mem.getCheckOutEntries();
		List<String> columnHeaders = Arrays
				.asList(new String[] { "Copy Number", "Book ISBN", "Book Title", "Checkout Date", "Due Date" });

		List<List<String>> rows = new ArrayList<List<String>>();
		rows.add(columnHeaders);

		for (CheckoutEntry entry : entries) {
			List<String> row = new ArrayList<String>();
			row.add(entry.getBookCopy().getCopyId());
			row.add(entry.getBookCopy().getBook().getIsbn());
			row.add(entry.getBookCopy().getBook().getTitle());
			row.add(entry.getCheckoutDate().toString());
			row.add(entry.getDueDate().toString());
			rows.add(row);
		}

		Util.printTable(rows);
	}

	public void showAllLibraryMembers() {
		List<List<String>> rows = new ArrayList<>();
		List<String> columnHeaders = Arrays
				.asList(new String[] { "Member ID", "First Name", "Last Name", "Address", "Phone" });
		rows.add(columnHeaders);

		HashMap<String, LibraryMember> allMembers = dao.readMemberMap();
		for (LibraryMember member : allMembers.values()) {
			List<String> row = new ArrayList<String>();
			row.add(member.getMemberId());
			row.add(member.getFirstname());
			row.add(member.getLastname());
			row.add(member.getAddress().toString());
			row.add(member.getPhone());
			rows.add(row);
		}

		Util.printTable(rows);
	}

	public void showAllBooks() {
		List<List<String>> rows = new ArrayList<>();
		List<String> columnHeaders = Arrays
				.asList(new String[] { "Book ISBN", "Book Title", "Max Checkout Days", "Number Of Copies" });
		rows.add(columnHeaders);

		HashMap<String, Book> allBooks = dao.readBooksMap();
		for (Book book : allBooks.values()) {
			List<String> row = new ArrayList<String>();
			row.add(book.getIsbn());
			row.add(book.getTitle());
			row.add(String.valueOf(book.getMaxCheckoutDay()));
			row.add(String.valueOf(book.getNumberOfCopy()));
			rows.add(row);
		}

		Util.printTable(rows);
	}

}
