package bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.dto.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DatabaseUtil;

public class RoomDAOImpl implements RoomDAO{

	@Override
	public ObservableList<Room> getLibraryIDByRoomID(int roomID) {
		// TODO Auto-generated method stub
		String sql = "SELECT library_id as libraryID FROM ROOM WHERE room_id = ?";
		ObservableList<Room> rooms = FXCollections.observableArrayList();
		
		try(Connection conn = DatabaseUtil.getConnection();
			    PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, roomID);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					rooms.add(new Room(
							 rs.getInt("library_id"),
							 rs.getInt("library_id"),
		                     rs.getString("room_id")));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return rooms;
		
	}

}
