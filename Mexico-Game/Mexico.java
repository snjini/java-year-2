public class Mexico {
	
	//main method calls playMexico
	public static void main(String[] args) {
		/*
		 Sasha Njini 260783102
		*/
		playMexico(50.0, 10.0);
		
	  }
	  
	//creating a dice roll
	  public static int diceRoll(){
	    int roll = (int) ((Math.random()*6)+1);
	    return roll;
	  }  
	  
	//multiplying the higher roll by ten to get a two-digit number then adding it to the lower roll
	  public static int getScore(int roll1, int roll2){
	    if(roll1 >= roll2) {
	      int score = roll1*10 + roll2;
	      return score;
	    } else {
	      int score = roll2*10 + roll1;
	      return score;
	    }    
 }
	  
	//displaying the roll values of a player and then combining the values to display the overall score achieved  
	  public static int playOneRound(String Name){
	    int roll1 = diceRoll();
	    int roll2 = diceRoll();
	    int score = getScore(roll1, roll2);
	    System.out.println(Name + " rolled: " + roll1 + " " + roll2); 
	    	System.out.println(Name + "'s score is: " + score);
	    return score;
	    }

	  /*series of conditional statements to compare Guilia and 
	  David's scores using the rules of Mexico
	  */
	  public static String getWinner(int score1, int score2) {
	  String winner = "";
	  
	  if(score1 == score2){
	      return "tie";
	    } 
	    else if(score1 == 21){
	      return "Giulia";
	    } 
	    else if(score2 == 21){
	      return "David";
	    } 
	    if(score1%11 == 0){
	      if(score2%11 != 0 || score2 < score1){
	        return "Giulia";
	    }  
	   } 
	    else if(score2%11 == 0){
	      if(score1%11 != 0 || score1 < score2){
	        return "David";
	    } 
	   } 
	    if(score1 > score2){
	      return "Giulia";
	    } 
	    else if(score2 > score1){
	      return "David";
	    }
	  		return winner;
}  
	  
//boolean method to state whether or not players have the correct amounts of money in order to play 
	public static boolean canPlay(double buyIn, double bet) {
		if( buyIn > 0 && bet <= buyIn && buyIn%bet ==0) {

			return true;
		} else {
			return false;	
		}
	}	
	
	//method to actually simulate the game Mexico which is called in the main method
	public static void playMexico(double buyIn, double bet) {
			int round = 1;
			double guiliasHand = buyIn;
			double davidsHand = buyIn;
			if(canPlay(buyIn, bet) == false) {
				System.out.println("Insufficient funds! The game cannot be played!");
		
		} else {
				while(guiliasHand > 0 && davidsHand > 0) {
					System.out.println("\nRound " + round + "\n");
					String winner = getWinner(playOneRound("Giulia"), (playOneRound("David")));
					
					if(winner.equals("Giulia")) {
						davidsHand -= bet;
						System.out.println("Giulia won this round!");
					}
					if(winner.equals("David")) {
						guiliasHand -= bet;
						System.out.println("David won this round!");
					}
					if(winner.equals("tie")) {
						System.out.println("It's a tie! Roll again!");
					}
					round++;
				}
	//ending the game and declaring the winner
	    if(guiliasHand == 0) {
				System.out.println("\nGame over. David Won!");
			}
			if(davidsHand == 0) {
				System.out.println("\nGame over. Giulia Won!");
				
			}
		}
	}
}

	


	  			
	  		
	  		
	  		
	  

