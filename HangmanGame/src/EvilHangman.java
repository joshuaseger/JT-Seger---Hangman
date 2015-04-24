
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
/**
 * EvilHangman implements the Hangman interface in a deceiving way by changing its chosen word based on the 
 * players guesses and mapping each redacted form of each word to a list partition and then choosing the 
 * partition with the most words.
 * Also included is a display() function which gives a graphical hangman image for viewing pleasure.
 * @author JT Seger
 * 2/15/2015
 *
 */

public class EvilHangman implements Hangman {
	
	
	public static ArrayList<String> dict = new ArrayList<String>();
	private ArrayList<Character> guesses;
	private String chosenWord;
	private ArrayList<String> wordBank;
	
	public EvilHangman(Scanner scan)
	{	
		this.wordBank = new ArrayList<String>();
		this.guesses = new ArrayList<Character>();
		this.chosenWord = "";
		
	while(scan.hasNextLine())
	{
		String word = scan.nextLine();
		dict.add(word);
	}	
	scan.close();
	this.selectWord();
	}

	/**
	 * Selects a secret word randomly and stores in this.chosenWord. 
	 * Also clears this.guesses and this.wordBank
	 */
	public void selectWord() {
		this.guesses = new ArrayList<Character>();
		this.wordBank = new ArrayList<String>();
		int size = dict.size();
		int index = new Random().nextInt(size);
		this.chosenWord = dict.get(index);
		int length = this.chosenWord.length();
		for(String word:dict)
		{
			if((word.length()==length))
			{
				this.wordBank.add(word);
			}
		}
	
	}

	
	@Override
	public String getRedactedWord() {
		String result = "";
		for(int i=0;i<chosenWord.length();i++)
		{
			char x = chosenWord.charAt(i);
			
			if(this.guesses.contains(x))result = result + x;
			else result = result + "-";
		}
		
		return result;
	}

    @Override
	public int getBlanksRemaining() {
		
		int result = 0;
		for(int i=0;i<chosenWord.length();i++)
		{
			char x = chosenWord.charAt(i);
			
			if(!this.guesses.contains(x)) result++;
		}
		
		return result;
	}

	@Override
	public Collection<Character> getAllGuesses() {
		
		return this.guesses;
	}

	@Override
	public int getNumGuesses() {
		int length = this.guesses.size();
		return length;
	}

	@Override
	public Collection<Character> getWrongGuesses() {
		
		ArrayList<Character> wrongGuesses = new ArrayList<Character>();
		for(int i=0;i<this.chosenWord.length();i++)
		{
			char x = this.chosenWord.charAt(i);
			
			if(!this.guesses.contains(x)) wrongGuesses.add(x);
		}
		
		return wrongGuesses;
	}

	@Override
	public int getNumWrongGuesses() {

		int result = 0;
		for(int i =0;i<this.guesses.size();i++)
		{
			Character x = this.guesses.get(i);
			if(this.chosenWord.indexOf(x)==-1)
			{
				result++;
			}
		}
		
		return result;
		
	}
	
  /*
   * getRedactedWord function but with a parameter String.
   */
	private String getRedactedWord(String word) {
		String result = "";
		for(int i=0;i<word.length();i++)
		{
			char x = word.charAt(i);
			
			if(this.guesses.contains(x))result = result + x;
			else result = result + "-";
		}
		
		return result;
	}
	
	  /*
	   * Updates the word bank to the partition with the greatest number
	   * of valid words which fit the current redaction.
	   */
	private void updateWordBank()
	{
		int biggest = 0;
	
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		for(String word:this.wordBank)
		{
			String redaction = getRedactedWord(word);
			Set<String> set = map.keySet();
			if(set.contains(redaction))
			{
				map.get(redaction).add(word);
			}
			else{
				ArrayList<String> newPartition = new ArrayList<String>();
				newPartition.add(word);
				map.put(redaction, newPartition);
			}
		}
		for(ArrayList<String> list:map.values())
		{
		int size = list.size();
			if(size>biggest)
			{
				biggest = size;
				this.wordBank = list;
			}
		}
		if(!this.wordBank.contains(this.chosenWord))
		{
			int size = this.wordBank.size();
			int index = new Random().nextInt(size);
			this.chosenWord = this.wordBank.get(index);
		}
			
	}


	  																																																																																					
    /**
     * Records a guess and returns a message describing the result.
     * Also updates this.wordBank.
     *   @param guess the character being guessed
     *   @return a message describing the result of the guess, either 
     *           "You already guessed X.", "Sorry.  X does not appear in the word.
     *           or "Good guess!".
     */
	public String makeGuess(char guess) {

		String result = "";
	
		if(this.guesses.contains(guess))
		{
			result = "You already guessed '" + guess +"'";
			return result;
		}
		//CASE guess doesn't exist in chosenWord
		this.guesses.add(guess);
		this.updateWordBank();
		int test = this.chosenWord.indexOf(guess);
	
		if(test==-1)
		{
			result = "Sorry. The letter '" + guess +"' does not appear in the word.";
			return result;
		}
		//CASE guess does exist in chosenWord
		else
		{
		result = "Good guess!";
		return result;
		}
	
	}
	
	@Override
	public String display(){
		String result = "";
		if(this.getNumWrongGuesses()==0){
			result = "________\n|       |\n|       |\n|       \n|     \n|     \n|     \n|     \n|_______________";				
		}
		if(this.getNumWrongGuesses()==1){
			result = "________\n|       |\n|       |\n|      (_)\n|    \n|    \n|     \n|     \n|_______________";				
		}
		if(this.getNumWrongGuesses()==2){
			result =  "________\n|       |\n|       |\n|      (_)\n|      |_|\n|    \n|     \n|     \n|_______________";			
		}
		if(this.getNumWrongGuesses()==3){
			result = "________\n|       |\n|       |\n|    __(_)\n|      |_|\n|    \n|     \n|     \n|_______________";			
		}
		if(this.getNumWrongGuesses()==4){
			result = "________\n|       |\n|       |\n|    __(_)__\n|      |_|\n|    \n|     \n|     \n|_______________";			
			}
		if(this.getNumWrongGuesses()==5){
			result = "________\n|       |\n|       |\n|    __(_)__\n|      |_|\n|      |\n|      | \n|     \n|_______________";				
		}
		if(this.getNumWrongGuesses()==6){
			result = "________\n|       |\n|       |\n|    __(_)__\n|      |_|\n|      | |\n|      | | \n|     \n|_______________";	
				
		}
		
				return result;
	}

}
