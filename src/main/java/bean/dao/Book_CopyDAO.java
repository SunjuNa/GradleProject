package bean.dao;

import bean.dto.Book_Copy;
import javafx.collections.ObservableList;

public interface Book_CopyDAO {
	ObservableList<Book_Copy> getBookCopysByISBN(String isbn);
}
