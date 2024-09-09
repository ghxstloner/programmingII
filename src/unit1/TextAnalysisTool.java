package unit1;

import java.util.*;

public class TextAnalysisTool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Get user input
        System.out.println("Please enter a paragraph or lengthy text: ");
        String text = scanner.nextLine();

        // Step 2: Perform analysis
        System.out.println("Total number of characters: " + characterCount(text));
        System.out.println("Total number of words: " + wordCount(text));
        System.out.println("Most common character: " + mostCommonCharacter(text));

        // Step 3: Get user input for character frequency
        System.out.println("Enter a character to find its frequency: ");
        char charToFind = scanner.nextLine().charAt(0);
        System.out.println("Frequency of '" + charToFind + "': " + characterFrequency(text, charToFind));

        // Step 4: Get user input for word frequency
        System.out.println("Enter a word to find its frequency: ");
        String wordToFind = scanner.nextLine();
        System.out.println("Frequency of \"" + wordToFind + "\": " + wordFrequency(text, wordToFind));

        // Step 5: Display number of unique words
        System.out.println("Number of unique words: " + uniqueWordCount(text));

        scanner.close();
    }

    // Function to count total number of characters
    public static int characterCount(String text) {
        return text.length();
    }

    // Function to count total number of words
    public static int wordCount(String text) {
        String[] words = text.split("\\s+"); // Split by whitespace
        return words.length;
    }

    // Function to find the most common character (case-insensitive)
    public static char mostCommonCharacter(String text) {
        text = text.replaceAll("\\s", "").toLowerCase(); // Remove spaces and convert to lowercase
        Map<Character, Integer> charCountMap = new HashMap<>();

        for (char c : text.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        char mostCommon = ' ';
        int maxCount = -1;

        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommon = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostCommon;
    }

    // Function to check the frequency of a specific character (case-insensitive)
    public static int characterFrequency(String text, char character) {
        text = text.toLowerCase();
        character = Character.toLowerCase(character);
        int frequency = 0;

        for (char c : text.toCharArray()) {
            if (c == character) {
                frequency++;
            }
        }

        return frequency;
    }

    // Function to check the frequency of a specific word (case-insensitive)
    public static int wordFrequency(String text, String word) {
        String[] words = text.toLowerCase().split("\\s+");
        word = word.toLowerCase();
        int frequency = 0;

        for (String w : words) {
            if (w.equals(word)) {
                frequency++;
            }
        }

        return frequency;
    }

    // Function to calculate the number of unique words (case-insensitive)
    public static int uniqueWordCount(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        return uniqueWords.size();
    }
}
