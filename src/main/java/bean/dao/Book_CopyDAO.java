package bean.dao;

import java.util.List;

import bean.dto.Book_Copy;
import javafx.collections.ObservableList;

public interface Book_CopyDAO {
	ObservableList<Book_Copy> getBookCopysByISBN(String isbn);
	String insertBookCopys(List<Book_Copy> bookCopys);
	String deleteBookCopys(List<Book_Copy> bookCopys);
}
