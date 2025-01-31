import java.util.ArrayList;
import java.util.Random;

public class Gladlib {
        private ArrayList<String> adjectiveList;
        private ArrayList<String> nounList;
        private ArrayList<String> colorList;
        private ArrayList<String> countryList;
        private ArrayList<String> nameList;
        private ArrayList<String> animalList;
        private ArrayList<String> timeList;
        private ArrayList<String> verbList;
        private Random myRandom;

        private String dataSourceURL = "";
        private String dataSourceDirectory = "";


        public Gladlib(){
            initializeFromSource(dataSourceDirectory);
            myRandom = new Random();
        }

        public Gladlib(String source){
            initializeFromSource(source);
            myRandom = new Random();
        }

        private void initializeFromSource(String source){
             adjectiveList = readIt(source + "/adjective.txt");
             nounList = readIt(source+ "/");
             colorList = readIt(source+ "/");
             countryList = readIt(source+ "/");
             nameList = readIt(source+ "/");
             animalList = readIt(source+ "/");
             timeList = readIt(source+ "/");
             verbList = readIt(source+ "/");

        }

        private String randomFrom(ArrayList<String> source){
            int index = myRandom.nextInt(source.size());
            return source.get(index);
        }

        private String getSubstitute(String label){
            if (label.equals("country")){
                return randomFrom(countryList);
            }if (label.equals("color")){
                return randomFrom(colorList);
            }if (label.equals("noun")) {
                return randomFrom(nameList);
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
            }if (label.equals("number")) {
                return " " + myRandom.nextInt(50) + 5;
            }
            return "**UNKNOWN**";
        }

}
