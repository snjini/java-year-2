package a4;
import java.util.Scanner;
import java.util.Random;

public class BookingSystem {
	//given code
	private static String[] typeOfRooms = {"double","queen","king"};
  private static Random r = new Random(123);
	
  //returns a random String from the above array. 
  static String getRandomType(){
      int index = r.nextInt(typeOfRooms.length);
      return typeOfRooms[index];
  }
  //returns a random number of rooms between 5 and 50.
  private static int getRandomNumberOfRooms(){
      return r.nextInt(50)+1;
  }
 //End of provided code. 
  
 //helper method for menu 
  private static boolean menu(Hotel newHotel) throws IndexOutOfBoundsException, NullPointerException
  { 	
System.out.println("Please choose one of the following options: ");
System.out.println("1) Make a reservation");
System.out.println("2) Cancel a reservation");
System.out.println("3) See an invoice");
System.out.println("4) See hotel info");
System.out.println("5) Exit the Booking System");
System.out.println("***************************************");
Scanner read = new Scanner(System.in);
int command = read.nextInt();
read.nextLine();

//Make a  reservation
	if(command == 1) {
		System.out.println("Please enter your name:");
		read = new Scanner(System.in);
		String resName = read.nextLine();
		System.out.println("What type of room would you like to reserve?");
		String typeWanted;
		read = new Scanner(System.in);
		typeWanted = read.nextLine();
		newHotel.createReservation(resName, new Room(typeWanted));
		
	return true;
 }	
	//Cancel a reservation
	else if(command == 2) {
		System.out.println("Please enter the name you used to make the reservation: ");
		read = new Scanner(System.in);
		String resName = read.nextLine();
		try {
		System.out.println("What type of room did you reserve?");
		read = new Scanner(System.in);
		String type = read.nextLine();
		newHotel.cancelReservation(resName, new Room(type));
		}
		catch (IllegalArgumentException e ) {
			System.out.println("Invalid room type");
			System.out.println("What type of room did you reserve?");
			String type = read.nextLine();
			newHotel.cancelReservation(resName, new Room(type));
			
		}
		return true;

	}
//See an invoice
	else if(command == 3) {
		System.out.println("Please enter your name:");
		String resName = read.nextLine();
		System.out.println(newHotel.printInvoice(resName));
		System.out.println("\n**************************************************");
return true;
}
//See hotel info
	else if(command == 4) {
		System.out.println("\n**************************************************");
		
		System.out.println(newHotel.toString());
		System.out.println("\n**************************************************");
		return true;
}
	//Exit the booking system
	else if(command == 5) {
		System.out.println("It was a pleasure doing business with you!");
		return false;
	}
		
	else {
		throw new IndexOutOfBoundsException();

 }
}
 
//main method 
  public static void main(String[] args){
      //Sasha	Njini
      //260783102
 
  	System.out.println("Please enter the name of the hotel you would like to book: ");
  String hotelName = new Scanner(System.in).nextLine();
  	
  	int numberOfRooms = getRandomNumberOfRooms();
  	Room[] room = new Room[numberOfRooms];
  	for(int i = 0; i < numberOfRooms; i++) {
  		String roomType = getRandomType();
  		room[i] = new Room(roomType);  	
  		}
	 Hotel newHotel = new Hotel(hotelName, room);
	 boolean x = true;
	

	System.out.println("\n**************************************************");
	while(x) {
		try { 
			System.out.println("Welcome to " + newHotel.getName() + ".\n" );
			x = menu(newHotel);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Inavlid entry, please enter 1, 2, 3, 4 or 5");
			System.out.println("\n**************************************************");
		}
		catch(NullPointerException e) {
			System.out.println("There are no reservations at this time, please enter another number");
		 System.out.println("\n**************************************************");
		}
		catch(IllegalArgumentException e) {
			System.out.println("Invalid type entered");
			System.out.println("\n**************************************************");
		}
	}
 }
}

  
