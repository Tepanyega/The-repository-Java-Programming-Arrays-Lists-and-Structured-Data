import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    //constructor to initialize the arralist
    public LogAnalyzer(){
        records = new ArrayList<>();
    }

    //count unique IP address
    public int countUniqueIPs(){
        HashSet<String> uniqueIPs = new HashSet<>();
        for (LogEntry entry : records){
            uniqueIPs.add(entry.getIpAddress());
        }
        return uniqueIPs.size();
    }
    // Print log entries with status code higher than num
    public void printAllHigherThanNum(int num) {
        for (LogEntry entry : records) {
            if (entry.getStatusCode() > num) {
                System.out.println(entry);
            }
        }
    }

    // Get unique IPs that visited on a given day
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        HashSet<String> uniqueIPs = new HashSet<>();
        for (LogEntry entry : records) {
            String date = entry.getAccessTime().toString();
            if (date.contains(someday)) {
                uniqueIPs.add(entry.getIpAddress());
            }
        }
        return new ArrayList<>(uniqueIPs);
    }


    // Count unique IPs in status code range
    public int countUniqueIPsInRange(int low, int high) {
        HashSet<String> uniqueIPs = new HashSet<>();
        for (LogEntry entry : records) {
            int statusCode = entry.getStatusCode();
            if (statusCode >= low && statusCode <= high) {
                uniqueIPs.add(entry.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }

    //read logs file and stores entries in the list
    public void readFile(String filename) {
        records = new ArrayList<>(); // Initialize ArrayList
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                LogEntry entry = WebLogParser.parseEntry(line);
                if (entry == null) {
                    System.out.println("Failed to parse log entry: " + line);
                } else {
                    records.add(entry);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    //prints all log entries
    public void printAll(){
        for (LogEntry le : records){
            System.out.println(le);
        }
    }
}
