import java.util.Scanner;
import java.io.File;

/**
 * Driver class for playing a text-based game of Hangman.
 *   @author Dave Reed (loosely based on code by Stuart Reges and Keith Schwarz) and edited by JT Seger
 *   @version 2/10/15
 */
public class HangmanGame {
	public static final int MAX_GUESSES = 6;
    public static final String DICTIONARY_FILE = "dictionary.txt";

    
    public static void main(String[] args) throws java.io.FileNotFoundException {
        Hangman hangman = new HonestHangman(new Scanner(new File(HangmanGame.DICTIONARY_FILE)));
        Scanner input = new Scanner(System.in);
      
        char play = 'y';
        while (play == 'y') {
        	
        	hangman.selectWord();
	        System.out.println("\n I'm thinking of a " + 
	                           hangman.getRedactedWord().length() + 
	                           " letter word.");
	     
	        	        
	        while (hangman.getNumWrongGuesses() < HangmanGame.MAX_GUESSES && 
	        	   hangman.getBlanksRemaining() > 0) {
	        	System.out.println(hangman.display());
	            System.out.println("\nWrong guesses remaining: " + 
	            		          (HangmanGame.MAX_GUESSES-hangman.getNumWrongGuesses()));
	            System.out.println("Guessed so far: " + hangman.getAllGuesses());
	            System.out.println("Current pattern: " + hangman.getRedactedWord());
	            System.out.print("Your guess: ");
	            
	            char guess = input.next().toLowerCase().charAt(0);
	            System.out.println(hangman.makeGuess(guess));
	            
	        }
	        //Only displays Hangman graphic if Max Guesses set to 6, otherwise images don't make sense.
	        if(MAX_GUESSES==6)
	    	System.out.println(hangman.display());
	        
	        if (hangman.getBlanksRemaining() > 0) {
            	for (char ch : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
	        		hangman.makeGuess(ch);
	        	}
            	
            	System.out.println("\nYOU LOSE!");
	        }
	        else {
	        	System.out.println("\nCONGRATULATIONS!");
	        }
	        System.out.println("The word was: " + hangman.getRedactedWord());
	     
	       
	        
	        System.out.println("\nPlay again? (y/n) ");
	        play = input.next().toLowerCase().charAt(0);
        }
        System.out.println("\nThanks for playing!");
        input.close();
    }
}
