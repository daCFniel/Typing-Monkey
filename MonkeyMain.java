import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Manage a monkey generating random words.
 *
 * @author David J. Barnes
 * @version 2020.02.20
 */
public class MonkeyMain
{
    // The dictionary used by monkeys to check words.
    private static final List<String> dictionary = 
            readDictionary("2of12id.txt");

    public static void main(String[] args)
        throws Exception
    {
        List<String> dictionary = readDictionary("toddlist.txt");
        Monkey m = new Monkey(dictionary);
        List<String> words = m.generate(30, false);
        for(String w : words) {
            System.out.println(w);
        }
    }
    
    public void tabulate(int wordsPerLine)
    {
        List<String> dictionary = readDictionary("toddlist.txt");
        Monkey m = new Monkey(dictionary);
        List<String> words = m.generate(30, true);
        Collections.sort(words);
        int i = 0;
        for(int j = 0; j < words.size(); j++) {
            System.out.printf(words.get(j)+" ");
            i++;
            if(i==wordsPerLine-1){
                j++;
                System.out.println(words.get(j)+" ");                
                i = 0;
            }
        }
    }
    
    public void timing(int numberOfWords)
    {
        List<String> dictionary = readDictionary("toddlist.txt");
        Monkey m = new Monkey(dictionary);
        
       // long timeBefore = System.nanoTime();
       // List<String> words1 = m.generate(numberOfWords, false);
       // long timeAfter = System.nanoTime();
       // System.out.println("Time with duplicates: "+(timeAfter-timeBefore));
       // System.out.println("Time to generate one word on average with duplicates: "+((timeAfter-timeBefore)/numberOfWords));
       //System.out.println("Average length of word with duplicates: "+(words1.get(words1.size()-1)));
        
        //long timeBefore = System.nanoTime();
        //List<String> words2 = m.generate(numberOfWords, true);
        //long timeAfter = System.nanoTime();
        //System.out.println("Time without duplicates: "+(timeAfter-timeBefore));
        //System.out.println("Time to generate one word on average without duplicates: "+((timeAfter-timeBefore)/numberOfWords));
        //System.out.println("Average length of word with duplicates: "+(words2.get(words2.size()-1)));
        
    }
    
    /**
     * Read a dictionary of words from the given file.
     * All words are converted to lower case.   
     * @param filename the dictionary file.
     * @return the list of words in the file.
     * @throws an exception if the dictionary cannot be read.
     */
    private static List<String> readDictionary(String filename)
    {
        try(BufferedReader reader = new BufferedReader(
                new FileReader(filename))) {             
            List<String> dictionary = new ArrayList<>();
            String word = reader.readLine();
            while(word != null) {
                dictionary.add(word.toLowerCase());
                word = reader.readLine();
            }
            return dictionary;
        }
        catch(Exception e) {
            System.out.println("Read of " + filename + " failed.");
            throw new RuntimeException(e);
        }
    }
}
