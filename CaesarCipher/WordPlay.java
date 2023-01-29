
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class WordPlay {
    
    
    
    
    public boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        if(ch =='a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
            return true;
        }
        else{
            return false;
        
        }
        
    }
    
    
    public String replaceVowels(String phrase, char ch){
        String newPhrase = "";
        int length = phrase.length();
        for(int i = 0; i < length; i++){
            char currChar = phrase.charAt(i);
            if(isVowel(currChar)){
                newPhrase = newPhrase + ch;
            } 
            else{
                newPhrase = newPhrase + currChar;
            }
        }        
        return newPhrase;
    }
    
    
    public String emphasize(String phrase, char ch){
        String newPhrase = "";
        int length = phrase.length();
        for(int i = 0; i < length; i++){
            char currChar = phrase.charAt(i);
            char temp1 = Character.toLowerCase(currChar);
            char temp2 = Character.toLowerCase(ch);
            if(temp1 == temp2){
                if((i+1) % 2 == 0){
                    newPhrase = newPhrase + '+';
                }
                else{
                    newPhrase = newPhrase + '*';
                }
            }
            else{
                newPhrase = newPhrase + currChar;
            }
            
            
        }
        return newPhrase;
    }
    
    
    
    
    
    public void testing(){
        String phrase = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(phrase);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
