
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;


public class CodonCount {
    
    private HashMap<String, Integer> myMap;
    
    public CodonCount(){
        myMap = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna){
        myMap.clear();
        for(int i = start; i < (dna.length()-2); i+=3){
            String temp = dna.substring(i, i+3);
            if(myMap.containsKey(temp)){
                myMap.put(temp, (myMap.get(temp)+1));
            }
            else{
                myMap.put(temp, 1);
            }
            
        }
        
    }
    
    private String getMostCommonCodon(){
        String largestKey = "";
        int largest = Collections.max(myMap.values());
        int check = 0;
        for(String s : myMap.keySet()){
            if(myMap.get(s) == largest && check == 0){
                largestKey += s; 
                check = 1;
            }
            
        }
        return largestKey;
    }
    
    public void printCodonCounts(int start, int end){
        for(String s : myMap.keySet()){
            int pos = myMap.get(s);
            
            if(pos >= start && pos <= end){
                System.out.println(s + " " + pos);
            }
        }
        System.out.println("size :" + myMap.size());
    }
    
    public void test(){
        
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        
        //String dna = "CGTTCAAGTTCAA";
        int pos = 2;
        
        buildCodonMap(pos, dna);
        
        int start = 1;
        int end = 50;
        String mostCommon = getMostCommonCodon();
        System.out.println("Most Common is : " + mostCommon);
        
        printCodonCounts(start, end);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
