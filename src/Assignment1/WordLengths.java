package Assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordLengths {

    public static void countWordLengths(File file, int[] counts) throws FileNotFoundException {

        //using scanner to check if there is a word in a file
        try (Scanner scanner = new Scanner(file)) {

            //reading words from file
            while (scanner.hasNext()) {
                String word = scanner.next();
                int wordLength = calculateWordLength(word);

                // If the word length is greater than or equal to the array size,
                // increment the count in the last index
                if (wordLength >= counts.length) {
                    counts[counts.length - 1]++;
                }else {
                    counts[wordLength]++;
                }
            }
        }
    }
    //calculating the length of the words
    private static int calculateWordLength(String word){
        //strip leading and trailing punctuations
        word = word.replaceAll("^[^a-zA-Z]+|[^a-zA-Z]+$", "");
        //return the word length after removing the noise
        return word.length();
    }

    //
    public static void testCountWordLengths(){
        //specify the file to test
        File file = new File("C:\\Users\\giles\\Java-Programming-Arrays-Lists-and-Structured-Data\\public\\file.txt");

        int[] counts = new int[31];

        try{
            //use the count word class
            countWordLengths(file,counts);
            //print the word counts
            System.out.println("Word length");
            for (int i = 0; i<counts.length;i++){
                if (counts[i] > 0){
                    System.out.println(i + " words of length " + counts[i]);
                }
            }
            //Find the most common word length
            int mostCommonLength = indexOfMax(counts);
            System.out.println("Most common word length: " + mostCommonLength);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    //helper method to find the index of the maximum value in the array
    private static int indexOfMax(int[] values){
        int maxIndex = 0;
        for (int i=0;i<values.length;i++){
            if (values[i] > values[maxIndex]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}