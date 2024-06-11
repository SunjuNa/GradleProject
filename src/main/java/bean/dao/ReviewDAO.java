package bean.dao;

import bean.dto.Review;
import javafx.collections.ObservableList;

public interface ReviewDAO {
	ObservableList<Review> selectisbn(String isbn);
}
