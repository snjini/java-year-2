
package a4Game;

import java.io.*;
public class Game {
	public static int play(InputStreamReader input) throws NumberFormatException, IOException{
		BufferedReader keyboard = new BufferedReader(input);
		Configuration c = new Configuration();
		int columnPlayed = 3; int player;

		// first move for player 1 (played by computer) : in the middle of the grid
		c.addDisk(firstMovePlayer1(), 1);
		int nbTurn = 1;

		while (nbTurn < 42){ // maximum of turns allowed by the size of the grid
			player = nbTurn %2 + 1;
			if (player == 2){
				columnPlayed = getNextMove(keyboard, c, 2);

			}
			if (player == 1){
				columnPlayed = movePlayer1(columnPlayed, c);

			}
			System.out.println(columnPlayed);
			
			c.addDisk(columnPlayed, player);
			if (c.isWinning(columnPlayed, player)){
				c.print();
				System.out.println("Congrats to player " + player + " !");
				return(player);
			}
			nbTurn++;
		}
		return -1;
	}
	public static boolean isValid(int num) {
		if(num < 7 && num >-1) {
			return true;
		}
		return false;
	}
	public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
		c.print();
		System.out.println("Your turn! Please type the column number where you want to put your disk.");
		int column = 0;
		while (true) { //creating a while loop to keep asking the player to make their move
		try {
		column = Integer.parseInt(keyboard.readLine()); //storing the column (from their move) in a variable
		if (!(c.available[column] ==6)) { //if that column has no more room, break out to the next statement
		break;
		}
		//keep asking the user to make a valid move until one is inputted
		System.out.println("Invalid move! Please type the column number where you want to put your disk.");
		} catch (IOException e) {
		//if an exception is caught, print this:
		System.out.println("Uh oh, something went wrong!!");
		}
		}
		return column; //returning the column number from the user
		}
	public static int firstMovePlayer1 (){

		return 3;
	}

	public static int movePlayer1 (int columnPlayed2, Configuration c) {
		//creating two ints corresponding to the column if they can win the next round
		int win1 = c.canWinNextRound(1);
		int win2 = c.canWinTwoTurns(1);
		//if they can win,  return the column number
		if(win1!=-1) 
			return win1;
		if(win2!=-1)
			return win2;
		if(c.available[columnPlayed2]<6)
			return columnPlayed2;
		for(int i=1; i<15; i++){
		int toAdd = 0;
		if(i%2 == 1) toAdd = i*-1;
		else toAdd = i;
		columnPlayed2+=toAdd;
		if(columnPlayed2>=0 && columnPlayed2<7 &&c.available[columnPlayed2]<6) 
			return columnPlayed2;
		}
		return 0;
		}
	}