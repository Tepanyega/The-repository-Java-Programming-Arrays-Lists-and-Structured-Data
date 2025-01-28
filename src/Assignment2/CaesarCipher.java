package Assignment2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaesarCipher {
    //I have to encrypt this, assume characters are all in uppercase
    public static String encrypt(String input, int key){
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i<input.length();i++){
            //converting them to uppercase
            if (Character.isLowerCase(input.charAt(i))){
                //take it to upper case
                char encryptedChar = (char) ((Character.toUpperCase(input.charAt(i)) - 'A' + key) % 26 + 'A');
                encryptedMessage.append(encryptedChar);
               //handling both Uppercase and lowercase ,
                //then you will comment line "19", and uncomment line 23
                //char encryptedChar = (char) ((input.charAt(i) - 'a' + key) % 26 + 'a');

            } else if (Character.isUpperCase(input.charAt(i))) {
                char encryptedChar = (char)((input.charAt(i) - 'A' + key) % 26 + 'A');
                encryptedMessage.append(encryptedChar);
            }else {
                encryptedMessage.append(input.charAt(i));
            }
        }
        return encryptedMessage.toString();
    }

    //tester method
    //Reading a file and encrypting the characters in it
    public static void testCaesar(){

        int key = 3;
        try{
            Path path = Paths.get("C:\\Users\\giles\\Java-Programming-Arrays-Lists-and-Structured-Data\\public\\file.txt");
            //reading it as a string
            String message = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

            String encrypted = encrypt(message, key);
            //print the encrypted message and teh key used
            System.out.println("key is " + key + "\n" + encrypted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //encrypt using two keys
    public static String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encryptedMessage = new StringBuilder();

        //the only way to go around this is to check odd and even
        //and encrypt accordingly
        for (int i = 0; i<input.length();i++){

            if (i % 2 == 0){//this is odd

                //catering for both upper and lower cases
                if(Character.isLowerCase(input.charAt(i))){
                    char encryptedChar = (char) ((input.charAt(i) - 'a' + key1) % 26 + 'a');
                    encryptedMessage.append(encryptedChar);
                }else {
                    char encryptedChar = (char) ((input.charAt(i) - 'A' + key1) % 26 + 'A');
                    encryptedMessage.append(encryptedChar);
                }

            }else { //means its odd, then ill be using key2

                //catering for both upper and lower cases
                if(Character.isLowerCase(input.charAt(i))){
                    char encryptedChar = (char) ((input.charAt(i) - 'a' + key2) % 26 + 'a');
                    encryptedMessage.append(encryptedChar);
                }else {
                    char encryptedChar = (char) ((input.charAt(i) - 'A' + key2) % 26 + 'A');
                    encryptedMessage.append(encryptedChar);
                }
            }
        }
        return encryptedMessage.toString();
    }



}
