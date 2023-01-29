
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String s : fr.lines()){
             records.add(WebLogParser.parseEntry(s));
             
         }
            
         
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs= new ArrayList<String>();
         
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         System.out.println("Printing all those with status codes higher than " + num);
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status > num){
                 System.out.println(le);
             }
         }
         System.out.println("");
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String date = (le.getAccessTime()).toString();
             if(date.indexOf(someday) != -1){
                 String ipAddr = le.getIpAddress();
                 if(!uniqueIPs.contains(ipAddr)){
                     uniqueIPs.add(ipAddr);
                 }
             }
            }
            return uniqueIPs;
     }
     
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status >= low && status <= high){
                 String ipAddr = le.getIpAddress();
                 if(!uniqueIPs.contains(ipAddr)){
                     uniqueIPs.add(ipAddr);
                 }
             }
         }
         return uniqueIPs.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip,1);
             }
             else{
                 counts.put(ip, counts.get(ip)+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 0;
         for(String s : counts.keySet()){
             int temp = counts.get(s);
             if(max < temp){
                 max = temp;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         int max = mostNumberVisitsByIP(counts);
         ArrayList<String> mostVisitIP = new ArrayList<String>();
         for(String s :  counts.keySet()){
             int temp = counts.get(s);
             if(temp == max){
                 mostVisitIP.add(s);
             }
         }
         return mostVisitIP;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> daysIPs = new HashMap<String, ArrayList<String>>();
         
         for(LogEntry le : records){
             String date = ((le.getAccessTime()).toString()).substring(4,10);
             String ipAddr = le.getIpAddress();
             if(!daysIPs.containsKey(date)){
                 ArrayList<String> ipList = new ArrayList<String>();
                 ipList.add(ipAddr);
                 daysIPs.put(date, ipList);
             }
             else{
                 ArrayList temp = daysIPs.get(date);
                 temp.add(ipAddr);
                 daysIPs.put(date, temp);
             }
             
         }
         return daysIPs;
     }
     
     public int mostNumberVisitsInDay(HashMap<String, ArrayList<String>> counts){
         int max = 0;
         for(String s : counts.keySet()){
             int temp = counts.get(s).size();
             if(max < temp){
                 max = temp;
             }
         }
         return max;
     }
     
     
     
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysIPs){
         String most = "";
         int max = mostNumberVisitsInDay(daysIPs);
         for(String s : daysIPs.keySet()){
             int temp = daysIPs.get(s).size();
             if(temp == max){
                 most = most + s;
                 // wont work if multiple right answers hence put the break!!
                 break;
             }
         }
         return most;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysIPs, String day){
         ArrayList<String> ipList = daysIPs.get(day);
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         
         for(String s : ipList){
             if(!ipCounts.containsKey(s)){
                 ipCounts.put(s, 1);
             }
             else{
                 ipCounts.put(s, ipCounts.get(s)+1);
             }
         }
         
         return iPsMostVisits(ipCounts);
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
