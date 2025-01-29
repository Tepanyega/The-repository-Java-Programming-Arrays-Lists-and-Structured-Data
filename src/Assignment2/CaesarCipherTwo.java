package Assignment2;


public class CaesarCipherTwo {

    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";;
    private static String shiftedAlphabet1, shiftedAlphabet2;
    private int key1;
    private int key2;

    //getting the constructor
    public CaesarCipherTwo(int key1, int key2){
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(1, key2);
        this.key1 = key1;
        this.key2 = key2;
    }
    //I have to encrypt this, assume characters are all in uppercase
    public String encrypt(String input){
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i<input.length();i++){
           // converting them to uppercase
            if (i % 2 == 0){ //this is even
                if (Character.isUpperCase(input.charAt(i))){
                    int idx = alphabet.indexOf(input.charAt(i));
                    char encryptedChar = shiftedAlphabet1.charAt(idx);
                    encryptedMessage.append(encryptedChar);
                }else if (Character.isLowerCase(input.charAt(i))) {
                        int idx = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
                        char encryptedChar = Character.toLowerCase(shiftedAlphabet1.charAt(idx));
                        encryptedMessage.append(encryptedChar);
                }else {
                    encryptedMessage.append(input.charAt(i));
                }
            }else {
                if (Character.isUpperCase(input.charAt(i))){
                    int idx = alphabet.indexOf(input.charAt(i));
                    char encryptedChar = shiftedAlphabet2.charAt(idx);
                    encryptedMessage.append(encryptedChar);
                }else if (Character.isLowerCase(input.charAt(i))) {
                    int idx = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
                    char encryptedChar = Character.toLowerCase(shiftedAlphabet2.charAt(idx));
                    encryptedMessage.append(encryptedChar);
                }else {
                    encryptedMessage.append(input.charAt(i));
                }
            }

        }
        return encryptedMessage.toString();
    }

    // Decrypts the input message
    public String decrypt(String input) {
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (i % 2 == 0) { // Use key1 for even indices
                decryptedMessage.append(decryptChar(currentChar, shiftedAlphabet1));
            } else { // Use key2 for odd indices
                decryptedMessage.append(decryptChar(currentChar, shiftedAlphabet2));
            }
        }
        return decryptedMessage.toString();
    }

    // Helper method to decrypt a character based on a shifted alphabet
    private char decryptChar(char ch, String shiftedAlphabet) {
        if (Character.isUpperCase(ch)) {
            return alphabet.charAt(shiftedAlphabet.indexOf(ch));
        } else if (Character.isLowerCase(ch)) {
            int idx = shiftedAlphabet.indexOf(Character.toUpperCase(ch));
            return Character.toLowerCase(alphabet.charAt(idx));
        } else {
            return ch; // Non-alphabetic characters are returned unchanged
        }
    }

}
