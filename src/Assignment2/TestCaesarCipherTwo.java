package Assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestCaesarCipherTwo {

    //void method that ha no parameters
    public static void simpleTests(){
        //reading file content as a string
        String message = readFile("C:\\Users\\giles\\Java-Programming-Arrays-Lists-and-Structured-Data\\public\\file.txt");

        //create a caesarCipherTwo objects with some keys
        CaesarCipherTwo cipher = new CaesarCipherTwo(2, 3);

        //encrypt the message
        String encryptedMessage = cipher.encrypt(message);
        System.out.println("Encrypted message: ".concat(encryptedMessage));

        //to decrypt the message
        String decryptMessage = breakCaesarCipher(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptMessage);

    }

    private static String readFile(String fileName){
        StringBuilder content = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){
                content.append(line).append("\n");
            }
        }catch (IOException e){
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return content.toString();
    }
    //counting the frequency of each letter in a word
    public static int[] countLetters(String message){
        int[] count = new int[26];
        for (int i = 0; i<message.length();i++){
            char ch = Character.toLowerCase(message.charAt(i));
            if (ch >= 'a' && ch <= 'z'){
                count[ch - 'a']++;
            }
        }
        return count;
    }
    // Method to split the string into two halves
    public static String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            half.append(message.charAt(i));
        }
        return half.toString();
    }

    // Method to find the index of the letter with the maximum frequency
    public static int maxIndex(int[] counts) {
        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    //this method should figure out which keys were used to encrypt this message
    public static String breakCaesarCipher(String input){
        //split the input into two parts, odd and even indexed
        String evenIndexed = halfOfString(input, 0);
        String oddIndexed = halfOfString(input, 1);

        //find the likely key for each part using frequency analysis
        int key1 = findKey(evenIndexed);
        int key2 = findKey(oddIndexed);

        //// Create a CaesarCipherTwo object with the found keys and decrypt the message
        CaesarCipherTwo cipher = new CaesarCipherTwo(key1, key2);
        return cipher.decrypt(input);

    }
    //split the input string into two halves
//    private static  String halfOfString(String message, int start){
//        StringBuilder half = new StringBuilder();
//        for (int i =0; i<message.length();i++){
//            half.append(message.charAt(i));
//        }
//        return half.toString();
//    }

    //find key method
//
    public static int findKey(String input) {
        // Create an array to store the frequency count of each letter
        int[] count = new int[26];

        // Count the frequency of each letter in the input string
        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetter(input.charAt(i))) {
                char ch = Character.toUpperCase(input.charAt(i));
                if (ch >= 'A' && ch <= 'Z') {
                    count[ch - 'A']++;
                }
            }
        }

        // Find the index of the most frequent letter
        int maxIndex = maxIndex(count);
        char mostFrequentLetter = (char) ('A' + maxIndex);

        // Debugging: Print the most frequent letter and its frequency
        System.out.println("Most frequent letter in the message: " + mostFrequentLetter);
        System.out.println("Frequency: " + count[maxIndex]);

        // Calculate the key by shifting the most frequent letter to 'E'
        int key = mostFrequentLetter - 'E';
        if (key < 0) {
            key += 26; // Handle negative keys (if the letter is before 'E')
        }

        // Debugging: Print the calculated key
        System.out.println("Calculated key: " + key);

        // Check for multiple frequent letters and their frequencies
        System.out.println("Letter frequencies: ");
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                System.out.println((char) ('A' + i) + ": " + count[i]);
            }
        }

        return key;
    }
}
