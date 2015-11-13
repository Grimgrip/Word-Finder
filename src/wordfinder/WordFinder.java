package wordfinder;

import javax.swing.JOptionPane;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordFinder 
{
    
    public static String removeNonLetters(String userInput)
    {
            String changedInput = userInput.replaceAll("\\W", "");
            changedInput = changedInput.replaceAll("[0-9]", "");
            changedInput = changedInput.replaceAll("_", "");

            if(!changedInput.equals(userInput))
            {
                    String message = "Your string contained non-letter characters which were removed.";
                    JOptionPane.showMessageDialog(null, message);
            }

            return changedInput;
    }

    public static ArrayList<String> findPotentialWords( String letters )
    {
            ArrayList<String> permutations = new ArrayList<String>( );

            if (letters.length() == 1)
            {
                    // input letters is a single character, output is one permutation containing input letters.
                    permutations.add(  letters );
            }
            else
            {
                    //finds all potential combinations of letters from an input
                    for (int i = 0; i < letters.length(); i++)
                    {
                            // Assuming letters = "abc"
                            // 	i == 0, then ch = 'a', and newLetters = 'bc', subList will return { "bc", "cb" }
                            // 	i == 1, then ch = 'b', and newLetters = 'ac', subList will return { "ac", "ca" }
                            // 	i == 2, then ch = 'c', and newLetters = 'ab', subList will return { "ab", "ba" }

                            // iterate through each char in the input letters
                            char ch = letters.charAt( i );

                            // remove the current char from letters
                            String newLetters = letters.substring( 0, i ) + letters.substring( i + 1 );

                            // and calculate all permutations for the remaining letters.
                            ArrayList<String> subList = findPotentialWords( newLetters );

                            // then concatenate the current char to each of the returned permutations, and populate output list.
                            for (int j = 0; j < subList.size(); j++)
                            {
                                    permutations.add( ch + subList.get(  j ) );
                            }
                    }
            }

            return permutations;
    }

    public static Boolean isWordInDictionary(String word, String dictionaryFilePath ) throws FileNotFoundException, IOException
    {
            boolean isAWord = false;

            String wordLower = word.toLowerCase();

            FileReader reader = null;
            BufferedReader bufferedReader = null;

            try {
                    reader = new FileReader( dictionaryFilePath );
                    bufferedReader = new BufferedReader( reader );

                    String line = bufferedReader.readLine();
                    while (line != null)
                    {
                            line = line.toLowerCase().trim();
                            if (line.equals( wordLower ))
                            {
                                    isAWord = true;
                                    break;
                            }
                            line = bufferedReader.readLine();
                    }
            }
            finally
            {
                    bufferedReader.close();
                    reader.close();
            }
            return isAWord;
    }
    
}
