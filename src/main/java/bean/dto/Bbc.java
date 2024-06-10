package bean.dto;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bbc {
    private final BooleanProperty checkBox;
    private final StringProperty bookID;
    private final StringProperty isbn;
    private final StringProperty bName;
    private final StringProperty author;
    private final IntegerProperty pYear; // 출판년도
    private final IntegerProperty bCopy; // 권수
    private final ObjectProperty<LocalDate> pDate; // 등록일자
    private final StringProperty libraryName;
    private final StringProperty roomName;
    private final StringProperty bStatus;

    public Bbc(String bookId, String isbn, String bName, String author, int pYear, int bCopy, LocalDate pDate, String libraryName, String roomName, String bStatus) {
        this.checkBox = new SimpleBooleanProperty(false);
        this.bookID = new SimpleStringProperty(bookId);
        this.isbn = new SimpleStringProperty(isbn);
        this.bName = new SimpleStringProperty(bName);
        this.author = new SimpleStringProperty(author);
        this.pYear = new SimpleIntegerProperty(pYear);
        this.bCopy = new SimpleIntegerProperty(bCopy);
        this.pDate = new SimpleObjectProperty<>(pDate);
        this.libraryName = new SimpleStringProperty(libraryName);
        this.roomName = new SimpleStringProperty(roomName);
        this.bStatus = new SimpleStringProperty(bStatus);
    }

    public BooleanProperty checkBoxProperty() {
        return checkBox;
    }

    public StringProperty bookIDProperty() {
        return bookID;
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public StringProperty bNameProperty() {
        return bName;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public IntegerProperty pYearProperty() {
        return pYear;
    }

    public IntegerProperty bCopyProperty() {
        return bCopy;
    }

    public ObjectProperty<LocalDate> pDateProperty() {
        return pDate;
    }

    public StringProperty libraryNameProperty() {
        return libraryName;
    }

    public StringProperty roomNameProperty() {
        return roomName;
    }

    public StringProperty bStatusProperty() {
        return bStatus;
    }
}