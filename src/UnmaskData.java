import java.util.Base64;

public class UnmaskData {
    // Function to unmask the data
    public static String unmaskData(String maskedData, String key) {
        // Decode the Base64 encoded masked data to get the original encoded data
        byte[] decodedBytes = Base64.getDecoder().decode(maskedData);
        String encodedData = new String(decodedBytes);

        // Create a StringBuilder to store the original data
        StringBuilder originalData = new StringBuilder();

        // Iterate through each character of the encoded data
        for (int i = 0; i < encodedData.length(); i++) {
            // XOR the character of the encoded data with the corresponding character of the key
            char originalChar = (char) (encodedData.charAt(i) ^ key.charAt(i % key.length()));
            
            // Append the unmasked character to the original data
            originalData.append(originalChar);
        }

        // Convert the StringBuilder to a string and return the original data
        return originalData.toString();
    }

    public static void main(String[] args) {
        // Input: maskedData (the data that has been masked) and key (the key used for masking)
        String maskedData = "HSscKxEDZhkQGRJVEAN3QTgiHyheE1Y/";
        String key = "YJpGpp9Ruja9uq78";
        
        // Call the unmaskData function to get the original data
        String originalData = unmaskData(maskedData, key);
        
        // Print the original data to the console
        System.out.println("Original Data: " + originalData);
    }
}

