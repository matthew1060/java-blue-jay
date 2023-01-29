
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log.txt");
        log.printAll();
    }
    
    
    public void testUniqueIP(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        //log.printAll();
        //int uniqueIPs = log.countUniqueIPs();
        //System.out.println("The number of unique ip's is : " + uniqueIPs);
        //log.printAllHigherThanNum(400);
        //ArrayList<String> unique = log.uniqueIPVisitsOnDay("Mar 24");
        //System.out.println(unique.size());
        int num = log.countUniqueIPsInRange(200, 299);
        System.out.println(num);
        //ArrayList<String> visits = log.uniqueIPVisitsOnDay("Sep 27");
        //System.out.println(visits.size());
    }
    
    
    
    public void tester(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        //HashMap<String, Integer> hash = log.countVisitsPerIP();
        //int num = log.mostNumberVisitsByIP(hash);
        //ArrayList<String> most = log.iPsMostVisits(hash);
        //System.out.println(most);
        HashMap<String, ArrayList<String>> hash = log.iPsForDays();
        //String day = log.dayWithMostIPVisits(hash);
        ArrayList<String> ips = log.iPsWithMostVisitsOnDay(hash, "Sep 29");
        System.out.println(ips);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
