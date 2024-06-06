package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.dto.Book;
import javafx.scene.control.Alert;
import util.DatabaseUtil;

public class BookDAOImpl implements BookDAO{

	@Override
	public List<Book> getBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM Book where author = ?";
		
		try(Connection conn = DatabaseUtil.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, author);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//Resultset에서 각 열의 데이터를 읽어옵니다
				String isbn = rs.getString("isbn");
				String dbauthor = rs.getString("author");
				int p_year = rs.getInt("p_year");
				String b_name = rs.getString("b_name");
				//읽어온 데이터로 book객체를 생성합니다.
				Book book = new Book();
				book.setAuthor(dbauthor);
				book.setB_name(b_name);
				book.setIsbn(isbn);
				book.setP_Year(p_year);
				books.add(book);
				
			}
			else {
				//키워드에 대한 정보가 없는 경우
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Keyword Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No information found for the keyword" );
                alert.showAndWait();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return books;
	}

	@Override
	public List<Book> getBooksByB_name(String b_name) {
		// TODO Auto-generated method stub
		return null;
	}

}
