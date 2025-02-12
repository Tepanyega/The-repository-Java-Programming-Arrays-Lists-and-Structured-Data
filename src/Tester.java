public class Tester {
    public void testUniqueIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("C:\\Users\\samue\\OneDrive\\Desktop\\The-repository-Java-Programming-Arrays-Lists-and-Structured-Data\\public\\weblog-short_log.txt");
        analyzer.printAll();
        System.out.println("Number of unique IPs: " + analyzer.countUniqueIPs());

    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");
        System.out.println("Entries with status code > 200:");
        analyzer.printAllHigherThanNum(200);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog-short_log");
        System.out.println("Unique IPs on Sep 14: " + analyzer.uniqueIPVisitsOnDay("Sep 14"));
        System.out.println("Unique IPs on Sep 30: " + analyzer.uniqueIPVisitsOnDay("Sep 30"));
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");
        System.out.println("Unique IPs in range 200-299: " + analyzer.countUniqueIPsInRange(200, 299));
        System.out.println("Unique IPs in range 300-399: " + analyzer.countUniqueIPsInRange(300, 399));
    }

}
