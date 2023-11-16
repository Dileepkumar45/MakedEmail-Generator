
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TempMailWithMasking {

    public static void main(String[] args) {
        try {
            String temporaryEmail = generateTemporaryEmail();
            String maskedEmail = maskEmail(temporaryEmail);

            System.out.println("Generated temporary email: " + maskedEmail);

            // Simulate sending an email to the temporary email address
            // ...

            // Check the inbox for the temporary email
            checkInbox(temporaryEmail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateTemporaryEmail() throws IOException {
        String apiUrl = "https://www.1secmail.com/api/v1/?action=genRandomMailbox";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            Scanner scanner = new Scanner(conn.getInputStream());
            if (scanner.hasNext()) {
                return scanner.next();
            }
        }

        throw new IOException("Unable to generate temporary email address.");
    }

    public static void checkInbox(String temporaryEmail) throws IOException {
        String apiUrl = "https://www.1secmail.com/api/v1/?action=getMessages&login=" + temporaryEmail.split("@")[0] + "&domain=" + temporaryEmail.split("@")[1];
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            Scanner scanner = new Scanner(conn.getInputStream());
            if (scanner.hasNext()) {
                String response = scanner.next();
                System.out.println("Inbox response:\n" + response);
            }
        } else {
            System.out.println("No messages in the inbox.");
        }
    }

    public static String maskEmail(String email) {
        if (email != null && !email.isEmpty()) {
            int atIndex = email.indexOf("@");
            if (atIndex > 0) {
                String maskedPart = maskPart(email.substring(0, atIndex));
                String domain = email.substring(atIndex);
                return maskedPart + domain;
            }
        }
        return email;
    }

    public static String maskPart(String part) {
        int maskedLength = Math.max(part.length() / 2, 1);
        String maskedChars = "*".repeat(maskedLength);
        return part.substring(0, part.length() - maskedLength) + maskedChars;
    }
}