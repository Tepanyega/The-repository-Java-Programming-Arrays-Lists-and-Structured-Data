import Assignment2.CaesarCipher;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String message = "FIRST LEGION ATTACK EAST FLANK!";
        int key = 3;
        System.out.println(CaesarCipher.encrypt(message, key));
        System.out.println("\n" + "Testing by reading from file: "+ "\n");
        CaesarCipher.testCaesar();
        System.out.println("\n" + "Encrypt using two keys");
        System.out.println(CaesarCipher.encryptTwoKeys(message, key, 4)); //please check the spaces when using the two keys;
    }
}