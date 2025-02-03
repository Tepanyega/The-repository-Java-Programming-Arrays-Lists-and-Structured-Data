import java.io.File;
import java.util.HashMap;

public class WordFrequencyMap {

    public void countWords(String fileSource){
        File file = new File(fileSource);
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        int total = 0;

//        for (String w : file.words()){
//            w = w.toLowerCase();
//            if (map.keySet().contains(w)){
//                map.put(w,map.get(w) + 1);
//            }else{
//                map.put(w,1);
//            }
//
//        }
       for (String w: map.keySet()){
           int occurences = map.get(w);
           if (occurences > 500){
               System.out.println(occurences + "\t" + w);
           }
       }
    }
}
