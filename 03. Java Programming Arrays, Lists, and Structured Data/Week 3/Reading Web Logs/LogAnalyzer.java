
/**
 * LogAnalyzer class contains various filtering methods on IP records data such as 
 * countUniqueIPs(), printAllHigherThanNum(), uniqueIPVisitsOnDay(), countUniqueIPsInRange(),
 * countVisitsPerIP(), mostNumberVisitsByIP(), iPsMostVisits(), iPsForDays(), dayWithMostIPVisits(),
 * iPsWithMostVisitsOnDay().
 * 
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (October 29, 2019)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
     private ArrayList <LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList <LogEntry> ();
     }
        
     public void readFile(String filename) {
         FileResource resource = new FileResource (filename);
         
         for (String line : resource.lines()) {
             LogEntry logEntry = WebLogParser.parseEntry(line);
             records.add(logEntry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs () {
         ArrayList <String> uniqueIPs = new ArrayList <String> ();
         
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             if (! uniqueIPs.contains(ipAddress)) {
                 uniqueIPs.add(ipAddress);
             }
         }
         
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum (int num) {
         System.out.println("List of log entries with status code higher than " + num + " :");
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             
             if (statusCode > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList uniqueIPVisitsOnDay (String someday) {
         ArrayList <String> uniqueIPVisitsOnDay = new ArrayList <String> ();
         
         for (LogEntry le : records) {
             String dateLong = le.getAccessTime().toString();
             String dateShort = dateLong.substring(4,10);
             
             String ipAddress = le.getIpAddress();
             
             if (dateShort.equals(someday) && !uniqueIPVisitsOnDay.contains(ipAddress)) {
                 uniqueIPVisitsOnDay.add(ipAddress);
             }
         }
         
         return uniqueIPVisitsOnDay;
     }
     
     public int countUniqueIPsInRange (int low, int high) {
         ArrayList <String> UniqueIPsInRange = new ArrayList <String> ();
         
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             
             String ipAddress = le.getIpAddress();
             
             if (statusCode >= low && statusCode <= high 
                 && !UniqueIPsInRange.contains(ipAddress)) {
                    UniqueIPsInRange.add(ipAddress); 
             }
         }
         
         return UniqueIPsInRange.size();
     }
     
     public HashMap <String, Integer> countVisitsPerIP () {
         HashMap<String,Integer> counts = new HashMap<String,Integer> ();
         
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             
             if (!counts.containsKey(ip)) {
                 counts.put(ip,1);
             }
             else {
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         
         return counts;
     }
     
     public int mostNumberVisitsByIP (HashMap<String,Integer> map) {
         int maxCount = 0;
         
         for (String key : map.keySet()) {
             int value = map.get(key);
             
             if (value > maxCount) {
                 maxCount = value;
             }
         }
         
         return maxCount;
     }
     
     public ArrayList<String> iPsMostVisits (HashMap<String,Integer> map) {
         ArrayList<String> iPsMostVisits = new ArrayList<String> ();
         
         int mostVisits = mostNumberVisitsByIP(map);
         
         for (String key : map.keySet()) {
             int value = map.get(key);
             
             if (value == mostVisits) {
                 iPsMostVisits.add(key);
             }
         }
         
         return iPsMostVisits;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays () {
         HashMap<String,ArrayList<String>> iPsForDays = new HashMap<String,ArrayList<String>> ();
         
         for (LogEntry le : records) {
             String dateLong = le.getAccessTime().toString();
             String dateShort = dateLong.substring(4,10);
             
             String iP = le.getIpAddress();
             
             if (! iPsForDays.containsKey(dateShort)) {
                 ArrayList<String> iPs = new ArrayList<String> ();
                 iPs.add(iP);
                 iPsForDays.put(dateShort, iPs);
             }
             else {
                 iPsForDays.get(dateShort).add(iP);
             }
         }
         
         return iPsForDays;
     }
     
     public String dayWithMostIPVisits (HashMap<String,ArrayList<String>> myMap) {
         int maxCount = 0;
         String dayWithMostIPVisits = "";
         
         for (String key : myMap.keySet()) {
             ArrayList<String> arrayList = myMap.get(key);
             int arrayListSize = arrayList.size();
             
             if (arrayListSize > maxCount) {
                 maxCount = arrayListSize;
                 dayWithMostIPVisits = key;
             }
         }
         
         return dayWithMostIPVisits;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String,ArrayList<String>> myMap, 
         String date) {
         ArrayList<String> iPsWithMostVisitsOnDay = new ArrayList<String> ();
         HashMap<String,Integer> counts = new HashMap<String,Integer> ();
         
         for (String key : myMap.keySet()) {
             if (key.equals(date)) {
                 ArrayList<String> arrayList = myMap.get(key);
                 
                 for (int k=0; k < arrayList.size(); k++) {
                     String iP = arrayList.get(k);
                     
                     if (!counts.containsKey(iP)) {
                         counts.put(iP, 1);
                     }
                     else {
                         counts.put(iP, counts.get(iP) + 1);
                     }
                 }
             }
         }
         
         int mostVisits = mostNumberVisitsByIP(counts);
         
         for (String key : counts.keySet()) {
             int value = counts.get(key);
             
             if (value == mostVisits) {
                 iPsWithMostVisitsOnDay.add(key);
             }
         }
         
         return iPsWithMostVisitsOnDay;
     }
}
