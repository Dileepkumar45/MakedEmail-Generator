import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class EmailValidation {
    public static void main(String[] args) {
        try {
            // Replace with the actual API endpoint to check inbox messages
            URL url = new URL("https://api.onesecmail.com/inbox");

            // Replace with the disposable email address you want to check
            String disposableEmail = "your-random-disposable-email@onesecmail.com";

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Set request headers, authentication, etc., if required by the API

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                // Read and process the inbox messages (JSON response)
                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();

                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }

                scanner.close();

                String inboxMessages = response.toString();

                // Implement your validation logic based on inboxMessages
                System.out.println("Inbox Messages: " + inboxMessages);
                // Implement your validation logic here
            } else {
                System.err.println("Failed to check inbox messages. Response code: " + responseCode);
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

