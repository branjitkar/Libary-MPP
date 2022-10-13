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

	public void addCopy() {
		// TODO: implement this
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
}
