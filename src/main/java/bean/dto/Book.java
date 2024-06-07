package bean.dto;

public class Book {
	private String isbn;
    private String bName;
    private String author;
    private int pYear;

    public Book(String isbn, String bName, String author, int pYear) {
        this.isbn = isbn;
        this.bName = bName;
        this.author = author;
        this.pYear = pYear;
    }
    
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Book{" +
		        "isbn='" + isbn + '\'' +
		        ", b_name='" + bName + '\'' +
		        ", author='" + author + '\'' +
		        ", P_Year=" + pYear +
		        '}';
	}

	// Getters
    public String getIsbn() {
        return isbn;
    }

    public String getBName() {
        return bName;
    }

    public String getAuthor() {
        return author;
    }

    public int getPYear() {
        return pYear;
    }

    // Setters
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBName(String bName) {
        this.bName = bName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPYear(int pYear) {
        this.pYear = pYear;
    }
	
}
