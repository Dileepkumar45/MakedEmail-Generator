

	import org.apache.commons.lang3.RandomStringUtils;

	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.List;
	import java.util.concurrent.ThreadLocalRandom;

	public class TestDataGenerator {

	    private static final String NAMES_FILE_PATH = "names.txt";
	    private static final String DOMAINS_FILE_PATH = "domains.txt";

	    public static void main(String[] args) {
	        generateTestData("email");
	        generateTestData("date of birth");
	        generateTestData("address");
	        generateTestData("phone number");
	    }

	    public static void generateTestData(String dataType) {
	        switch (dataType) {
	            case "email":
	                String email = generateRandomEmail();
	                System.out.println("Generated email: " + email);
	                break;
	            case "date of birth":
	                String dob = generateRandomDOB();
	                System.out.println("Generated date of birth: " + dob);
	                break;
	            case "address":
	                String address = generateRandomAddress();
	                System.out.println("Generated address: " + address);
	                break;
	            case "phone number":
	                String phoneNumber = generateRandomPhoneNumber();
	                System.out.println("Generated phone number: " + phoneNumber);
	                break;
	            default:
	                System.out.println("Unsupported data type.");
	        }
	    }

	    public static String generateRandomEmail() {
	        List<String> domains = readLinesFromFile(DOMAINS_FILE_PATH);
	        List<String> names = readLinesFromFile(NAMES_FILE_PATH);
	        String domain = domains.get(ThreadLocalRandom.current().nextInt(domains.size()));
	        String name = names.get(ThreadLocalRandom.current().nextInt(names.size()));
	        String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(5); // Added for uniqueness
	        return name.toLowerCase() + "." + randomAlphanumeric + "@" + domain.toLowerCase();
	    }

	    public static String generateRandomDOB() {
	        long minDate = parseDate("1950-01-01").getTime();
	        long maxDate = new Date().getTime();
	        long randomDateMillis = ThreadLocalRandom.current().nextLong(minDate, maxDate);
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        return sdf.format(new Date(randomDateMillis));
	    }

	    public static String generateRandomAddress() {
	        return RandomStringUtils.randomAlphanumeric(10) + " " +
	                RandomStringUtils.randomAlphanumeric(10) + ", " +
	                RandomStringUtils.randomAlphanumeric(10) + ", " +
	                ThreadLocalRandom.current().nextInt(10000, 99999);
	    }

	    public static String generateRandomPhoneNumber() {
	        return "(" + ThreadLocalRandom.current().nextInt(100, 999) + ")" +
	                ThreadLocalRandom.current().nextInt(100, 999) + "-" +
	                ThreadLocalRandom.current().nextInt(1000, 9999);
	    }

	    private static Date parseDate(String dateStr) {
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            return sdf.parse(dateStr);
	        } catch (Exception e) {
	            return null;
	        }
	    }

	    private static List<String> readLinesFromFile(String filePath) {
	        try {
	            return Files.readAllLines(Path.of(filePath));
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + e.getMessage());
	            return List.of(); // Return an empty list on error
	        }
	    }
	}

