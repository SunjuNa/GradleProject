package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import bean.dto.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DatabaseUtil;

public class BookDAOImpl implements BookDAO{

	@Override
	public ObservableList<Book> getBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		// 태백산맥으로 시험해볼것
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

	@Override
	public String insertBooks(List<Book> books) {
		// TODO Auto-generated method stub
		String sql = "insert into book(isbn, b_name, author, p_year) values (?,?,?,?)";
		try(Connection conn = DatabaseUtil.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(sql)){
			for(Book book : books) {
				pstmt.setString(1, book.getIsbn());
				pstmt.setString(2, book.getBName());
				pstmt.setString(3, book.getAuthor());
				pstmt.setInt(4, book.getPYear());
				pstmt.addBatch(); //Batch update
			}
			pstmt.executeBatch(); //Execute batch update
		}catch(Exception e) {
			e.printStackTrace();
			return "Insert failed: "+ e.getMessage();
		}
		return "insert를 성공했습니다";
	}

}
