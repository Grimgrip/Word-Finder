package wordfinder;

import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ATProject 
{
	private static final int MAX_NUMBER_OF_LETTERS = 6;
	private static final String DICTIONARY_PATH = "Dictionary.txt";
	public static final String LOG_PATH = "Log.txt";


	public static void main(String args[])
	{
		String message = "Given a set of six or fewer letters, this program finds all possible related words in a dictionary. \n \n"
						 +"Non-letters are removed and words larger than six letters will be truncated to work with the program.";

		//JOptionPane.showMessageDialog(null, message);

		String userInput = JOptionPane.showInputDialog(message + "\n \n Please enter the six letter or fewer string that you would like to search for.");

		// TEST: String userInput = "l;o(po9";
		String letters = WordFinder.removeNonLetters(userInput);

		if(letters.length() >  MAX_NUMBER_OF_LETTERS)
		{
			letters = letters.substring(0,  MAX_NUMBER_OF_LETTERS);
			String inputMessage = "Your string was longer than six characters. It has been truncated to: '" + letters + "'.";
			JOptionPane.showMessageDialog(null, inputMessage);
		}
		else
		{
			String inputMessage = "The string that will be used for the search is: '" + letters + "'.";
			JOptionPane.showMessageDialog(null, inputMessage);
		}



		try
		{
			Log log = new Log( LOG_PATH );

			SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy hh:mma " );
			String currentDate = dateFormat.format( new Date() );

			log.write( currentDate + ": Program Input: " + letters + "\r\n" );
			log.write( "Words found in the dictionary: \r\n\r\n" );

			ArrayList<String> wordList = new ArrayList<String>(  );

			ArrayList<String> permutations = WordFinder.findPotentialWords( letters );
			for (int i = 0; i < permutations.size(); i++)
			{
				String wordCandidate = permutations.get(  i );

				Boolean isAWord = WordFinder.isWordInDictionary( wordCandidate, DICTIONARY_PATH );

				if (isAWord)
				{
					boolean alreadyAdded = false;
					for (int wordIx = 0; wordIx < wordList.size(); wordIx++)
					{
						String wordIter = wordList.get(  wordIx );
						if (wordIter.equals(  wordCandidate ))
						{
							alreadyAdded = true;
							break;
						}
					}

					if (!alreadyAdded)
					{
						log.write( wordCandidate + "\r\n" );
						wordList.add(  wordCandidate );
					}
				}
			}

			log.write( "--------------------------------------------------\r\n" );
		}
		catch (Exception e)
		{
			System.out.println( "Exception thrown in main: " + e.getMessage() );
		}
	}    
}
