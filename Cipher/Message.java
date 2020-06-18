package a1;
public class Message {

//Sasha Njini
//260783102
	
	public String message;
	public int lengthOfMessage;
	
	public Message (String m){
		message = m;
		lengthOfMessage = m.length();
		this.makeValid();
	}
	
	public Message (String m, boolean b){
		message = m;
		lengthOfMessage = m.length();
	}
	
	/**
	 * makeValid modifies message to remove any character that is not a letter and turn Upper Case into Lower Case
	 */
	public void makeValid(){
		//INSERT YOUR CODE HERE
		
		// used the replaceAll method to remove any character that is not a letter
		message = message.replaceAll("[^a-zA-Z]", ""); 
		//used the toLowerCase method to make all upper case letter lower case
		message = message.toLowerCase();
		//changed the attribute length to the length of the valid message
		lengthOfMessage = message.length();
		
	}
	
	/**
	 * prints the string message
	 */
	public void print(){
		System.out.println(message);
	}
	
	/**
	 * tests if two Messages are equal
	 */
	public boolean equals(Message m){
		if (message.equals(m.message) && lengthOfMessage == m.lengthOfMessage){
			return true;
		}
		return false;
	}
	
	/**
	 * caesarCipher implements the Caesar cipher : it shifts all letter by the number 'key' given as a parameter.
	 * @param key
	 */
	public void caesarCipher(int key){
		// INSERT YOUR CODE HERE
		
	//created an empty string to hold the encrypted message
	String shiftMessage = "";
	
	//made a loop to go through the string and shift each letter
	//made  if statements to ensure that the key can be any size
	for(int i =0; i<message.length(); i++) {
	
		char c = (char) (message.charAt(i) + (key%26));
          if (c > 'z') {
        	  c = (char) (c-26);
        	  
          } else if (c < 'a') {
        	  c = (char) (c+26);
          }
          shiftMessage += c;
        }
        
    
 
   // changed the message attribute to the new message
   // changed the message length attribute to the new message length	
	
	
	 message = shiftMessage;	
	 lengthOfMessage = message.length();
	}
	
	public void caesarDecipher(int key){
		this.caesarCipher(-key);
		
	}	
	
	/**
	 * caesarAnalysis breaks the Caesar cipher
	 * you will implement the following algorithm :
	 * - compute how often each letter appear in the message
	 * - compute a shift (key) such that the letter that happens the most was originally an 'e'
	 * - decipher the message using the key you have just computed
	 */
	
	public void caesarAnalysis(){
		// INSERT YOUR CODE HERE
	
		int highestFreq = 0;
		char mostFreqChar = ' ';
		 
	  //get a char and go through entire string to determine how many times that char occurs
	 for (int i = 0; i < message.length(); i++) { 
		 char x = message.charAt(i);
		 int counter = 0;
		 
		 for (int j = message.indexOf(x); j > -1; j = message.indexOf(x, j + 1)){
			 counter++;
		} 
		 if (counter > highestFreq) {
		   highestFreq = counter;
		   mostFreqChar = x;
		} 
	 }
	 
	//make the key equal to the number that shifts the string when the most frequent char is e
	   int key = (int)mostFreqChar-101;
	   caesarDecipher(key);
	   lengthOfMessage = message.length();
  
	}
	
