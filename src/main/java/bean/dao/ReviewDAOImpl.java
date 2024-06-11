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
	    String sql = "select * from review where isbn = ?";
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
	        			, rs.getClob("reviewText"), rs.getInt("rating")
	        			));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return reviews;
	}

}
