package business;

public class Book {
	private String isbn;
	private String title;
	private int maxCheckoutDay;
	private int numberOfCopy;

	public Book(String isbn, String title, int maxCheckoutDay, int numberOfCopy) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutDay = maxCheckoutDay;
		this.numberOfCopy = numberOfCopy;
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
}
