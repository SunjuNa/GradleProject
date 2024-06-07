package bean.dao;

import bean.dto.Book;
import javafx.collections.ObservableList;

public interface BookDAO {
	ObservableList<Book> getBooksByAuthor(String author);
	ObservableList<Book> getBooksByB_name(String b_name);
}
