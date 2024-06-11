package bean.dao;

import bean.dto.Room;
import javafx.collections.ObservableList;

public interface RoomDAO {
	ObservableList<Room> getLibraryIDByRoomID(int roomID);
}
