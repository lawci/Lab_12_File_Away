import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser("./src");
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            Path filePath = fileChooser.getSelectedFile().toPath();

            try {
                List<String> lines = Files.readAllLines(filePath);
                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;

                System.out.println("File Contents:");
                System.out.println("--------------");

                for (String line : lines) {
                    System.out.println(line);
                    lineCount++;

                    // Count words (split by whitespace)
                    String[] words = line.split("\\s+");
                    wordCount += words.length;

                    // Count characters (including spaces)
                    charCount += line.length();
                }

                // Print summary report
                System.out.println("\nFile Summary Report:");
                System.out.println("---------------------");
                System.out.println("File Name: " + filePath.getFileName());
                System.out.println("Number of Lines: " + lineCount);
                System.out.println("Number of Words: " + wordCount);
                System.out.println("Number of Characters: " + charCount);

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}