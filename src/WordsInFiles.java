import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordFileMap;

    //constructor to initialise the hash map
    public WordsInFiles(){
        wordFileMap = new HashMap<>();
    }

    //add words from the file to the map
    private void addWordsFromFile(File f){
        //this method should add all the words from file into the map
        try{
            Scanner scanner = new Scanner(f);
            while(scanner.hasNext()){
                String word = scanner.next();
                // If word is not in the map, create a new entry
                wordFileMap.putIfAbsent(word, new ArrayList<>());

                // Add the file name if it's not already in the list
                ArrayList<String> fileList = wordFileMap.get(word);
                if (!fileList.contains(f.getName())) {
                    fileList.add(f.getName());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + f.getName());
        }
    }

    //building file word map
    public void buildWordFileMap(String directoryPath){
        wordFileMap.clear();

        File folder = new File(directoryPath);
        File[] files = folder.listFiles();
        if (files != null){
            for(File file : files){
                if (file.isFile()){
                    addWordsFromFile(file);
                }
            }
        }
    }

    public int maxNumber(){
        int max = 0;
        for (ArrayList<String> fileList : wordFileMap.values()){
            if (fileList.size() > max){
                max = fileList.size();
            }
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entry : wordFileMap.entrySet()){
            if (entry.getValue().size() == number){
                words.add(entry.getKey());
            }
        }
        return words;
    }
    public void printFilesIn(String word){
        if (wordFileMap.containsKey(word)) {
            for (String fileName : wordFileMap.get(word)){
                System.out.println(fileName);
            }
        }else{
            System.out.println("Word not found in any file.");
        }
    }

    public void tester(String directoryPath) {
        buildWordFileMap(directoryPath);
        int maxFiles = maxNumber();
        System.out.println("Maximum number of files any word appears in: " + maxFiles);
        ArrayList<String> maxWords = wordsInNumFiles(maxFiles);
        System.out.println("Words that appear in the most files (" + maxFiles + " files): " + maxWords);

        for (String word : maxWords) {
            System.out.println("Files containing word \"" + word + "\":");
            printFilesIn(word);
            System.out.println();
        }
    }
}
