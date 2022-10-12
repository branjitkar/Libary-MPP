package business;

public class BookCopy {
	private Book book;
	private int copyId;
	private boolean isAvailable;

	public BookCopy(Book book, int copyId) {
		this.book = book;
		this.copyId = copyId;
		this.isAvailable = true;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getCopyId() {
		return copyId;
	}

	public Book getBook() {
		return book;
	}

}
