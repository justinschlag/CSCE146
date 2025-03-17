//Justin Schlag 2025
//Word Helper Lab01CSCE146
public class WordHelper {

    // Returns a new array sorted by the number of vowels (ascending)
    public static String[] sortByVowels(String[] words) {
        // Make a copy of the original array
        String[] copy = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            copy[i] = words[i];
        }

        // Bubble sort based on vowel count
        for (int i = 0; i < copy.length - 1; i++) {
            for (int j = 0; j < copy.length - i - 1; j++) {
                if (countVowels(copy[j]) > countVowels(copy[j + 1])) {
                    String temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
            }
        }
        return copy;
    }

    // Returns a new array sorted by the number of consonants (ascending)
    public static String[] sortByConsonants(String[] words) {
        // Make a copy of the original array
        String[] copy = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            copy[i] = words[i];
        }

        // Bubble sort based on consonant count
        for (int i = 0; i < copy.length - 1; i++) {
            for (int j = 0; j < copy.length - i - 1; j++) {
                if (countConsonants(copy[j]) > countConsonants(copy[j + 1])) {
                    String temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
            }
        }
        return copy;
    }

    // Returns a new array sorted by word length (ascending)
    public static String[] sortByLength(String[] words) {
        // Make a copy of the original array
        String[] copy = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            copy[i] = words[i];
        }

        // Bubble sort based on the length of each word
        for (int i = 0; i < copy.length - 1; i++) {
            for (int j = 0; j < copy.length - i - 1; j++) {
                if (copy[j].length() > copy[j + 1].length()) {
                    String temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
            }
        }
        return copy;
    }

    // Counts vowels in a word (a, e, i, o, u, y)
    private static int countVowels(String word) {
        int count = 0;
        word = word.toLowerCase(); // Make it case-insensitive
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' ||
                c == 'u' || c == 'y') {
                count++;
            }
        }
        return count;
    }

    // Counts consonants in a word by subtracting vowel count from word length
    private static int countConsonants(String word) {
        return word.length() - countVowels(word);
    }
}
