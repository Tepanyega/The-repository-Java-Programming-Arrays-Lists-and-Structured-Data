import java.util.Date;

public class WebLogParser {
    public static LogEntry parseEntry(String logLine) {
        try {
            String[] parts = logLine.split(" ");
            if (parts.length < 6) return null;

            String ipAddress = parts[0];
            String month = parts[1];
            String day = parts[2];
            String time = parts[3];
            String method = parts[4];
            String url = parts[5];
            int statusCode = Integer.parseInt(parts[6]);
            int bytesReturned = Integer.parseInt(parts[7]);

            // Create a dummy date (you may replace this with proper parsing)
            Date accessTime = new Date();

            return new LogEntry(ipAddress, accessTime, method + " " + url, statusCode, bytesReturned);
        } catch (Exception e) {
            return null; // If parsing fails, return null
        }
    }
}
