
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
import java.util.*;


public class CharactersInPlay {
    
    private ArrayList<String> characterNames;
    private ArrayList<Integer> myFreq;
    
    public CharactersInPlay(){
        characterNames = new ArrayList<String>();
        myFreq = new ArrayList<Integer>();
    }
    
    public void update(String person){
        person = person.toLowerCase();
        int index = characterNames.indexOf(person);
        if (index == -1){
            characterNames.add(person);
            myFreq.add(1);
        } else {
            int value = myFreq.get(index);
            myFreq.set(index, value+1);
        }
    }
    
    public void findAllCharacters(){
        FileResource resource = new FileResource();
        characterNames.clear();
        myFreq.clear();
        for (String s : resource.lines()){
            int index = s.indexOf(".");
            if(index != -1){
                //update(s);
            
                String temp = s.substring(0, index);
                update(temp);
                
            }
            
        }
        
    }
    
    public void characterWithNumParts(int num1, int num2){
        System.out.println("Nums in the range given below: ");
        for(int i = 0; i < characterNames.size(); i++){
            int temp = myFreq.get(i);
            if(temp >= num1 && temp <= num2){
                System.out.println(characterNames.get(i) + " " + temp);
            }
        }
    }
    
    
    
    
    public void tester(){
        int max = 0;
        int index = 0;
        findAllCharacters();
        //System.out.println("");
        for(int i = 0; i < characterNames.size(); i++){
            //System.out.println(myFreq.get(i) + "\t" + characterNames.get(i));
            int temp = myFreq.get(i);
            if(max < temp){
                max = temp;
                index = i;
            }
        }
        System.out.println("most freq char: "+ characterNames.get(index) + " "+ max);
        characterWithNumParts(10,15);    
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
