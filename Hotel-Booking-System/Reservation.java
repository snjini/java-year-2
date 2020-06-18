package a4;

public class Reservation {
	private String name;
	private Room roomReserved;
	
		
	public Reservation(Room room, String name) {
		this.name = name;
		this.roomReserved = room;
	  }
		public String getName() {
			return this.name;
		}
		public Room getRoom() {
			return this.roomReserved;
	  }
}
