import Assignment2.CaesarCipher;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String message = "Txhegofatxo my daughter";
        CaesarCipher cc = new CaesarCipher(3);
        System.out.println("original message: " + message);
        System.out.println("Encrypted message: " + cc.encrypt(message));
        System.out.println("decrypted message: " + cc.decrypt(cc.encrypt(message)));
    }
}


