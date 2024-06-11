package bean.dto;

import java.sql.Clob;

public class Review {
	private int review_id;
	private String isbn;
	private int librarian_id;
	private Clob reviewText;
	private int rating;
	
	public Review(int review_id, String isbn, int librarian_id, Clob reviewText, int rating) {
		super();
		this.review_id = review_id;
		this.isbn = isbn;
		this.librarian_id = librarian_id;
		this.reviewText = reviewText;
		this.rating = rating;
	}



	public int getReview_id() {
		return review_id;
	}



	public int setReview_id(int review_id) {
		return review_id;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public int getLibrarian_id() {
		return librarian_id;
	}



	public void setLibrarian_id(int librarian_id) {
		this.librarian_id = librarian_id;
	}



	public Clob getReviewText() {
		return reviewText;
	}



	public void setReviewText(Clob reviewText) {
		this.reviewText = reviewText;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	@Override
    public String toString() {
        return "Review{" +
                "reviewText='" + reviewText + '\'' +
                '}';
    }
	
}
