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
         		librarian = new Librarian(rs.getInt("librarian_id"), 
         				rs.getString("l_name"), rs.getString("l_email"), rs.getString("l_phone"));
//         		librarian.setLibrarian_id(rs.getInt("librarian_id"));
//         		librarian.setL_name(rs.getString("l_name"));
//         		librarian.setL_email(rs.getString("l_email"));
//         		librarian.setL_phone(rs.getString("l_phone"));

         	}
         	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return librarian;
	}

	@Override
	public String updateLibrarian(Librarian librarian) {
		// TODO Auto-generated method stub
		String sql = "Update Librarian set l_email = ? , l_phone=? where librarian_id = ?";
		try(Connection conn = DatabaseUtil.getConnection();
			    PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, librarian.getL_email());
				pstmt.setString(2, librarian.getL_phone());
				pstmt.setInt(3, librarian.getLibrarian_id());
				pstmt.addBatch(); //Batch update
				pstmt.executeBatch(); //Execute batch update
			}catch(Exception e) {
				e.printStackTrace();
				return "Update failed: "+ e.getMessage();
			}
			return "update를 성공했습니다";
	}

}