	/**
	 * vigenereCipher implements the Vigenere Cipher : it shifts all letter from message by the corresponding shift in the 'key'
	 * @param key
	 */
	public void vigenereCipher (int[] key){
		// INSERT YOUR CODE HERE
		
	//created an empty string to hold the encrypted message
		String shiftMessage = "";
		
	//go through the array of integers and shift the characters accordingly 
	//keep in mind that the length of the key is not always equal to to the length of the message
		
		for(int j =0; j<key.length; j++) {
			for(int i=0; i<message.length(); i++) {
				
			if(j>key.length-1) {
				j=0;
			}
			
			char c = (char) (message.charAt(i) + ((key[j])%26));
			
	          if (c > 'z') {
	        	  c = (char) (c-26);
	        	  shiftMessage += c;
	        	  j++;
	          } else if (c < 'a') {
	        	  c = (char) (c+26);
	        	  shiftMessage += c;
	        	  j++;
	          } else {
	          shiftMessage += c;
	          j++;
	        }
	       
	        }
	//stop the loop when the message has been encrypted		
		 if(shiftMessage.length()>message.length()) {
	        	break;  	
		}
		 
	// changed the message attribute to the new message
	// changed the message length attribute to the new message length	
	message = shiftMessage;	
	lengthOfMessage = message.length();
	
	}
}
	/**
	 * vigenereDecipher deciphers the message given the 'key' according to the Vigenere Cipher
	 * @param key
	 */
	public void vigenereDecipher (int[] key){
		// INSERT YOUR CODE HERE
		
	//created an empty string to hold the message	
		String shiftMessage = "";
	
	//go through the array of integers and shift the characters accordingly 
	//keep in mind that the length of the key is not always equal to to the length of the message
	//just changed the signs because its just the (-key)
		for(int j =0; j<key.length; j++) {
			for(int i=0; i<this.message.length(); i++) {
				
			if(j>key.length-1) {
				j=0;
			}
			
			char c = (char) (message.charAt(i) - ((key[j])%26));
	          
			if (c > 'z') {
	        	  shiftMessage += (char) (c-26);
	        	  j++;
	        	  
	          } else if (c < 'a') {
	        	  shiftMessage += (char) (c+26);
	        	  j++;
	        	  
	          } else {
	          shiftMessage += c;
	          j++;
	          
	          ;
	        }
	           
		}	
			
	//stop the loop when the message has been decrypted
		if(shiftMessage.length()>message.length()) {
    	break;
			}			
	// changed the message attribute to the new message
	// changed the message length attribute to the new message length	
		message = shiftMessage;	
		lengthOfMessage = message.length();
		
	 }		
 }
	/**
	 * transpositionCipher performs the transition cipher on the message by reorganizing the letters and eventually adding characters
	 * @param key
	 */
	public void transpositionCipher (int key){
		// INSERT YOUR CODE HERE
		
		//made empty string to hold the message
		String tMessage = "";
		
		//made the number of rows for the particular string
	    int row = (int) Math.ceil((double) message.length() / key);

	   //created the array to hold the message
	        char[][] mesA = new char[row][key];
	        
	   //filled the array with the characters of the message
	        int z = 0;
	        for (int x = 0; x < row; x++) {
	            for (int y = 0; y < key; y++) {
	            //made the character '*' when it had reached the end of the message
	                if (message.length() == z) {
	                    mesA[x][y] = '*';
	                    z--;
	                } else {
	                    mesA[x][y] = message.charAt(z);
	                }

	                z++;
	            }
	        }
	  // went through the array and added each character to the empty string
	      for (int column = 0; column < mesA[0].length; column++) {
	    	    for (int roww = 0; roww < mesA.length; roww++) {
	    	       tMessage += mesA[roww][column];
	    	    }
	    	}
	// changed the message attribute to the new message
	// changed the message length attribute to the new message length
	      message = tMessage;
	      lengthOfMessage = message.length();
	  }
	 
	  
	/**
	 * transpositionDecipher deciphers the message given the 'key'  according to the transition cipher.
	 * @param key
	 */
	public void transpositionDecipher (int key){
		// INSERT YOUR CODE HERE
		
		//made empty string to hold the message
		 String tMessage = "";
		 
		 //made the number of rows for the particular string
	        int row = (int) Math.ceil((double) message.length() / key);
	        
	     //created the array to hold the message
	        char[][] mesA = new char[row][key];
	        
	    //filled the array with the characters of the message
	        int z = 0;
	        for (int y = 0; y < key; y++) {
	        		for (int x = 0; x < row; x++) {
	         
	                    mesA[x][y] = message.charAt(z);
	            
	                z++;
	            }
	        }
	    // went through the array and added each character to the empty string
	      for (int roww = 0; roww < mesA.length; roww++) {
	    	  	for (int column = 0; column < mesA[0].length; column++) {
	    	  
	    	       tMessage += mesA[roww][column];
	    	//took out all characters that are not letters
	    	       tMessage = tMessage.replaceAll("[^a-z]", ""); 
	    	    }
	    	}
	    // changed the message attribute to the new message
	    // changed the message length attribute to the new message length
	      message = tMessage;
	      lengthOfMessage = message.length();
	   }
}

	
	

