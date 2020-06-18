package a5;
//Sasha Njini
//260783102


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Twitter {
  private ArrayList<Tweet> tweets;
  
  public Twitter() {
   tweets = new ArrayList<Tweet>();
   
  }
  
  public void loadDB(String fileName) {
    try {
   FileReader fr = new FileReader(fileName);
   BufferedReader br = new BufferedReader(fr);
   String currentLine = br.readLine();
   while(currentLine != null) {
  	 String [] lines = currentLine.split("\t");
   Tweet tweet = new Tweet(lines[0], lines[1], lines[2], lines[3]);
   if(tweet.checkMessage() == true) {
     this.tweets.add(tweet); 
     System.out.println(tweet.toString());
    }
   currentLine.split("\n");
   currentLine = br.readLine();
   	
   }
    br.close();
    fr.close();   
    sortTwitter();
   
   }
    catch (IOException e) {
      System.out.println("The file can't be found");
    }
    catch (NullPointerException e) {
    	System.out.println("The file of stopWords has not been loaded yet."); 
    }
   } 
  
 
  public void sortTwitter() {
   for(int i=0; i<tweets.size(); i++) {
    for(int j=0; j<tweets.size(); j++) {
     if(tweets.get(j).isBefore(tweets.get(i))) {
      Tweet tempVar = tweets.get(j+1);
      tweets.set(j+1, tweets.get(i));
      tweets.set(i, tempVar);
     } else {
      Tweet tempVar = tweets.get(i+1);
      tweets.set(i+1, tweets.get(j));
      tweets.set(j, tempVar);
     }
    }
   }
   }
  
  public int getSizeTwitter() {
   return tweets.size();
  }
  
  public Tweet getTweet(int index) {
   return tweets.get(index);
  }
  public String printDB() {
   String database = " ";
   for(Tweet element: tweets) {
    database += element.toString() +"ln";
   }
    return database;
  }
  public ArrayList<Tweet> rangeTweets(Tweet tweet1, Tweet tweet2) {
   int indexTweet1;
   int indexTweet2;
   if(tweet1.isBefore(tweet2)) {
    for(int i=0; i<tweets.size(); i++) {
     for(int j=0; j<tweets.size(); j++)
     if((tweets.get(i).equals(tweet1)) && ((tweets.get(j).equals(tweet2)))) {
     indexTweet1 = i;
      indexTweet2 = j; 
      ArrayList<Tweet> inBetween = new ArrayList<Tweet>();
      for(int k= indexTweet1; k<indexTweet2; k++ ) {
       inBetween.add(tweets.get(k));
      }
      return inBetween; 
     }
    }
   }  
   else if(tweet2.isBefore(tweet1)) {
    for(int i=0; i<tweets.size(); i++) {
     for(int j=0; j<tweets.size(); j++)
     if((tweets.get(i).equals(tweet2)) && ((tweets.get(j).equals(tweet1)))) {
     indexTweet1 = i;
      indexTweet2 = j; 
      ArrayList<Tweet> inBetween = new ArrayList<Tweet>();
      for(int k= indexTweet2; k<indexTweet1; k++ ) {
       inBetween.add(tweets.get(k));
      }
      return inBetween; 
     }
     }
    }
   return null;
   } 

  public void saveDB(String fileName) {
    try {
        // create the objects needed to write on the file
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        // iterate through all the elements of the array and write each of them on the file separated by a new line
        for(int i=0; i<tweets.size(); i++) {
            bw.write(printDB());
            bw.newLine();
        }
        // close the objects
        bw.close();
        fw.close();
    } catch (IOException e) {
        System.out.println(" ");
    }
 }
  
 public String trendingTopic() {
//hashmap to count the number of each word
HashMap<String, Integer> countWords = new HashMap<String, Integer>();
HashSet<String> allWords = new HashSet<String>();
for (Tweet t: tweets) {
String[] word = t.getMessage().split(" ");
for (int i = 0; i < word.length; i++) {
if (!Tweet.isStopWord(word[i])) {
allWords.add(word[i]);
}
if (!countWords.containsKey(word[i]) && !Tweet.isStopWord(word[i])); {
countWords.put(word[i], 1);
}
if (!Tweet.isStopWord(word[i])) {
int number = countWords.get(word[i]);
countWords.put(word[i], ++number);
}
}
}
int counter = 0;
String trendword = null;
for (String word: allWords) {
int numWords = countWords.get(word);
if (numWords == counter) {
trendword = trendword + "," + word;
}
if (numWords > counter) {
counter = numWords;
trendword = word;
}

}
return trendword; 

}
  public static void main(String[] args) {
   Twitter example = new Twitter();
   Tweet.loadStopWords("/Users/Sasha/Desktop/Files/stopWords.txt");
  example.loadDB("/Users/Sasha/Desktop/Files/tweets.txt");
  System.out.println(example.trendingTopic());
  }
 }
