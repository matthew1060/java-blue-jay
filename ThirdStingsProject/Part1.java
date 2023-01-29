
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part1 {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
        
    }
    
    
    
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 ||
             (tgaIndex != -1 && tgaIndex < taaIndex)) {
                 minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)) {
                minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
            
            
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        
        while ( true ) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();
            
        }
        
    }
    
    public StorageResource getAllGenes( String dna ) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        
        while ( true ) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            //System.out.println(currentGene);
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();
            
        }
        return geneList;
    }
    
    
    public double cgRatio (String dna) {
        int count = 0;
        char c = 'C';
        char g = 'G';
        int stringLength = dna.length();
        
        for (int i = 0; i < stringLength; i++) {
            if(dna.charAt(i) == c || dna.charAt(i) == g){
                count++;
            }
        }
        double ratio = (double) count/stringLength;
        return ratio;
        
    }
    
    public int countCTG (String dna) {
        int count = 0;
        int startIndex = 0;
        //int stringLength = dna.length();
        int currIndex = dna.indexOf("CTG", startIndex);
        while (currIndex != -1){
            count++;
            currIndex = dna.indexOf("CTG", currIndex+1);
        }
        //double ratio = (double) count/ stringLength;
        return count;
    }
    
    public void processGenes(StorageResource sr) {
        //StorageResource genes = getAllGenes(sr);
        String longestString = "";
        int countLongerThan9 = 0;
        int countGreaterCGRatio = 0;
        // for longer than 9 characters
        System.out.println("Strings greater than 9 characters:");
        int countLoop = 0;
        for (String g: sr.data()) {
            if(g.length() > 60){
                countLongerThan9++;
                System.out.println(g);
            }
            
            if(countLoop == 0){
                longestString = g;
            }
            else if (g.length() > longestString.length()){
                longestString =  g;
            }
            countLoop++;
            
            //System.out.println(g);
        }
        System.out.println("Number of Strings above 9 chars =" + 
                            countLongerThan9); 
        
        
        // for c-g-ratio
        System.out.println("Strings with C-G ratio above 0.35:");
        for (String g: sr.data()) {
            double stringCGRatio = cgRatio(g);
            if(stringCGRatio > 0.35){
                countGreaterCGRatio++;
                System.out.println(g);
            }
            
            //System.out.println(g);
        }
        System.out.println("Number of Strings above 0.35 c-g-ratio =" + 
                            countGreaterCGRatio); 
        
        System.out.println("Length of Longest String =" +
                            longestString.length());
        
    }
    
    
    
    
    public void testCGRatio(){
        String test = "ATGCCATAG";
        float answer = (float) cgRatio(test);
        System.out.println(answer);
    }
    
    
    
    
    
    public void testOn(String dna) {
        System.out.println("Testing getAllGenes");
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            
            System.out.println(g);
        }
    }
    
    
    public void testProcessGenes(){
        //String dna = "aaaaaaaaatgccctaaaaaaatgaaaaaataaaa";
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println(dna);
        StorageResource geneList = getAllGenes(dna.toUpperCase());
        
        System.out.println("Gene List Size:" + geneList.size());
        int numberCTG = countCTG(dna.toUpperCase());
        System.out.println("Number of times CTG appears:" + numberCTG);
        
        processGenes(geneList);
        
        
    }
    

}
