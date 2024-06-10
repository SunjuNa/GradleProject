package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import bean.dto.BorrowRecord;
import util.DatabaseUtil;

/**
 * @author ps202203
 */
public class BorrowRecordDAOImpl implements BorrowRecordDAO {

	//반납된 도서
    @Override
    public int countReturnsByDate(Date date) {
        String sql = "SELECT COUNT(*) AS return_count FROM BORROWRECORD WHERE RETURN_DATE = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("return_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //대출 현황
    @Override
    public int countBorrowsByDate(Date date) {
        String sql = "SELECT COUNT(*) AS borrowed_books_count FROM BORROWRECORD WHERE BORROW_DATE = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("borrowed_books_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //인기 작가
    @Override
    public String getPopularAuthor(Date date) {
        String popularAuthor = null;
        String sql = "SELECT AUTHOR FROM (SELECT B.AUTHOR, COUNT(*) AS B_COUNT FROM BORROWRECORD BR JOIN BOOK_COPY BC ON BR.BOOKID = BC.BOOKID JOIN BOOK B ON BC.ISBN = B.ISBN WHERE BR.BORROW_DATE = ? GROUP BY B.AUTHOR ORDER BY COUNT(*) DESC) WHERE ROWNUM = 1";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, date);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    popularAuthor = rs.getString("AUTHOR");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return popularAuthor;
    }

    //인기 도서
    @Override
    public String getPopularBook(Date date) {
        String popularBook = null;
        String sql = "SELECT B.B_NAME FROM (SELECT BC.ISBN, COUNT(*) AS B_COUNT FROM BORROWRECORD BR JOIN BOOK_COPY BC ON BR.BOOKID = BC.BOOKID WHERE BR.BORROW_DATE = ? GROUP BY BC.ISBN ORDER BY COUNT(*) DESC) BR_COUNT JOIN BOOK B ON BR_COUNT.ISBN = B.ISBN WHERE ROWNUM = 1";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, date);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    popularBook = rs.getString("B_NAME");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return popularBook;
    }
	

	/*
	 * @Override public ObservableList<BorrowRecord> getAllBorrowRecords() { String
	 * sql = "SELECT * FROM BORROWRECORD"; ObservableList<BorrowRecord> list =
	 * FXCollections.observableArrayList(); try (Connection conn =
	 * DatabaseUtil.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
	 * 
	 * while (rs.next()) { BorrowRecord record = new BorrowRecord(
	 * rs.getString("borrowID"), rs.getString("bookID"), rs.getString("userID"),
	 * rs.getDate("borrowDate"), rs.getDate("returnDate"), rs.getString("status") );
	 * list.add(record); } } catch (Exception e) { e.printStackTrace(); } return
	 * list; }
	 */
}
