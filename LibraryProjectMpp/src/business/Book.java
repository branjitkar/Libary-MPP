package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
	private static final long serialVersionUID = 4071300205964358396L;
	private String isbn;
	private String title;
	private int maxCheckoutDay;
	private int numberOfCopy;
	private List<Author> authors;
	private List<BookCopy> bookCopies;

	public Book(String isbn, String title, int maxCheckoutDay, List<Author> authors) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutDay = maxCheckoutDay;
		this.numberOfCopy = 0;
		this.authors = authors;
		this.bookCopies = new ArrayList<>();
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public int getMaxCheckoutDay() {
		return maxCheckoutDay;
	}

	public int getNumberOfCopy() {
		return numberOfCopy;
	}

	public List<BookCopy> getBookCopies() {
		// TODO: convert to unmodifiable List
		return bookCopies;
	}

	public List<Author> getAuthors() {
		// TODO: convert to unmodifiable List
		return authors;
	}

	// The code block below is to add a book copy to an existing book object.
	public void addBookCopy() {
		String copyId = generateCopyId();
		bookCopies.add(new BookCopy(this, copyId));
		numberOfCopy++;

	}

	// The code block below is to add an author to an existing book object.
	public void addAuthor(String firstname, String lastname, String phone, Address address, String credential) {
		authors.add(new Author(firstname, lastname, phone, address, credential));
	}

	/**
	 * @returns the first available bookCopy from the list of bookCopies. null if
	 *          there are no available bookCopy
	 */
	public BookCopy getAvailableCopy() {
		for (BookCopy copy : bookCopies) {
			if (copy.isAvailable())
				return copy;
		}
		return null;
	}

	// gets a unique copy id for the book
	private String generateCopyId() {
		if (bookCopies.isEmpty())
			return "1";
		String maxCopyId = bookCopies.get(0).getCopyId();
		for (BookCopy copy : bookCopies) {
			if (maxCopyId.compareTo(copy.getCopyId()) < 0)
				maxCopyId = copy.getCopyId();
		}
		int nextCopyIdVal = Integer.parseInt(maxCopyId) + 1;
		return String.valueOf(nextCopyIdVal);
	}

	@Override
	public boolean equals(Object ob) {
		if (ob == null)
			return false;
		if (!(ob instanceof Book))
			return false;
		Book book = (Book) ob;
		return isbn.equals(book.getIsbn());
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + "\n" 
				+ "Title: " + title + "\n" 
				+ "Max Checkout Day: " + maxCheckoutDay + "\n"
				+ "Number of Copies: " + numberOfCopy;
	}
}
