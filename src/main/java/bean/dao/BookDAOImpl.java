package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.dto.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DatabaseUtil;

public class BookDAOImpl implements BookDAO{

	@Override
	public ObservableList<Book> getBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT isbn, b_name as bName, author, p_year as pYear FROM Book where author LIKE ? ";
		ObservableList<Book> books = FXCollections.observableArrayList();
		try(Connection conn = DatabaseUtil.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%"+author+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				books.add(new Book(
						 rs.getString("isbn"),
	                     rs.getString("bName"),
	                     rs.getString("author"),
	                     rs.getInt("pYear")));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return books;
	}

	@Override
	public ObservableList<Book> getBooksByB_name(String b_name) {
		// TODO Auto-generated method stub
		String sql = "SELECT isbn, b_name as bName, author, p_year as pYear FROM Book where b_name LIKE ? ";
		ObservableList<Book> books = FXCollections.observableArrayList();
		try(Connection conn = DatabaseUtil.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%"+b_name+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				books.add(new Book(
						 rs.getString("isbn"),
	                     rs.getString("bName"),
	                     rs.getString("author"),
	                     rs.getInt("pYear")));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return books;
	}

}
