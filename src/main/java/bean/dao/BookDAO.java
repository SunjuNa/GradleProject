package bean.dao;

import java.util.List;

import bean.dto.Book;

public interface BookDAO {
	List<Book> getBooksByAuthor(String author);
	List<Book> getBooksByB_name(String b_name);
}
