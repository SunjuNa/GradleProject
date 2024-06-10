package bean.dao;

import javafx.collections.ObservableList;

import java.sql.Date;

import bean.dto.BorrowRecord;

/**
 * @author ps202203
 */
public interface BorrowRecordDAO {
	// 반납된 도서, 대출 현황
    int countReturnsByDate(Date date);
    int countBorrowsByDate(Date date);
    //ObservableList<BorrowRecord> getAllBorrowRecords();
    
    //인기 작가, 인기 도서
    String getPopularAuthor(Date date);
    String getPopularBook(Date date);
    
    
}
