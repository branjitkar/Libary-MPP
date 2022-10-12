package business;

public class BookCopy {
	private int copyId;
	private boolean isAvailable;

	public BookCopy(int copyId) {
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

}
