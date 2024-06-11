package bean.dao;

import bean.dto.BookStatus;
import javafx.collections.ObservableList;

public interface BookStatusDAO {
	ObservableList<BookStatus> getb_statusByStatusID(int status_id);
}
