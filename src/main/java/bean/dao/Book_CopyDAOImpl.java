package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;

import bean.dto.Book_Copy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DatabaseUtil;

public class Book_CopyDAOImpl implements Book_CopyDAO{

	@Override
	public ObservableList<Book_Copy> getBookCopysByISBN(String isbn) {
		// TODO Auto-generated method stub
		String sql  = "Select bookid, isbn, b_copy as bCopy, p_date as pDate, room_id as roomID, status_id as statusID FROM BOOK_COPY where isbn = ?";
		ObservableList<Book_Copy> bookcopys = FXCollections.observableArrayList();
		try(Connection conn = DatabaseUtil.getConnection();
			    PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, isbn);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					bookcopys.add(new Book_Copy(
							 rs.getString("bookid"),
		                     rs.getString("isbn"),
		                     rs.getInt("bCopy"),
		                     rs.getString("pDate"),
		                     rs.getInt("roomID"),
		                     rs.getInt("statusID")));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return bookcopys;
	}

	@Override
	public String insertBookCopys(List<Book_Copy> bookCopys) {
		// TODO Auto-generated method stub
		String sql = "insert into book_Copy(bookid, isbn, b_copy, p_date, room_id, status_id) values (?,?,?,?,?,?)";
		try(Connection conn = DatabaseUtil.getConnection();
			    PreparedStatement pstmt = conn.prepareStatement(sql)){
			SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
				for(Book_Copy bookCopy : bookCopys) {
					pstmt.setString(1, bookCopy.getBookid());
					pstmt.setString(2, bookCopy.getIsbn());
					pstmt.setInt(3, bookCopy.getB_copy());
					// String을 java.util.Date로 변환
		            java.util.Date utilDate = formatter.parse(bookCopy.getP_date());
		         // java.util.Date를 java.sql.Date로 변환
		            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					pstmt.setDate(4, sqlDate);
					pstmt.setInt(5, bookCopy.getRoom_id()); 
					pstmt.setInt(6, bookCopy.getStatus_id()); 
					pstmt.addBatch(); //Batch update
				}
				pstmt.executeBatch(); //Execute batch update
			}catch(Exception e) {
				e.printStackTrace();
				return "Insert failed: "+ e.getMessage();
			}
		return "bookCopy insert를 성공했습니다";
	}

	@Override
	public String deleteBookCopys(List<Book_Copy> bookCopys) {
		// TODO Auto-generated method stub
		String sql = "delete from book_copy where bookid = ?";
		ObservableList<Book_Copy> bookcopys = FXCollections.observableArrayList();
		try(Connection conn = DatabaseUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			for(Book_Copy bookCopy : bookCopys) {
				pstmt.setString(1, bookCopy.getBookid());
				pstmt.addBatch(); //Batch update
			}
				pstmt.executeBatch();
			}catch(Exception e) {
				e.printStackTrace();
				return "Delete failed: " + e.getMessage();
			}
			
			return "삭제를 시행했습니다";
	}

}
