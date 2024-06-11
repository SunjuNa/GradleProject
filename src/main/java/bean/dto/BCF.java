package bean.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class BCF {
	private CheckBox checkbox;
    private final SimpleStringProperty bookId;
    private final SimpleStringProperty isbn;
    private final SimpleStringProperty bName;
    private final SimpleStringProperty author;
    private final SimpleIntegerProperty pYear;
    private final SimpleIntegerProperty bCopy;
    private final SimpleStringProperty pDate;
    private final SimpleStringProperty libraryName;
    private final SimpleStringProperty roomName;
    private final SimpleStringProperty bStatus;
    
	public BCF(String bookId, String isbn, String bName, String author, Integer pYear, Integer bCopy, String pDate
			,String libraryName, String roomName, String bStatus ) {
		super();
		this.checkbox = new CheckBox();
		this.bookId = new SimpleStringProperty(bookId);
		this.isbn = new SimpleStringProperty(isbn);
		this.bName = new SimpleStringProperty(bName);
		this.author = new SimpleStringProperty(author);
		this.pYear = new SimpleIntegerProperty(pYear);
		this.bCopy = new SimpleIntegerProperty(bCopy);
		this.pDate = new SimpleStringProperty(pDate);
		this.libraryName = new SimpleStringProperty(libraryName);
		this.roomName = new SimpleStringProperty(roomName);
		this.bStatus = new SimpleStringProperty(bStatus);
	}

	public String getBookId() {
		return bookId.get();
	}
	public void setBookId(String bId) {
		bookId.set(bId);
	}
	public String getIsbn() {
		return isbn.get();
	}
    public void setIsbn(String isb) {
    	isbn.set(isb);
    }
    public String getBName() {
    	return bName.get();
    }
    public void setBName(String bN) {
    	bName.set(bN);
    }
    
    
    public String getAuthor() {
    	return author.get();
    }
    public void setAuthor(String au) {
    	author.set(au);
    }
    
    
    
    public Integer getPYear() {
    	return pYear.get();
    }
    public void setPYear(int pY) {
    	pYear.set(pY);
    }
    
    public Integer getBCopy() {
    	return bCopy.get();
    }
    public void setBCopy(int bC) {
    	bCopy.set(bC);
    }
    
    public String getPDate() {
    	return pDate.get();
    }
    public void setPDate(String pD) {
    	pDate.set(pD);
    }
    public String getLibraryName() {
    	return libraryName.get();
    }
    public void setLibraryName(String lN) {
    	libraryName.set(lN);
    }
    public String getRoomName() {
    	return roomName.get();
    }
    public void setRoomName(String rN) {
    	roomName.set(rN);
    }
    public String getBStatus() {
    	return bStatus.get();
    }
    public void bStatus(String bS) {
    	bStatus.set(bS);
    }
    
    public CheckBox getCheckbox() {
    	return checkbox;
    }
    public void setCheckBox(CheckBox checkbox) {
    	this.checkbox = checkbox;
    }
}
