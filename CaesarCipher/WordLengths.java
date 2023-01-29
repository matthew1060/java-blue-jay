
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class WordLengths {
    
    
    public void countWordLengths(FileResource resource, int[] counts) {
        
        for(String word : resource.words()){
            word = word.toLowerCase();
            //System.out.println(word);
            int length = word.length();
            if(length == 1 && Character.isDigit(word.charAt(0))){
                
                
                
            }else{
                if(!Character.isLetter(word.charAt(0)) && !Character.isLetter(word.charAt(length-1))) {
                    length = length - 2;
                    //System.out.println("-2");
                }
                else if (!Character.isLetter(word.charAt(0)) || !Character.isLetter(word.charAt(length-1))){
                    length = length - 1;
                    //System.out.println("-1");
                }   
                //System.out.println(word);
                counts[length]++;
                
            }
            
           
            
            //System.out.println(length);
        }
 
    }
    
    public int indexOfMax(int[] values){
        int maxIndex = 0;
        int maxVal = values[0];
        for(int i = 0; i < values.length; i++){
            if(maxVal < values[i]){
                maxIndex = i;
                maxVal = values[i];
            }
            
        }
        return maxIndex;
    }
    
    
    
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        
        for(int i = 0; i < counts.length; i++){
            System.out.println(counts[i] + " words of length " + i);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
