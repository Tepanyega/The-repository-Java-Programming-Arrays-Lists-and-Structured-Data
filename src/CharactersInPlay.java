import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CharactersInPlay {
    private Map<String, Integer> characterMap; // Map for character name and count

    // Constructor to initialize the map
    public CharactersInPlay() {
        characterMap = new HashMap<>();
    }

    // Step 1: Update the counts of characters
    private void update(String person) {
        characterMap.put(person, characterMap.getOrDefault(person, 0) + 1);
    }

    // Step 2: Read the file and extract characters
    public void findAllCharacters(String filename) throws FileNotFoundException {
        characterMap.clear();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Trim spaces

                if (line.contains(".")) {
                    int periodIndex = line.indexOf(".");
                    String character = line.substring(0, periodIndex).toUpperCase(); // Extract character name

                    if (character.length() > 1) { // Ignore very short names
                        update(character);
                    }
                }
            }
        }
    }

    // Step 3: Print main characters with high speaking counts
    public void tester(String filename, int minCount) throws FileNotFoundException {
        findAllCharacters(filename);
        System.out.println("Characters with " + minCount + " or more speaking parts:");

        for (Map.Entry<String, Integer> entry : characterMap.entrySet()) {
            if (entry.getValue() >= minCount) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
        }
    }

    // Step 4: Print characters with speaking parts between num1 and num2
    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("\nCharacters with speaking parts between " + num1 + " and " + num2 + ":");

        for (Map.Entry<String, Integer> entry : characterMap.entrySet()) {
            int count = entry.getValue();
            if (count >= num1 && count <= num2) {
                System.out.println(entry.getKey() + "\t" + count);
            }
        }
    }
}
