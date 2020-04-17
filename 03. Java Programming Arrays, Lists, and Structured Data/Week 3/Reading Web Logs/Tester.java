
/**
 * Tester class contains various testing scenarios for LogAnalyzer methods.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (October 29, 2019)
 */

import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        la.printAll();
    }
    
    public void testUniqueIP () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        int uniqueIPs = la.countUniqueIPs ();
        System.out.println("There are " + uniqueIPs + " IPs");
    }
    
    public void testPrintAllHigherThanNum () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        
        String date = "Sep 27";
        ArrayList array = la.uniqueIPVisitsOnDay(date);
        System.out.println("Unique IP addresses with visits on " + date + ": " + array);
        System.out.println("Its size is " + array.size());
    }
    
    public void testCountUniqueIPsInRange () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        
        int low = 400;
        int high = 499;
        int uniqueIPsInRange = la.countUniqueIPsInRange(low, high);
        System.out.println("There are " + uniqueIPsInRange + " IPs in range b/w " +
                           low + " & " + high);
    }
    
    public void testCountVisitsPerIP () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        
        HashMap<String,Integer> counts = la.countVisitsPerIP ();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        
        HashMap<String,Integer> counts = la.countVisitsPerIP ();
        int mostVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Most number of visits by IP in the file is " + mostVisits);
    }
    
    public void testIpsMostVisits () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        
        HashMap<String,Integer> counts = la.countVisitsPerIP ();
        ArrayList<String> iPsMostVisits = la.iPsMostVisits(counts);
        System.out.println("IPs with the most number of visits: " + iPsMostVisits);
    }
    
    public void testIpsForDays () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
        System.out.println("Following IPs were visiting on those days: ");
        System.out.println(myMap);
    }
    
    public void testDayWithMostIPVisits () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
        String dayWithMostIPVisits = la.dayWithMostIPVisits(myMap);
        System.out.println("The day with most IP visits: " + dayWithMostIPVisits);
    }
    
    public void testIpsWithMostVisitsOnDay () {
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("data/weblog2_log");
        
        String date = "Sep 29";
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
        ArrayList<String> iPsWithMostVisitsOnDay = la.iPsWithMostVisitsOnDay(myMap, date);
        System.out.println("IPs with the most visits on " + date + ": " + iPsWithMostVisitsOnDay);
    }
}
