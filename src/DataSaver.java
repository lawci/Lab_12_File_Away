import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

public class DataSaver {
    public static void main(String[] args) {
        ArrayList<String> records = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean moreRecords = true;
        while (moreRecords) {
            // Collect data using SafeInput methods
            String firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");

            // Get ID with validation for 6 digits
            int idNum = SafeInput.getRangedInt(scanner, "Enter ID Number (6 digits)", 1, 999999);
            String id = String.format("%06d", idNum);

            String email = SafeInput.getNonZeroLenString(scanner, "Enter Email");

            // Get birth year with reasonable range (1900-current year)
            int currentYear = java.time.Year.now().getValue();
            int birthYear = SafeInput.getRangedInt(scanner, "Enter Year of Birth (4 digits)", 1900, currentYear);

            // Create CSV record
            String record = String.format("%s, %s, %s, %s, %d",
                    firstName, lastName, id, email, birthYear);
            records.add(record);

            // Ask if user wants to add more records using Y/N confirmation
            moreRecords = SafeInput.getYNConfirm(scanner, "Add another record?");
        }

        // Save to file
        String filename = SafeInput.getNonZeroLenString(scanner, "Enter filename to save (without extension)") + ".csv";
        Path filePath = Paths.get("./src/" + filename);

        try {
            Files.write(filePath, records, StandardOpenOption.CREATE);
            System.out.println("Data successfully saved to " + filePath);

            // Show confirmation message with the saved data
            System.out.println("\nSaved Records:");
            System.out.println("--------------");
            for (String record : records) {
                System.out.println(record);
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}