package a4;
import java.util.NoSuchElementException;
import java.util.Arrays;
public class Hotel {
	private String name;
	private Room[] rooms;
	private Reservation[] reservations;
	
	
	public Hotel(String name, Room[] newRooms) {
		this.name = name;
		this.rooms = new Room[newRooms.length];

		for(int i=0; i<newRooms.length; i++) {
			rooms[i] = newRooms[i];
		}
		this.reservations = new Reservation[rooms.length];
	}
	
	private void addReservation(Reservation newReservation) {
		for(int i = 0; i < reservations.length; i++) {
			if(reservations[i] == null) {
				reservations[i] = newReservation;
				return;
			}
		}
	}
		
	private void removeReservation(String name, Room type) throws NoSuchElementException  {
		Room res = type; 
		for(int i = 0; i<this.reservations.length; i++) {
			Reservation byeReservation = this.reservations[i];
			Room resRoom = byeReservation.getRoom();
			if(name.equals(this.reservations[i].getName()) &&
					(res.getType()).equals(resRoom.getType())){
						int index = i;
						this.reservations[index].getRoom().changeAvailability();
						this.reservations[index] = null;
			} else {
				throw new NoSuchElementException("No reservation has been made under this name."); 
			}
		}
	}	


	public void createReservation(String name, Room type1) {
		Room available = Room.findAvailableRoom(this.rooms, type1);
			if(available == null) {
				System.out.println("There is not a room available of this type in the hotel.");
			} else {
			available.changeAvailability();
			Reservation newReservation = new Reservation(available, name);
			addReservation(newReservation);
			System.out.println("Your reservation has been made.");
			System.out.println("\n**************************************************");
		}
	}

	public void cancelReservation(String name, Room roomType) {
		
		try { 
			removeReservation(name, roomType);
			System.out.println("The reservation has been cancelled.");
		} catch (NoSuchElementException e) {
			System.out.println("No Reservation has been made under this name.");
			
		}
	}
	public String printInvoice(String name) {
		double owing = 0.0;
		for(int j = 0; j < reservations.length; j++) {
			if(reservations[j] == null) {
				continue;
			}
				String resName = reservations[j].getName();
				Room resRoom = reservations[j].getRoom();
				double owes = resRoom.getPrice();
				if (resName.equals(name)) {
					owing = owing + owes;
				}
			}
			String invoice; 
			if(owing == 0) {
				invoice = "You do not owe anything.";
			} else {
				invoice = "You currently owe $" + owing + " from all your reservations";
			}
			return invoice;
		}
	 
	public String toString() {
		int doubles = 0;
		int queens = 0;
		int kings = 0;
		
		for (int i = 0; i<this.rooms.length; i++) {
			Room room = this.rooms[i];
			
			boolean availability = room.getAvailability();
			if(availability) {
				if(room.getType().equals("king")) {
					kings++;
				} else if (room.getType().equals("queen")) {
					queens++;
				} else {
					doubles ++;
					
				}
			}
		}
		
	if ((doubles == 0) && (queens == 0) && (kings == 0))	{
		return "There are no rooms available in this hotel at the moment.";
	}
	return "Here is the hotel info \n" + "Hotel name: " + this.name + " \n" + "Available rooms: " + doubles 
			+ " double, " + queens + " queen, " + kings + " king."	;
	}
		

public String getName() {
	return this.name;
	}
}
