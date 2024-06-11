package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.dto.BookStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DatabaseUtil;

public class BookStatusDAOImpl implements BookStatusDAO{

	@Override
	public ObservableList<BookStatus> getb_statusByStatusID(int status_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT b_status as bStatus FROM BOOKSTATUS where status_id = ?";
		ObservableList<BookStatus> bookStatusCouple = FXCollections.observableArrayList();
		
		try(Connection conn = DatabaseUtil.getConnection();
			    PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, status_id);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					bookStatusCouple.add(new BookStatus(
							 rs.getInt("status_id"),
		                     rs.getString("b_status")));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return bookStatusCouple;
	}

}
