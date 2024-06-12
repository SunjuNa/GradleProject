package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.dto.Review;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DatabaseUtil;

public class ReviewDAOImpl implements ReviewDAO{

	@Override
	public ObservableList<Review> selectisbn(String isbn) {
	    String sql = "select review_id, isbn, librarian_id, DBMS_LOB.SUBSTR(reviewtext,4000,1) AS reviewText, rating from review where isbn = ?";
	    ObservableList<Review> reviews = FXCollections.observableArrayList();
	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	
	        pstmt.setString(1, isbn);
	        ResultSet rs = pstmt.executeQuery();
	        if (!rs.isBeforeFirst()) { // ResultSet이 비어 있는지 확인합니다.
	        	System.out.println("No data found for ISBN: " + isbn);
	        	System.out.println("No data found.");
	        	return reviews;
	        }
	        System.out.println(rs.toString());
	        while(rs.next()) {
	        	reviews.add(new Review(rs.getInt("review_id"), rs.getString("isbn"), rs.getInt("librarian_id")
	        			, rs.getString("reviewText"), rs.getInt("rating")
	        			));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return reviews;
	}

	@Override
	public ObservableList<Review> selectById(int librarianId) {
		// TODO Auto-generated method stub
		String sql = "select review_id, isbn, librarian_id, DBMS_LOB.SUBSTR(reviewtext,4000,1) AS reviewText, rating from review where librarian_id = ?";
	    ObservableList<Review> reviews = FXCollections.observableArrayList();
	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	
	        pstmt.setInt(1, librarianId);
	        ResultSet rs = pstmt.executeQuery();
	        if (!rs.isBeforeFirst()) { // ResultSet이 비어 있는지 확인합니다.
	        	System.out.println("No data found for ISBN: " + librarianId);
	        	System.out.println("No data found.");
	        	return reviews;
	        }
	        System.out.println(rs.toString());
	        while(rs.next()) {
	        	reviews.add(new Review(rs.getInt("review_id"), rs.getString("isbn"), rs.getInt("librarian_id")
	        			, rs.getString("reviewText"), rs.getInt("rating")
	        			));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return reviews;
	}

}
