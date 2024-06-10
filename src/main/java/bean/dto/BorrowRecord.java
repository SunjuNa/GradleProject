package bean.dto;

import java.sql.Date;

public class BorrowRecord {
    private String borrowID;
    private String bookID;
    private String userID;
    private Date borrowDate;
    private Date returnDate;
    private String status;

    public BorrowRecord(String borrowID, String bookID, String userID, Date borrowDate, Date returnDate, String status) {
        this.borrowID = borrowID;
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters and Setters
    // ...
}
