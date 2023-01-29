
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
import java.util.*;

public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreq;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreq = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource resource = new FileResource();
        myWords.clear();
        myFreq.clear();
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreq.add(1);
            } else {
                int value = myFreq.get(index);
                myFreq.set(index, value+1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        int max = 0;
        int index = 0;
        
        for(int i = 0; i < myWords.size(); i++){
            System.out.println(myFreq.get(i) + "\t" + myWords.get(i));
            int temp = myFreq.get(i);
            if(max < temp){
                max = temp;
                index = i;
            }
        }
        System.out.println("Most common word:" + myWords.get(index)+"\t"+ max);
        
        System.out.println("hello");
        System.out.println("# of Unique words: " + myWords.size());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
