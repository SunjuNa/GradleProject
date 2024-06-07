package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		                     rs.getDate("pDate"),
		                     rs.getInt("roomID"),
		                     rs.getInt("statusID")));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return bookcopys;
	}

}
