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
	//The code block below is to add a book copy to an existing book object.
	public void addBookCopy() {
		// TODO: implement this
		String copyId = Java.util.UUID.randomUUID().toString();
		bookCopies.add(new BookCopy(this, copyId));
		numberOfCopy++;

	}
	//The code block below is to add an author to an existing book object.
	public void addAuthor(String firstname, String lastname, String phone, Address address, String credential){
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
}
