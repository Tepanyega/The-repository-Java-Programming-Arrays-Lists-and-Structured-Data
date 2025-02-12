import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String message = "Top ncmy qkff vi vguv vbg ycpx";
        int key = 3;
        String encrtypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        int key1 = 2;
        int key2 = 20;

        System.out.println("Original message: " .concat(message));
        System.out.println("Encrypted message: " .concat(encrypt(message, key)));
        System.out.println("Original message1: " .concat(message));
        System.out.println("Encrypted message1: " .concat(encryptTwoKeys(message, key1, key2)));
        System.out.println("Decrypt two keys: " + decryptTwoKeys(message, key1, key2));
        String path = "C:\\Users\\giles\\Java-Programming-Arrays-Lists-and-Structured-Data\\public\\file.txt";
        ArrayList<String> words = readWordFromFile(path);

        System.out.println("Most common word length".concat("\n"));
        for (String word : words){
            System.out.println(word);
        }
        int[] wordLengths = countWordLengths(words);
        int mostCommonLength = -1;
        int maxCount =0;
        System.out.println("Word lengths:");
        for (int i = 1; i < wordLengths.length; i++) {
            if (wordLengths[i] > maxCount) {
                maxCount = wordLengths[i];
                mostCommonLength = i;
            }
        }

        // Print the most common word length
        if (mostCommonLength != -1) {
            System.out.println("Most common word length: " + mostCommonLength + " with " + maxCount + " occurrences.");
        } else {
            System.out.println("No words found.");
        }

         //Optionally print the word lengths distribution
        System.out.println("\nWord lengths:");
        for (int i = 1; i < wordLengths.length; i++) {
            if (wordLengths[i] > 0) {
                System.out.println(i + ": " + wordLengths[i]);
            }
        }


        System.out.println("Decrypt with two keys:\n");
        System.out.println("Decrypted message1: " .concat(decryptTwoKeys(encrtypted, key1, key2)));


        System.out.println("Decrypt two keys by guessing\n");
        String guessE = "Xifqvximt tsdtlxzrx iijirvtl ek Uybi";

        // Try all combinations of keys for decryption
        for (int key11 = 1; key11 < 26; key11++) {
            for (int key22 = 1; key22 < 26; key22++) {

                // Decrypt the message with the current key pair
                String decryptedMessage = decryptTwoKeysByGuessingKeys(guessE, key11, key22);

                // Debug: Print the decrypted message for each key pair
                System.out.println("Decrypted with key1 = " + key11 + " and key2 = " + key22 + ": " + decryptedMessage);

                // Check if the decrypted message is valid
                if (isValidMessage(decryptedMessage)) {
                    System.out.println("Valid decrypted message: " + decryptedMessage);
                }
            }
        }

        WordFrequencies wf = new WordFrequencies();
        wf.tester("C:\\Users\\samue\\OneDrive\\Desktop\\The-repository-Java-Programming-Arrays-Lists-and-Structured-Data\\public\\file.txt");

        CharactersInPlay cip = new CharactersInPlay();

        // Testing with macbethSmall.txt
        System.out.println("Testing with macbethSmall.txt...");
        cip.tester("C:\\Users\\samue\\OneDrive\\Desktop\\The-repository-Java-Programming-Arrays-Lists-and-Structured-Data\\public\\macbethSmall.txt", 2); // Only print characters with at least 2 speaking parts

        // Testing with macbeth.txt (full play)
        System.out.println("\nTesting with macbeth.txt...");
        cip.tester("C:\\Users\\samue\\OneDrive\\Desktop\\The-repository-Java-Programming-Arrays-Lists-and-Structured-Data\\public\\macbeth.txt", 10); // Set a higher threshold to filter major characters

        // Testing range method
        cip.charactersWithNumParts(5, 15);

        System.out.println("\nGladlib class...");

        Gladlib gladlib = new Gladlib();
        gladlib.makeStory();

        System.out.println("\nCodon Count class...");
        CodeCount cc = new CodeCount();
       // cc.tester();

        System.out.println("Words in files class...");

        WordsInFiles wif = new WordsInFiles();
        //wif.tester("C:\\Users\\samue\\OneDrive\\Desktop\\The-repository-Java-Programming-Arrays-Lists-and-Structured-Data\\public\\publiWords");

        System.out.println("Logs clas...");
        Tester tester = new Tester();
        //tester.testLogAnalyzer();
        tester.testUniqueIP();
        tester.testPrintAllHigherThanNum();
        tester.testUniqueIPVisitsOnDay();
        tester.testCountUniqueIPsInRange();
    }

    //most common word length
    //m using arrayList because i dont know how bif is the file
    //and ArrayLists shrinks and expand
    public static ArrayList<String> readWordFromFile(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        // ill be storing words from the file in here so i can do further analysis
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            // reading words from file
            while (scanner.hasNext()) {
                String word = scanner.next();
                // removing punctuations from the word
                String cleanWord = word.replaceAll("^[^a-zA-Z] + |[^a-zA-Z]+$", "");
                //only add non-empty clean words
                if (!cleanWord.isEmpty()) {
                    words.add(cleanWord);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File nt found: " + e.getMessage());
        }
        return words;
    }

    //count the word length
    public static int[] countWordLengths(ArrayList<String> words) {
        int[] counts = new int[31];
        for (String word : words) {
            int length = word.length();
            if (length >= counts.length) {
                counts[counts.length - 1]++; //Group words of length 30+
            } else {
                counts[length]++;
            }
        }
        return counts;
    }

    private static String decryptTwoKeys(String message, int key1, int key2) {

        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (i % 2 == 0) {//catering for even indexes
                if (Character.isUpperCase(ch)) {
                    char encryptedChar = (char) ((ch - 'A' - key1 + 26) % 26 + 'A');
                    decryptedMessage.append(encryptedChar);
                } else if (Character.isLowerCase(ch)) {
                    char encryptedChar = (char) ((ch - 'a' - key1 + 26) % 26 + 'a');
                    decryptedMessage.append(encryptedChar);
                } else {
                    decryptedMessage.append(ch);
                }
            } else {//this is odd
                if (Character.isUpperCase(ch)) {
                    char encrytedChar = (char) ((ch - 'A' - key2 + 26) % 26 + 'A');
                    decryptedMessage.append(encrytedChar);
                } else if (Character.isLowerCase(ch)) {

                    char encryptedChar = (char) ((ch - 'a' - key2 + 26) % 26 + 'a');
                    decryptedMessage.append(encryptedChar);
                } else {
                    decryptedMessage.append(ch);
                }
            }
        }
        return decryptedMessage.toString();
    }

    private static String encryptTwoKeys(String message, int key1, int key2) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (i % 2 == 0) {//catering for even indexes
                if (Character.isUpperCase(ch)) {
                    char encryptedChar = (char) ((ch - 'A' + key1) % 26 + 'A');
                    encryptedMessage.append(encryptedChar);
                } else if (Character.isLowerCase(ch)) {
                    char encryptedChar = (char) ((ch - 'a' + key1) % 26 + 'a');
                    encryptedMessage.append(encryptedChar);
                } else {
                    encryptedMessage.append(ch);
                }
            } else {//this is odd
                if (Character.isUpperCase(ch)) {
                    char encrytedChar = (char) ((ch - 'A' + key2) % 26 + 'A');
                    encryptedMessage.append(encrytedChar);
                } else if (Character.isLowerCase(ch)) {

                    char encryptedChar = (char) ((ch - 'a' + key2) % 26 + 'a');
                    encryptedMessage.append(encryptedChar);
                } else {
                    encryptedMessage.append(ch);
                }
            }
        }
        return encryptedMessage.toString();
    }

    //encrypt using caesar cipher algorithm using key 15
    public static String encrypt(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (Character.isLowerCase(ch)) {
                char encryptedChar = (char) ((ch - 'a' + key) % 26 + 'a');
                encryptedMessage.append(encryptedChar);
            } else if (Character.isUpperCase(ch)) {
                char encryptedChar = (char) ((ch - 'A' + key) % 26 + 'A');
                encryptedMessage.append(encryptedChar);
            } else {
                encryptedMessage.append(ch);
            }
        }
        return encryptedMessage.toString();
    }


    public static String decryptTwoKeysByGuessingKeys(String message, int key1, int key2) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);

            if (i % 2 == 0) { // Even index (Key1)
                if (Character.isUpperCase(ch)) {
                    char decryptedChar = (char) (((ch - 'A' - key1 + 26) % 26) + 'A');
                    decryptedMessage.append(decryptedChar);
                } else if (Character.isLowerCase(ch)) {
                    char decryptedChar = (char) (((ch - 'a' - key1 + 26) % 26) + 'a');
                    decryptedMessage.append(decryptedChar);
                } else {
                    decryptedMessage.append(ch);  // Non-alphabetic characters
                }
            } else {  // Odd index (Key2)
                if (Character.isUpperCase(ch)) {
                    char decryptedChar = (char) (((ch - 'A' - key2 + 26) % 26) + 'A');
                    decryptedMessage.append(decryptedChar);
                } else if (Character.isLowerCase(ch)) {
                    char decryptedChar = (char) (((ch - 'a' - key2 + 26) % 26) + 'a');
                    decryptedMessage.append(decryptedChar);
                } else {
                    decryptedMessage.append(ch);  // Non-alphabetic characters
                }
            }
        }
        return decryptedMessage.toString();
    }

    // Method to check if a message contains valid English words (basic check using a set)
    public static boolean isValidMessage(String message) {
        String[] words = message.split("\\s+");
        Set<String> validWords = getEnglishWords();

        int validWordCount = 0;
        for (String word : words) {
            if (validWords.contains(word.toLowerCase())) {
                validWordCount++;
            }
        }

        // Return true if more than half of the words are valid
        return validWordCount > words.length / 2;
    }

    // Get a basic set of English words (can be expanded with a larger dictionary)
    public static Set<String> getEnglishWords() {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("the");
        dictionary.add("is");
        dictionary.add("a");
        dictionary.add("in");
        dictionary.add("and");
        dictionary.add("to");
        dictionary.add("of");
        dictionary.add("it");
        dictionary.add("you");
        dictionary.add("for");
        dictionary.add("with");
        dictionary.add("quick");
        dictionary.add("brown");
        dictionary.add("fox");
        dictionary.add("jumps");
        dictionary.add("over");
        dictionary.add("lazy");
        dictionary.add("dog");

        return dictionary;
    }
}



  // Decrypted with key1 = 14 and key2 = 24: Mcx gvfz jy mcx Cvov Htnvjm bn Ynfx. Rhzhzxz!


