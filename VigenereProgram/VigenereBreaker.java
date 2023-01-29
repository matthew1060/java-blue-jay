import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        String newMessage = "";
        for(int i = whichSlice; i < message.length(); i+=totalSlices){
            newMessage += message.charAt(i);
        }
        
        
        return newMessage;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker ck = new CaesarCracker(mostCommon);
        
        for(int i = 0; i < klength; i++){
            String newMessage = sliceString(encrypted, i, klength);
            int newKey = ck.getKey(newMessage);
            key[i] = newKey;
        }
        
        
        
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE]
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        
        // for dictionary 
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            String name = f.getName();
            FileResource dict = new FileResource(f);
            HashSet<String> dictionary = readDictionary(dict);
            languages.put(name, dictionary);
        }
        
        String decrypted = breakForAllLangs(encrypted, languages);
        
    }
    
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for(String s : fr.lines()){
            s = s.toLowerCase();
            words.add(s);
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for(String word : message.split("\\W+")){
            word = word.toLowerCase();
            // Flag contains might not work with strings??? check at compilation
            if(dictionary.contains(word)){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int index = 0;
        int current = 0;
        char mostCommon = mostCommonCharIn(dictionary);
        
        for(int i = 1; i < 100; i++){
            //System.out.println(i);
            int[] keyLength = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keyLength);
            String decrypted = vc.decrypt(encrypted);
            int numWords= countWords(decrypted, dictionary);
            if(numWords > current){
                current = numWords;
                index = i;
            }
        }
        
        //index = 38;
        int[] keyLength = tryKeyLength(encrypted, index, mostCommon);
        VigenereCipher vc = new VigenereCipher(keyLength);
        String decrypted = vc.decrypt(encrypted);
        //current = countWords(decrypted, dictionary);
        //System.out.println("index :" + index);
        //System.out.println("matched words : " + current);
        return decrypted;
    }
    
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] countAlph = new int[26];
        Arrays.fill(countAlph, 0);
        for(String s : dictionary){
            s = s.toLowerCase();
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                int index = alph.indexOf(c);
                //System.out.println(index);
                if(index != -1)
                    countAlph[index]++;
            }
        }
        int max =  0;
        int index = 0;
        for(int i = 0; i < countAlph.length; i++){
            int num = countAlph[i];
            if(num > max){
                max = num;
                index = i;
            }
        }
        char mostCommon = alph.charAt(index);
        return mostCommon;
    }
    
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxValue = 0;
        HashMap<String, String> maxInLang = new HashMap<String, String>(); 
        for(String s : languages.keySet()){
            HashSet<String> dictionary = languages.get(s);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int numWords= countWords(decrypted, dictionary);
            maxInLang.put(s, decrypted);
            if(maxValue < numWords){
                maxValue = numWords;
            }
        }
        String finalDecrypted = "";
        String finalLanguage = "";
        
        for(String s : maxInLang.keySet()){
            HashSet<String> dictionary = languages.get(s);
            int numWords= countWords(maxInLang.get(s), dictionary);
            if(numWords == maxValue){
                finalDecrypted += maxInLang.get(s);
                finalLanguage += s;
                break;
            }
        }
        System.out.println("Language :::: " + finalLanguage);
        System.out.println(finalDecrypted);
        return finalDecrypted;
    }
    
    
    
   
    
    public void tester(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int klength = 4;
        int[] key = tryKeyLength(encrypted, klength, 'e');
        for(int i = 0; i < klength; i++)
            System.out.println(key[i]);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
