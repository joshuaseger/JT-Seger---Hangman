import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

/**
 * HonestHangman implements the Hangman interface in an honest fashion to play Hangman.  
 * Also included is a display() function which gives a graphical hangman image for viewing pleasure.
 * @author JT Seger
 * 2/13/2015
 *
 */

public class HonestHangman implements Hangman {
	
	private ArrayList<String> wordBank;
	private ArrayList<Character> guesses;
	private String chosenWord;
	
	public HonestHangman(Scanner scan){
		
		this.wordBank = new ArrayList<String>();
		this.guesses = new ArrayList<Character>();
		this.chosenWord = "";
		
	while(scan.hasNextLine())
	{
		String word = scan.nextLine();
		wordBank.add(word);
	}	
	scan.close();
	this.selectWord();
	}

	@Override
	public void selectWord() {
		this.guesses = new ArrayList<Character>();
		int size = wordBank.size();
		int index = new Random().nextInt(size);
		this.chosenWord = this.wordBank.get(index);
	}

	
	@Override
	public String getRedactedWord() {
		String result = "";
		for(int i=0;i<chosenWord.length();i++)
		{
			char x = chosenWord.charAt(i);
			
			if(guesses.contains(x))result = result + x;
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
			
			if(!guesses.contains(x)) result++;
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

	  
    /**
     * Records a guess and returns a message describing the result.
     *   @param guess the character being guessed
     *   @return a message describing the result of the guess, either 
     *           "You already guessed X.", "Sorry.  X does not appear in the word.",
     *           or "Good guess!".
     */
	public String makeGuess(char guess) {
	
		String result = "";
		int test = this.chosenWord.indexOf(guess);
		if(this.guesses.contains(guess))
		{
			result = "You already guessed '" + guess + "'";
			return result;
		}
		//CASE guess doesn't exist in chosenWord
		else if(test==-1){
			this.guesses.add(guess);
			result = "Sorry. The letter '" + guess +"' does not appear in the word.";
			return result;
		}
		//CASE guess does exist in chosenWord
		else{
			this.guesses.add(guess);
		result = "Good guess!";
		return result;}
	
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
