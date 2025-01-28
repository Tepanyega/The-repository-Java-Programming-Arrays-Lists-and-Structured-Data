package Assignment2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CaesarBreaker {

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
    //decrypt a message with one key
    // Method to decrypt a message that was encrypted with one key using Caesar cipher
    public static String decrypt(String input, int key){
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < input.length(); i++){
            if (Character.isUpperCase(input.charAt(i))){
                char originalChar = (char)((input.charAt(i) - 'A' - key + 26) % 26 + 'A' );
                decryptedMessage.append(originalChar);
            }
            else if (Character.isLowerCase(input.charAt(i))){
                char originalChar = (char) ((input.charAt(i) - 'a' - key + 26) % 26 + 'a');
                decryptedMessage.append(originalChar);
            }else {
                decryptedMessage.append(input.charAt(i));
            }
        }
        return decryptedMessage.toString();
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
    // Method to determine the key used for encrypting a string (finds the most frequent letter 'e')
    public static int getKey(String s) {
        int[] letterCounts = countLetters(s);
        int maxIndex = maxIndex(letterCounts);

        // Dynamically find the most frequent letter in the message
        char mostFrequentLetter = (char)(maxIndex + 'a');

        // Assuming the most frequent letter is the key, we calculate the difference
        return maxIndex - ('e' - 'a');  // Using 'e' as the standard most frequent letter
    }


    // Method to decrypt a message encrypted with two keys
    public static String decryptTwoKeys(String encrypted) {
        // Step 1: Split the message into two halves
        String half1 = halfOfString(encrypted, 0);  // Odd positions (starting from 0)
        String half2 = halfOfString(encrypted, 1);  // Even positions (starting from 1)

        // Step 2: Find the keys for both halves
        int key1 = getKey(half1);
        int key2 = getKey(half2);

        // Print the two keys found
        System.out.println("Key 1: " + key1);
        System.out.println("Key 2: " + key2);

        // Step 3: Decrypt using the two keys
        TwoKeysDecript cc = new TwoKeysDecript();
        return TwoKeysDecript.decryptTwoKeys(encrypted, key1, key2);  // Use the encryption method for two keys
    }

    // Test method for decryption for two keys
    public static void testDecrypt() {
        // Test message that was encrypted with two keys
        Path encryptedFile = Path.of("C:\\Users\\giles\\Java-Programming-Arrays-Lists-and-Structured-Data\\public\\file.txt");
        String encryptedMessage = "";

        try {

            encryptedMessage = Files.readString(encryptedFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Decrypt the message with two keys
        // Decrypt the message with two keys
        System.out.println("Encrypted message: \n");
        TwoKeysDecript.testCaesar();
        System.out.println("Encrypted message two keys:");



        String decryptedMessage = decryptTwoKeys(encryptedMessage);
        System.out.println("Decrypted message: \n" + decryptedMessage);
    }
}
