import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordFrequencies{
    private static ArrayList<String> myWords;//to store unique words from a file
    private static ArrayList<Integer> myFreequency;//should represent the number of times the kth word in myWords

    public  WordFrequencies(){

        myFreequency = new ArrayList<>();
        myWords = new ArrayList<>();
    }

    //read words from a file and count uniqueness
    //This should first clear  both my words and my...
    public static void findUnique(String fileName) throws FileNotFoundException {
        myFreequency.clear();
        myWords.clear();

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        //reading words from file
        while(scanner.hasNext()){
            String word = scanner.next().toLowerCase();//convert to lower cases

            //remove punctuations
           // word = word.replaceAll("[^a-zA-Z]", "");

            if (!word.isEmpty()){
                if (myWords.contains(word)){
                    int index = myWords.indexOf(word);
                    myFreequency.set(index, myFreequency.get(index) + 1);
                }else {
                    myWords.add(word);
                    myFreequency.add(1);
                }
            }
        }
        scanner.close();

    }

    //find the index of the most frequent word
    public int findIndexOfMax(){
        if (myFreequency.isEmpty()) return  -1;
        int maxIndex = 0;
        for (int i = 0; i < myFreequency.size(); i++){
            if (myFreequency.get(i) > myFreequency.get(maxIndex)){
                maxIndex = i;
            }
        }
        return  maxIndex;
    }

    //test the functionality
    public void tester(String fileName) throws FileNotFoundException {
        findUnique(fileName);
        System.out.println("Number of Unique words:" + myWords.size());
        //print the words and their frequencies
        for (int i = 0; i < myWords.size(); i++){
            System.out.println(myFreequency.get(i) + " " + myWords.get(i));
        }

        //find and print the most frequent word
        int maxIndex = findIndexOfMax();
        if (maxIndex != -1){
            System.out.println("The word that occurs most often and its count are: " +
                    myWords.get(maxIndex)+ " " + myFreequency.get(maxIndex));
        }
    }

}
