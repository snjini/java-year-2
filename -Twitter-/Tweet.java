package a5;
//Sasha Njini
//260783102

import java.util.HashSet;
import java.io.*;

public class Tweet {
 private String userAccount;
 private String date;
 private String time;
 private String message;
 private static HashSet<String> stopWords;
 
 
 public Tweet(String userAccount, String date, String time, String message) {
 this.userAccount = userAccount;
  this.date = date;
  this.time = time;
  this.message = message;
 }
 public boolean checkMessage() throws NullPointerException {
	 if(Tweet.stopWords == null) {
	    throw new NullPointerException("HashSet has not been initialized.");
	   }
	String[] words = this.message.split(" ");
  int numOfWords = 0;
    for(int i=0; i<words.length; i++) {
     if(isStopWord(words[i]) == false) {
      numOfWords++;
    }
  }
  if(numOfWords < 16 && numOfWords  > 0){
   return true;
  } 
  
  return false; 
 }

public static boolean isStopWord(String word) {
 
if((word.charAt(word.length() -1) == ',') || (word.charAt(word.length() -1) == '.')
  || (word.charAt(word.length() -1) == ';') || (word.charAt(word.length() -1) == ':')){
   word = word.substring(0, word.length()-2);
  }
 
for(String s : stopWords) {
 if(word.equalsIgnoreCase(s)) {
  return true;
 } 
}
 return false;
}

public String getDate() {
 return this.date;
}
public String getTime() {
 return this.time;
}
public String getMessage() {
 return this.message; 
}
public String getUserAccount() {
 return this.userAccount;
}

public String toString() {
 String s = this.userAccount +  "\t" +  this.date + "\t" + this.time + "\t" + this.message ;
 return s;
}

public boolean isBefore(Tweet Tweet) {
 String [] ymd = Tweet.date.split("-");
 String [] ymdUser =  date.split("-");
 
 String [] hms = Tweet.time.split(":");
 String [] hmsUser = time.split(":");
 
 //if the year is before
 if(Integer.parseInt(ymd[0]) < Integer.parseInt(ymdUser[0])) {
  return true;
 }
 //if the year is the same then check if the month is before
 else if(Integer.parseInt(ymd[0]) == Integer.parseInt(ymdUser[0])) {
  if(Integer.parseInt(ymd[1]) < Integer.parseInt(ymdUser[1])) {
   return true;
  }
 //if the month is the same then check if the day is before
   else if(Integer.parseInt(ymd[1]) == Integer.parseInt(ymdUser[1])) {
   if(Integer.parseInt(ymd[2]) < Integer.parseInt(ymdUser[2])) {
    return true;
   }
 //if the day of the month is the same then check the time
   else if(Integer.parseInt(ymd[0]) == Integer.parseInt(ymdUser[0])) {
    if(Integer.parseInt(hms[0]) > Integer.parseInt(hmsUser[0])) {
     return true;
    }
    
    else if(Integer.parseInt(hms[0]) == Integer.parseInt(hmsUser[0])) {
     if(Integer.parseInt(hms[1]) < Integer.parseInt(hmsUser[1])) {
      return true;
     }
     else if(Integer.parseInt(hms[1]) == Integer.parseInt(hmsUser[1])) {
      if(Integer.parseInt(hms[2]) < Integer.parseInt(hmsUser[2])) {
       return true;
      }
      else if(Integer.parseInt(hms[2]) == Integer.parseInt(hmsUser[2])) {
       return true;
       
      }
      else {
       return false;
      }
     }
     else {
      return false;
      }
     }
    else {
     return false;
    }
   }
   else {
    return false;
   }
  }
   else {
    return false;
   }
 }
 else {
  return false;
  
 }
}

public static void loadStopWords(String file) {
  try {
 FileReader fr = new FileReader(file);
 BufferedReader br = new BufferedReader(fr);
 String currentLine = br.readLine();
 HashSet<String>stopWords = new HashSet<>();
 while(currentLine != null) {
  currentLine = br.readLine();
  stopWords.add(currentLine);
 }
 br.close();
 fr.close();

 }
  catch (IOException e) {
    System.out.println(" ");
}
}
}




