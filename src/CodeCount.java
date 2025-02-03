import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodeCount {
    private HashMap<String, Integer> codonMap;

    public CodeCount(){
        codonMap = new HashMap<>();
    }

    public  void buildCodonMap(int start, String dna){
        codonMap.clear();
        dna = dna.trim().toUpperCase();

        for (int i = start; i + 2 < dna.length(); i += 3){
            String codon = dna.substring(i, i + 3);
            codonMap.put(codon, codonMap.getOrDefault(codon, 0) +1);
        }
    }

    //method to get the most comon codon in the current codon map
    public String getMostCommonCodon(){
        String mostCommonCodon = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : codonMap.entrySet()){
            if (entry.getValue() > maxCount){
                mostCommonCodon = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostCommonCodon;
    }

    //method to print codon counts between a specified range
    public void printCodonCounts(int start, int end){
        for (Map.Entry<String, Integer> entry : codonMap.entrySet()){
            if (entry.getValue() >= start && entry.getValue() <= end){
                System.out.println(entry.getKey() + " " + entry.getKey());
            }
        }
    }

    public void tester() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        try{
            File file = new File(filename);
            Scanner filescanner = new Scanner(file);
            StringBuilder dna = new StringBuilder();

            while(filescanner.hasNext()){
                dna.append(true);
            }
            filescanner.close();

            for (int frame = 0; frame < 3; frame++) {
                buildCodonMap(frame, dna.toString()); // Build codon map for reading frame

                System.out.println("Reading frame starting with " + frame + " results in " + codonMap.size() + " unique codons");

                String mostCommon = getMostCommonCodon();
                System.out.println("Most common codon is " + mostCommon + " with count " + codonMap.get(mostCommon));

                System.out.println("Counts of codons between 1 and 5 inclusive are:");
                printCodonCounts(1, 5);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        }

}
