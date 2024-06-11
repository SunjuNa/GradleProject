package bean.dto;

import javafx.scene.control.CheckBox;

public class ItemsDetail {
	private CheckBox checkbox;
	private int bookID;
	private String isbn;
	private String bName;
	private String author;
	private int pYear;
	private String libraryName;
	private String roomName;
	private String bStatus;
	
	public ItemsDetail(int bookID, String isbn, String bName, String author, int pYear, String libraryName, String roomName,
			String bStatus) {
		super();
		this.checkbox = new CheckBox();
		this.bookID = bookID ;
		this.isbn=isbn;
		this.bName = bName;
		this.author = author;
		this.pYear = pYear;
		this.libraryName = libraryName;
		this.roomName = roomName;
		this.bStatus = bStatus;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ItemsDetail{bookid='"+bookID+'\''+", isbn="+isbn+
				",bName= "+bName+", author="+author+
				",p_year="+pYear+", libraryName="+libraryName+
				",roomName= "+roomName+", bStatus= "+bStatus+'}';
	}
	
	//getter setter
	public int getBookID() {
		return bookID;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBName() {
		return bName;
	}

	public void setBName(String bName) {
		this.bName = bName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPYear() {
		return pYear;
	}

	public void setPYear(int pYear) {
		this.pYear = pYear;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getBStatus() {
		return bStatus;
	}

	public void setBStatus(String bStatus) {
		this.bStatus = bStatus;
	}
	
	public CheckBox getCheckbox() {
    	return checkbox;
    }
    public void setCheckBox(CheckBox checkbox) {
    	this.checkbox = checkbox;
    }
	
}
