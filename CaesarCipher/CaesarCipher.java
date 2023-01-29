
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class CaesarCipher {
    
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean checkUpper = Character.isUpperCase(currChar);
            int idx = -1;
            if (checkUpper == false){
                
                idx = alphabet.toLowerCase().indexOf(currChar);     
            }
            else{
                idx = alphabet.indexOf(currChar);
            }

            
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);   
                if(checkUpper){
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    char temp = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, temp);
                }
                
                
            }
        }
        return encrypted.toString();     
    }
    
    
    
    
    
    
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean checkUpper = Character.isUpperCase(currChar);
            int idx = -1;
            if (checkUpper == false){
                
                idx = alphabet.toLowerCase().indexOf(currChar);     
            }
            else{
                idx = alphabet.indexOf(currChar);
            }

            
            if(idx != -1){
                if(i%2 == 0){
                    char newChar = shiftedAlphabet1.charAt(idx);   
                    if(checkUpper){
                        encrypted.setCharAt(i, newChar);
                    }
                    else{
                        char temp = Character.toLowerCase(newChar);
                        encrypted.setCharAt(i, temp);
                    }    
                }
                else{
                    char newChar = shiftedAlphabet2.charAt(idx);   
                    if(checkUpper){
                        encrypted.setCharAt(i, newChar);
                    }
                    else{
                        char temp = Character.toLowerCase(newChar);
                        encrypted.setCharAt(i, temp);
                    }
                }
                
                
                
            }
        }
        return encrypted.toString();     
    }
    
    
    
    
    
    
    public void testCaesar(){
        //FileResource fr = new FileResource();
        int key = 17;
        //String message = fr.asString();
        //String encrypted = encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8);
        String encrypted = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15);
        System.out.println(encrypted);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
