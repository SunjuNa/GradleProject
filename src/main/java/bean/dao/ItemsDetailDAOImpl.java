package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.dto.ItemsDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DatabaseUtil;

public class ItemsDetailDAOImpl implements ItemsDetailDAO{

	@Override
	public ObservableList<ItemsDetail> getItemsDetailByISBN(String isbn) {
		// TODO Auto-generated method stub
		String sql = "SELECT "
						+ "C.BOOKID AS bookID, "
						+ "C.ISBN AS isbn, "
						+ "B.B_NAME AS bName, "
						+ "B.AUTHOR as author, "
						+ "B.P_YEAR as pYear, "
						+ "F.LIBRARY_NAME as libraryName, "
						+ "F.ROOM_NAME as roomName, "
						+ "S.B_STATUS as bStatus "
				+ "FROM "
				+ "( "
				+ "SELECT "
				+ 		"r.room_id, "
				+ 		"r.library_id, "
				+ 		"r.room_name, "
				+ 		"l.library_name "
				+ "FROM  ROOM R "
				+ "FULL OUTER JOIN "
				+ "LIBRARY L ON R.LIBRARY_ID = L.LIBRARY_ID "
				+ ") F "
				+ "JOIN "
				+ 		"BOOK_COPY C ON C.ROOM_ID = F.ROOM_ID AND C.ISBN = ? "
				+ "JOIN "
				+ 		"BOOK B ON B.ISBN = C.ISBN "
				+ "LEFT JOIN  "
				+ 		"BOOKSTATUS S ON C.STATUS_ID = S.STATUS_ID "
				+ "WHERE  C.ISBN = ?";
		ObservableList<ItemsDetail> itemsDetails = FXCollections.observableArrayList();
		try(Connection conn = DatabaseUtil.getConnection();
			    PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, isbn);
				pstmt.setString(2, isbn);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					itemsDetails.add(new ItemsDetail(
							 rs.getInt("bookID"),
							 rs.getString("isbn"),
		                     rs.getString("bName"),
		                     rs.getString("author"),
		                     rs.getInt("pYear"),
		                     rs.getString("libraryName"),
		                     rs.getString("roomName"),
		                     rs.getString("bStatus")));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return itemsDetails;
	}

	@Override
	public ObservableList<ItemsDetail> getItemsDetailByAuthor(String author) {
	    String sql = "SELECT "
	                    + "C.BOOKID AS bookID, "
	                    + "B.ISBN AS isbn, "
	                    + "B.B_NAME AS bName, "
	                    + "B.AUTHOR as author, "
	                    + "B.P_YEAR as pYear, "
	                    + "L.LIBRARY_NAME as libraryName, "
	                    + "R.ROOM_NAME as roomName, "
	                    + "S.B_STATUS as bStatus "
	                + "FROM BOOK_COPY C "
	                    + "JOIN BOOK B ON C.ISBN = B.ISBN "
	                    + "JOIN BOOKSTATUS S ON C.STATUS_ID = S.STATUS_ID "
	                    + "JOIN ROOM R ON C.ROOM_ID = R.ROOM_ID "
	                    + "JOIN LIBRARY L ON R.LIBRARY_ID = L.LIBRARY_ID "
	                + "WHERE B.AUTHOR LIKE ?";
	    ObservableList<ItemsDetail> itemsDetails = FXCollections.observableArrayList();
	    try(Connection conn = DatabaseUtil.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql)){
	        pstmt.setString(1, "%" + author + "%");
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()) {
	            itemsDetails.add(new ItemsDetail(
	                rs.getInt("bookID"),
	                rs.getString("isbn"),
	                rs.getString("bName"),
	                rs.getString("author"),
	                rs.getInt("pYear"),
	                rs.getString("libraryName"),
	                rs.getString("roomName"),
	                rs.getString("bStatus")));
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	    return itemsDetails;
	}

	@Override
	public ObservableList<ItemsDetail> getItemsDetailByB_name(String b_name) {
	    String sql = "SELECT "
	                    + "C.BOOKID AS bookID, "
	                    + "B.ISBN AS isbn, "
	                    + "B.B_NAME AS bName, "
	                    + "B.AUTHOR as author, "
	                    + "B.P_YEAR as pYear, "
	                    + "L.LIBRARY_NAME as libraryName, "
	                    + "R.ROOM_NAME as roomName, "
	                    + "S.B_STATUS as bStatus "
	                + "FROM BOOK_COPY C "
	                    + "JOIN BOOK B ON C.ISBN = B.ISBN "
	                    + "JOIN BOOKSTATUS S ON C.STATUS_ID = S.STATUS_ID "
	                    + "JOIN ROOM R ON C.ROOM_ID = R.ROOM_ID "
	                    + "JOIN LIBRARY L ON R.LIBRARY_ID = L.LIBRARY_ID "
	                + "WHERE B.B_NAME LIKE ?";
	    ObservableList<ItemsDetail> itemsDetails = FXCollections.observableArrayList();
	    try(Connection conn = DatabaseUtil.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql)){
	        pstmt.setString(1, "%" + b_name + "%");
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()) {
	            itemsDetails.add(new ItemsDetail(
	                rs.getInt("bookID"),
	                rs.getString("isbn"),
	                rs.getString("bName"),
	                rs.getString("author"),
	                rs.getInt("pYear"),
	                rs.getString("libraryName"),
	                rs.getString("roomName"),
	                rs.getString("bStatus")));
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	    return itemsDetails;
	}

	@Override
	public ObservableList<ItemsDetail> getItemsDetailByB_nameOrAuthor(String nameOrAuthor) {
		// TODO Auto-generated method stub
		String sql = "SELECT "
		                + "C.BOOKID AS bookID, "
		                + "B.ISBN AS isbn, "
		                + "B.B_NAME AS bName, "
		                + "B.AUTHOR as author, "
		                + "B.P_YEAR as pYear, "
		                + "L.LIBRARY_NAME as libraryName, "
		                + "R.ROOM_NAME as roomName, "
		                + "S.B_STATUS as bStatus "
		            + "FROM BOOK_COPY C "
		                + "JOIN BOOK B ON C.ISBN = B.ISBN "
		                + "JOIN BOOKSTATUS S ON C.STATUS_ID = S.STATUS_ID "
		                + "JOIN ROOM R ON C.ROOM_ID = R.ROOM_ID "
		                + "JOIN LIBRARY L ON R.LIBRARY_ID = L.LIBRARY_ID "
		            + "WHERE B.AUTHOR LIKE ? OR B.B_NAME LIKE ?";
		ObservableList<ItemsDetail> itemsDetails = FXCollections.observableArrayList();
		try(Connection conn = DatabaseUtil.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(sql)){
		    pstmt.setString(1, "%" + nameOrAuthor + "%");
		    pstmt.setString(2, "%" + nameOrAuthor + "%");
		    ResultSet rs = pstmt.executeQuery();
		    while(rs.next()) {
		        itemsDetails.add(new ItemsDetail(
		            rs.getInt("bookID"),
		            rs.getString("isbn"),
		            rs.getString("bName"),
		            rs.getString("author"),
		            rs.getInt("pYear"),
		            rs.getString("libraryName"),
		            rs.getString("roomName"),
		            rs.getString("bStatus")));
		    }
		}catch(Exception e) {
		    e.printStackTrace();
		}
		
		return itemsDetails;
			}

}
