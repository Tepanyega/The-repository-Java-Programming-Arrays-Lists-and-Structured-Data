package Assignment1;

public class WordPlay {
    public static boolean isVowel(char ch){
       ch = Character.toLowerCase(ch); //character is a built-in method to work with characters
        return ch == 'a' || ch == 'e' ||ch == 'i' || ch == 'o' || ch == 'u';
    }
    //testing the isVowel method
    public static void tester(){
        System.out.println(isVowel('F')); //this should return false
        System.out.println(isVowel('a')); //this should return true
    }
    //replace vowels with ch else leave the way it is
    public static String replaceVowels(String phrase, char ch){
        StringBuilder results = new StringBuilder();

        for (int i = 0;i<phrase.length();i++){
            if (isVowel(phrase.charAt(i))){
                results.append(ch);
            }else{
                results.append(phrase.charAt(i));
            }
        }
        return results.toString();

    }
    //check if the character is ch then is replaced with * or +
    public static String emphasize(String phrase, char ch){
        StringBuilder results = new StringBuilder();

        for (int i = 0;i<phrase.length();i++){

            //check character is == ch
            if(phrase.charAt(i) == ch){
                //if true the check if the index is odd
                if(i % 2 == 0){
                    results.append('*');
                }else {  //it means its odd
                    results.append('+');
                }
            }else {
                results.append(phrase.charAt(i));
            }
        }
        return results.toString();
    }

}
