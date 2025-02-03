import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Gladlib {
        private ArrayList<String> adjectiveList;
        private ArrayList<String> nounList;
        private ArrayList<String> colorList;
        private ArrayList<String> countryList;
        private ArrayList<String> nameList;
        private ArrayList<String> animalList;
        private ArrayList<String> timeList;
        private ArrayList<String> verbList;
        private ArrayList<String> fruitList;
        private ArrayList<String> seenWords;
        private Random myRandom;



        private String dataSourceDirectory = "C:\\Users\\giles\\Java-Programming-Arrays-Lists-and-Structured-Data\\public";


        public Gladlib(){
            initializeFromSource(dataSourceDirectory);
            myRandom = new Random();
            seenWords = new ArrayList<>();
        }

        public Gladlib(String source){
            initializeFromSource(source);
            myRandom = new Random();
            myRandom = new Random();
            seenWords = new ArrayList<>();
        }

    private void initializeFromSource(String source) {
        adjectiveList = readIt( "C:\\Users\\giles\\Java-Programming-Arrays-Lists-and-Structured-Data\\public\\adjective.txt");
        nounList = readIt(source + "\\noun.txt");
        colorList = readIt(source + "\\color.txt");
        countryList = readIt(source + "\\country.txt");
        nameList = readIt(source + "\\name.txt");
        animalList = readIt(source + "\\animal.txt");
        timeList = readIt(source + "\\time.txt");
        verbList = readIt(source + "\\verb.txt");
        fruitList = readIt(source + "\\fruit.txt");
    }


    private String randomFrom(ArrayList<String> source){
        if (source.isEmpty()) {
            return "**UNKNOWN**"; // Prevent crashes if a file is empty
        }
            int index = myRandom.nextInt(source.size());
            return source.get(index);
        }

        private String getSubstitute(String label){
            if (label.equals("country")){
                return randomFrom(countryList);
            }if (label.equals("color")){
                return randomFrom(colorList);
            }if (label.equals("noun")) {
                return randomFrom(nounList);
            }if (label.equals("name")) {
                return randomFrom(nameList);
            }if (label.equals("adjective")) {
                return randomFrom(adjectiveList);
            }if (label.equals("animal")) {
                return randomFrom(animalList);
            }if (label.equals("timeframe")) {
                return randomFrom(timeList);
            }if (label.equals("verb")) {
                return randomFrom(verbList);
            }if (label.equals("fruit")) { // NEW: Handles <fruit>
                return randomFrom(fruitList);
            }if (label.equals("number")) {
                return String.valueOf(myRandom.nextInt(50) + 5);

            }
            return "**UNKNOWN**";
        }

    private ArrayList<String> readIt(String filename) {
        ArrayList<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine().trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return list;
    }

    private String processWord(String word) {
        if (word.startsWith("<") && word.endsWith(">")) {
            String label = word.substring(1, word.length() - 1);
            String substitute;

            do {
                substitute = getSubstitute(label);
            } while (seenWords.contains(substitute)); // Ensure unique words

            seenWords.add(substitute); // Mark as used
            return substitute;
        }
        return word;
    }

    public void makeStory() {
        seenWords.clear(); // Reset used words before generating a new story
        StringBuilder story = new StringBuilder();
        int wordCount = 0;

        try (Scanner scanner = new Scanner(new File(dataSourceDirectory + "/madtemplate2.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");

                for (String word : words) {
                    story.append(processWord(word)).append(" ");
                    if (word.startsWith("<") && word.endsWith(">")) {
                        wordCount++;
                    }
                }
                story.append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading template file.");
        }

        System.out.println(story.toString().trim());
        System.out.println("\nTotal words replaced: " + wordCount);
    }


}
