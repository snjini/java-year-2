package a4Game;

public class Configuration {
	public int[][] board;
	public int[] available;
	boolean spaceLeft;
	
	public Configuration(){
		board = new int[7][6];
		available = new int[7];
		spaceLeft = true;
	}
	
	public void print(){
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++){
			System.out.print("|");
			for (int j = 0; j < 7; j++){
				if (board[j][5-i] == 0){
					System.out.print("   |");
				}
				else{
					System.out.print(" "+ board[j][5-i]+" |");
				}
			}
			System.out.println();
		}
	}
	
	public void addDisk (int index, int player){
		// ADD YOUR CODE HERE
	
	this.board[index][available[index]] = player;
			
	this.available[index]++;
	
	}
	
	//helper method
	public boolean fullColumn(int index) {
		if(this.available[index] > 5) {
			return true;
		}
		return false;
	}
	
	public boolean isWinning (int lastColumnPlayed, int player){
		// ADD YOUR CODE HERE
		for (int j = 0; j<3 ; j++ ){
	        for (int i = 0; i<7; i++){
	            if (this.board[i][j] == player && this.board[i][j+1] == player && this.board[i][j+2] == player && this.board[i][j+3] == player){
	                return true;
	            }           
	        }
	    }
	    // verticalCheck
	    for (int i = 0; i<4 ; i++ ){
	        for (int j = 0; j<6; j++){
	            if (this.board[i][j] == player && this.board[i+1][j] == player && this.board[i+2][j] == player && this.board[i+3][j] == player){
	                return true;
	            }           
	        }
	    }
	    // ascendingDiagonalCheck 
	    for (int i=3; i<7; i++){
	        for (int j=0; j<3; j++){
	            if (this.board[i][j] == player && this.board[i-1][j+1] == player && this.board[i-2][j+2] == player && this.board[i-3][j+3] == player)
	                return true;
	        }
	    }
	    // descendingDiagonalCheck
	    for (int i=3; i<7; i++){
	        for (int j=3; j<6; j++){
	            if (this.board[i][j] == player && this.board[i-1][j-1] == player && this.board[i-2][j-2] == player && this.board[i-3][j-3] == player)
	                return true;
	        }
	   
	}
		return false; // DON'T FORGET TO CHANGE THE RETURN
	}
	
	public int canWinNextRound (int player){
		// ADD YOUR CODE HERE
		
		int bestMove = -1;
		
		for (int i = 0; i <= 6; i++) {
			if (available[i] < 6) {
			addDisk(i, player);
				if (isWinning(i, player) == true) {
					bestMove = i;
					removeDisk(i);
					break;
				}
			removeDisk(i);
			}
		}
	
		
		if(canWinNextRoundB(player) > -1 ) {
			
			if(this.available[canWinNextRoundB(player)] <= this.available[bestMove]) 
				return canWinNextRoundB(player);
				
			
	
			
		}
			//else if(this.available[canWinNextRoundB(player)] >= this.available[canWinNextRound(player)]) {
				//return bestmove;
			//}
		
		
		return bestMove;
			 // DON'T FORGET TO CHANGE THE RETURN
	}
	
	public int canWinNextRoundB (int player){
		// ADD YOUR CODE HERE
		
		int bestmove = -1;
		
		for (int i = 6; i >= 0; i--) {
			if (available[i] < 6) {
			addDisk(i, player);
				if (isWinning(i, player) == true) {
					bestmove = i;
					removeDisk(i);
					break;
				}
			removeDisk(i);
			}
		}
		return bestmove;
			 // DON'T FORGET TO CHANGE THE RETURN
	}
	
	
	//helper method
	public void removeDisk(int column) {
		
		this.board[column][available[column] - 1] = 0;
		this.available[column]--;
	}
	
	public int canWinTwoTurns (int player){
		//ADD YOUR CODE HERE 
		
		int opponent = 1;
		if (player == 1) {
		opponent = 2;
		}
		
		int bestMove = -1;
		
		for (int i = 0; i <7; i++) {
			if(available[i] < 6) {
				addDisk(i, player);
				
				if(canWinNextRound(opponent) == -1) {
					addDisk(i, 1);
					
					if(canWinNextRound(player) != -1 && canWinNextRoundB(player) != -1) {
						removeDisk(i);
						bestMove = i;
						break;
						
					}
					
					//removeDisk(j);
				}
					removeDisk(i);
				}
			}
				//removeDisk(i);
		
		return bestMove; // DON'T FORGET TO CHANGE THE RETURN
		}
	}
