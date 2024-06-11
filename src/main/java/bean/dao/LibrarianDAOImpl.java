package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.dto.Librarian;
import util.DatabaseUtil;

public class LibrarianDAOImpl implements LibrarianDAO{

	@Override
	public Librarian getLibrarianById(int id) {
		// TODO Auto-generated method stub
		Librarian librarian = null;
		String sql = "SELECT * FROM librarian where librarian_id = ?";
		
		try(Connection conn = DatabaseUtil.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
         	ResultSet rs = pstmt.executeQuery();
         	
         	if(rs.next()) {
         		librarian = new Librarian();
         		librarian.setLibrarian_id(rs.getInt("id"));
         		librarian.setL_name(rs.getString("name"));
         		librarian.setL_email(rs.getString("email"));
         		librarian.setL_phone(rs.getString("phone"));
         	}
         	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return librarian;
	}

}
