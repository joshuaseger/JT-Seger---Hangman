import java.util.Collection;

/**
 * Interface that defines the methods that must be implement by a Hangman.
 *   @author Dave Reed and edited by JT Seger
 *   @version 2/10/15
 */
public interface Hangman {      
    /**
     * Selects a word at random to be guessed (and resets appropriate fields).
     * [Note: should be called by the constructor to select the initial word.]
     */
	public void selectWord();
	
    /**
     * Returns the selected word with unguessed letters redacted (i.e., replaced by '-').
     *   @return the redacted word
     */
    public String getRedactedWord();
    
    /**
     * Returns the number of blanks still remaining in the redacted word.
     *   @return the remaining number of blanks
     */
    public int getBlanksRemaining();
        
    /**
     * Returns a collection of all characters guessed so far.
     *   @return the collection of guessed characters
     */
    public Collection<Character> getAllGuesses();

    /**
     * Returns number of characters guessed so far.
     *   @return the number of guesses
     */
    public int getNumGuesses();

    /**
     * Returns a collection of all wrong characters guessed so far.
     *   @return the collection of wrong characters
     */
    public Collection<Character> getWrongGuesses();
    
    /**
     * Returns number of wrong characters guessed so far.
     *   @return the number of wrong guesses
     */
    public int getNumWrongGuesses();
    
    /**
     * Records a guess and returns a message describing the result.
     *   @param guess the character being guessed
     *   @return a message describing the result of the guess, either 
     *           "You already guessed X.", "Sorry.  X does not appear in the word.",
     *           or "Good guess!".
     */
    public String makeGuess(char guess);
    

    /**
     * ADDED BY JT for the fun purpose of displaying an actual hangman!
     * @return
     */
    public String display();

}