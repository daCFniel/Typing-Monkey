import java.io.*;
import java.util.*;
/**
 * A random typing monkey.
 * Based on an idea by Dermot Shinners-Kennedy.
 * 
 * @author David J. Barnes, Daniel Bielech
 * @version 2020.02.20
 */
public class Monkey
{
    // A small dictionary of real words.
    private List<String> dictionary;
    // A random number generator.
    private Random random;
    // The alphabet to use in word generation.
    // This contains a space, which is used to terminate words.
    private static final char[] ALPHABET = 
            "abcdefghijklmnopqrstuvwxyz         ".toCharArray();

    /**
     * Create a random typing monkey.
     * @param dictionary A list of valid words.
     */
    public Monkey(List<String> dictionary)
    {
        random = new Random();
        this.dictionary = dictionary;
    }
    
    /**
     * Generate the given number of words.
     * @param numberOfWords How many to generate.
     * @return A list of the words generated.
     */
    public List<String> generate(int numberOfWords, boolean duplicates)
    {
        List<String> generated = new ArrayList<>(numberOfWords);
        double totalLength = 0;
        while(generated.size() < numberOfWords) {
            String word = generateRandomWord();
            if(word.length() > 2  && Collections.binarySearch(dictionary, word)>=0) {
                if(duplicates){
                    if(!generated.contains(word)){
                        generated.add(word);
                        totalLength+=word.length();
                    }
                }
                else{
                    generated.add(word);
                    totalLength+=word.length();
                }               
            }
        }
        double averageLength = totalLength/numberOfWords;
        generated.add(Double.toString(averageLength));
        return generated;
    }
    
    /**
     * Generate a random word.
     * @return The generated word. The length could be zero.
     */
    public String generateRandomWord()
    {
        StringBuilder word = new StringBuilder();
        char randomChar = generateRandomChar();
        
        while(randomChar != ' ') {
            word.append(randomChar);
            randomChar = generateRandomChar();
        }
        return word.toString();
    }
    
    /**
     * Generate a random char from the alphabet.
     * @return A random character from the alphabet.
     */
    public char generateRandomChar()
    {   
        return ALPHABET[random.nextInt(35)];
    }
}
