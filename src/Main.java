import Assignment1.WordLengths;
import Assignment2.CaesarBreaker;
import Assignment2.TwoKeysDecript;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        WordLengths.testCountWordLengths();

        //Count Letter Frequencies
        //Decrypt a Message with One Key
        //Split the Encrypted Message into Two Halves
        //Determine the Keys Used for Each Half
        //Decrypt the Message Encrypted with Two Keys
        String message = "sam";
        int key = 3;
        String h =TwoKeysDecript.encrypt(message, key);
        System.out.println("\n" + h);
        System.out.println("\n decrypt: " );
        CaesarBreaker.testDecrypt();

        System.out.println("Lawl my mind is fucked i can barely keep track of what am doing");
    }
}