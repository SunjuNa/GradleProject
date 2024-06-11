package bean.dao;

import java.util.List;

import bean.dto.Book;
import javafx.collections.ObservableList;

public interface BookDAO {
	ObservableList<Book> getBooksByAuthor(String author);
	ObservableList<Book> getBooksByB_name(String b_name);
	String insertBooks(List<Book> books);
	String updateBooks(Book book);
}
