package bean.dao;

import bean.dto.ItemsDetail;
import javafx.collections.ObservableList;

public interface ItemsDetailDAO {
	ObservableList<ItemsDetail> getItemsDetailByISBN(String isbn);
}
