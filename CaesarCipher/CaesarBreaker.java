
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;


public class CaesarBreaker {
    
    public String decrypt(String encrypted) {
        int key = 4;
        CaesarCipher cc = new CaesarCipher();
        //String message = cc.encrypt(encrypted, 26 - key);
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        System.out.println("freq : " + maxDex);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - ( 4 - maxDex);
        }
        //System.out.println("here");
        return cc.encrypt(encrypted, (26 - dkey));
        
    }
    
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k = 0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public void testDecrypt(){
        //FileResource resource = new FileResource();
        //String decrypted = decrypt(resource.asString());
        String message = "hello world";
        //CaesarCipher cc = new CaesarCipher();
        //String message = cc.encrypt
        //String encrypted = cc.encrypt(message, 4);
        String decrypted = decrypt("jgtg");
        System.out.println(decrypted);
        
    }
    
    
    
    public String halfOfString(String message, int start){
        String newMessage = "";
        for(int i = start; i < message.length(); i += 2){
            newMessage = newMessage + message.charAt(i); 
        }
        
        return newMessage;
        
        
    }
    
    public void testHalfOf(){
        String part1 = halfOfString("Qbkm Zgis", 0);
        String part2 = halfOfString("Qbkm Zgis", 1);
        
        System.out.println("A : " + part1);
        System.out.println("B : " + part2);
        
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - ( 4 - maxDex);
        }
        return dkey;
    }
    
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String s1 = halfOfString(encrypted, 0);
        System.out.println(s1);
        String s2 = halfOfString(encrypted, 1);
        int key1 = getKey(s1);
        
        int key2 = getKey(s2);
        System.out.println("key 1 :" + key1);
        System.out.println("key 2 :" + key2);
        String decrpt1 = cc.encrypt(s1, (26 - key1));
        String decrpt2 = cc.encrypt(s2, (26 - key2));
        int count1 = 0;
        int count2 = 0;
        String newMessage = "";
        for(int i = 0; i < encrypted.length(); i++){
            if(i % 2 == 0){
                newMessage = newMessage + decrpt1.charAt(count1);
                count1++;
            } else {
                newMessage = newMessage + decrpt2.charAt(count2);
                count2++;
            }
        }
        return newMessage;
    }
    
    public void testDecryptTwokeys(){
        
        FileResource message = new FileResource();
        
        String decrypted = decryptTwoKeys(message.asString());
        //String decrypted = decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
        System.out.println(decrypted);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
