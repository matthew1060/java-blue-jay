
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    
    private ArrayList<String> seenWords;
    private ArrayList<String> usedLabels;
    private int counter;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        seenWords = new ArrayList<String>();
        usedLabels = new ArrayList<String>();
        counter = 0;
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        seenWords = new ArrayList<String>();
        usedLabels = new ArrayList<String>();
        counter = 0;
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        
        String[] labels = {"adjective", "noun", "color", "country", "name",
                            "animal", "timeframe", "verb", "fruit"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            //System.out.println("hello");
            myMap.put(s, list);
        }
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if(!usedLabels.contains(label)){
            usedLabels.add(label);
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        counter++;
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int check = 0;
        //System.out.println(seenWords.indexOf(sub));
        while(check == 0){
            if(!seenWords.contains(sub)){
                seenWords.add(sub);
                check = 1;
                //System.out.println(sub + "yess");
            }
            else{
                sub = getSubstitute(w.substring(first+1,last));
                //System.out.println(sub);
            }
        }
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        seenWords.clear();
        usedLabels.clear();
        counter = 0;
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int count = 0;
        for(String s : myMap.keySet()){
            int temp = myMap.get(s).size();
            count += temp;
            
        }
        return count;
    }
    
    
    
    private int totalWordsConsidered(){
        int count = 0;
        for(String s : myMap.keySet()){
            if(usedLabels.contains(s)){
                int temp = myMap.get(s).size();
                count += temp;
            }
        }
        return count;
        
    }
    
    
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("replaced words = " + counter);  
        int totalWordsMap = totalWordsInMap();
        int totalWordsCon = totalWordsConsidered();
        System.out.println("Total words in the map : " + totalWordsMap);
        System.out.println("Total words considered by labels: " + totalWordsCon);
    }
    


}
