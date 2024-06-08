package bean.dto;

public class Room {
	private int room_id;
	private int library_id;
	private String room_name;
	
	public Room(int room_id, int library_id, String room_name) {
		super();
		this.room_id = room_id;
		this.library_id = library_id;
		this.room_name = room_name;
	}
	
	
}
