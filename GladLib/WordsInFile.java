
/**
 * Write a description of WordsInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;


public class WordsInFile {
    
    private HashMap<String, ArrayList<String>> myMap;
    //private ArrayList<String> fileNames;
    
    public WordsInFile(){
        //fileNames = new ArrayList<String>();
        myMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        
        String name = f.getName();
        for(String s : fr.words()){
            if(myMap.containsKey(s)){
                //myMap.put(s, (myMap.get(s).add(name)));
                if(myMap.get(s).contains(name)){
                    
                }else{
                    myMap.get(s).add(name);
                }
                
            }
            else{
                ArrayList<String> fileNames = new ArrayList<String>(); 
                fileNames.add(name);
                myMap.put(s, fileNames);
                //myMap.put(s, (myMap.get(s).add(name)));
            }
        }
        
                    
        
    }
    
    
    public void buildWordFileMap(){
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int maxNum = 0;
        for (String s : myMap.keySet()){
            int temp = myMap.get(s).size();
            if(maxNum < temp){
                maxNum = temp;
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for(String s : myMap.keySet()){
            int temp = myMap.get(s).size();
            if(temp == number){
                words.add(s);
            }
        }
        return words;
    }
    
    public void printFilesIn(String word){
        for(String s : myMap.keySet()){
            if(s.equals(word)){
                ArrayList<String> temp = myMap.get(s);
                for(String name : temp){
                    System.out.println(name);
                }
            }
        }
    }
    
    
    
    
    public void tester(){
        buildWordFileMap();
        ArrayList<String> temp = wordsInNumFiles(4);
        System.out.println(temp.size());
        printFilesIn("tree");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
