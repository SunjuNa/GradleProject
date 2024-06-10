package bean.dto;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddBookPro {
	private final IntegerProperty bookID;
    private final StringProperty isbn;
    private final StringProperty bName;
    private final StringProperty author;
    private final IntegerProperty pYear;//출판년도
    private final IntegerProperty bCopy;//권수
    private final ObjectProperty<LocalDate> pDate;//등록일자
    private final StringProperty libraryName;
    private final StringProperty roomName;
    private final StringProperty bStatus;
    
    public AddBookPro(int bookID, String isbn, String bName, String author, int pYear, int bCopy, LocalDate pDate, String libraryName, String roomName, String bStatus) {
    	this.bookID = new SimpleIntegerProperty(bookID);
    	this.isbn=new SimpleStringProperty(isbn);
    	this.bName=new SimpleStringProperty(bName);
    	this.author=new SimpleStringProperty(author);
    	this.pYear= new SimpleIntegerProperty(pYear);
    	this.bCopy = new SimpleIntegerProperty(bCopy);
    	this.pDate = new SimpleObjectProperty<>(pDate);
    	this.libraryName=new SimpleStringProperty(libraryName);
    	this.roomName=new SimpleStringProperty(roomName);
    	this.bStatus=new SimpleStringProperty(bStatus);
    }
    
    public int getBookID() {
    	return bookID.get(); 
    }
    
	public IntegerProperty bookIDProperty() {
		return bookID;
	}
    
	public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

	public String getbName() {
		return bName.get();
	}

	public StringProperty bNameProperty() {
		return bName;
	}
	
	public String getAuthor() {
		return author.get();
	}

	public StringProperty AuthorProperty() {
		return author;
	}
	
	public int getpYear() {
		return pYear.get();
	}
	
	public IntegerProperty pYearProperty() {
		return pYear;
	}
	
	public String getLibraryName() {
		return libraryName.get();
	}
	
	public StringProperty LibraryNameProperty() {
		return libraryName;
	}

	public String getRoomName() {
		return roomName.get();
	}
	
	public StringProperty RoomNameProperty() {
		return roomName;
	}
	
	public String getbStatus() {
		return bStatus.get();
	}
    
	public StringProperty bStatusProperty() {
		return bStatus;
	}
    
	public int getbCopy() {
		return bCopy.get();
	}
	
	public IntegerProperty bCopyProperty() {
		return bCopy;
	}
	
	public LocalDate getPDate() {
		return pDate.get();
	}
	
	public ObjectProperty<LocalDate> pDateProperty(){
		return pDate;
	}
}
