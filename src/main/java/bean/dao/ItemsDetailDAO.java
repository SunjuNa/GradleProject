package bean.dao;

import bean.dto.ItemsDetail;
import javafx.collections.ObservableList;

public interface ItemsDetailDAO {
	ObservableList<ItemsDetail> getItemsDetailByISBN(String isbn);
	ObservableList<ItemsDetail> getItemsDetailByAuthor(String author);
	ObservableList<ItemsDetail> getItemsDetailByB_name(String b_name);
	ObservableList<ItemsDetail> getItemsDetailByB_nameOrAuthor(String nameOrAuthor);
}
