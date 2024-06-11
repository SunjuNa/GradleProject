package bean.dto;

public class Book_Copy {
	private String bookid;
	private String isbn;
	private int b_copy;
	private String p_date;
	private int room_id;
	private int status_id;
	
	public Book_Copy(String bookid, String isbn, int b_copy, String p_date, int room_id, int status_id) {
		super();
		this.bookid = bookid;
		this.isbn = isbn;
		this.b_copy = b_copy;
		this.p_date = p_date;
		this.room_id = room_id;
		this.status_id = status_id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Book_copy{ bookid='"+bookid+'\''+
				",isbn="+isbn+", b_copy="+b_copy+
				",p_date="+p_date+", room_id="+room_id+
				",room_id="+room_id+", status_id="+status_id+'}';
	}



	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getB_copy() {
		return b_copy;
	}
	public void setB_copy(int b_copy) {
		this.b_copy = b_copy;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	
	
}
