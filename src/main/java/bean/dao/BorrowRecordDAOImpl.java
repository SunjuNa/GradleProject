package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import bean.dto.BorrowRecord;
import util.DatabaseUtil;

public class BorrowRecordDAOImpl implements BorrowRecordDAO {

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
