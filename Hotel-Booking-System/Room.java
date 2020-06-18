package a4;

public class Room {

	private String type;
	private double price;
	private boolean availability;
	
	 
	public Room(String type) throws IllegalArgumentException {
		this.availability = true;
		this.type = type;
		
		if (type.equals("double")) {
			this.type = "double";
			this.price = 90.0;
		}
		else if (type.equals("queen")) {
	  		this.type = "queen";
	  		this.price = 110.0;
		}
		else if (type.equals("king")) {
			this.type = "king";
			this.price = 150.0;

		} else {
			throw new IllegalArgumentException("No type of that room can be created.");
		}	
	}
			public String getType() {
				return this.type;
			}
			public double getPrice() {
				return this.price;	
			}
			public boolean getAvailability() {
				return availability;
			}
			public void changeAvailability() {
				this.availability = !availability;
				
			}
			public static Room findAvailableRoom(Room[] rooms, Room type) {
				for (int i = 0; i < rooms.length; i++) {
						if((rooms[i].getType()).equals(type.getType()) && rooms[i].getAvailability()) {
				
							return rooms[i];
						}
					}
			return null;
	}
}
