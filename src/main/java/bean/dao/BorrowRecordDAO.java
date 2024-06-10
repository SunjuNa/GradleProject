package bean.dao;

import javafx.collections.ObservableList;
import bean.dto.BorrowRecord;

public interface BorrowRecordDAO {
    int countReturnsByDate(java.sql.Date date);
    int countBorrowsByDate(java.sql.Date date);
    //ObservableList<BorrowRecord> getAllBorrowRecords();
}
