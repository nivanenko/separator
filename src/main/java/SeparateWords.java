import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SeparateWords {
    static Trie t;
    static Map<Integer, String> wordsMap;
    static int wordsQuantity = Integer.MAX_VALUE;
    static StringBuilder prefix;

    public static void main(String[] args) {
        t = Trie.fillTrie();
        wordsMap = new HashMap<>();
        prefix = new StringBuilder();

        String test =
                "foodbridgecoolbadbedgoalaimobjectiveboygirldogcatlock" +
                        "keyforkclearboardchairtabledeskcore";
        // Console input
//        System.out.print("Enter string to separate words: ");
//        String input = inputString();
        System.out.println("Result: " + separateWords(test));
    }

    private static boolean isWord(String str) {
        return t.search(str);
    }

    // for console input
    private static String inputString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static int countWords(String str) {
        int count = 1;
        for (int i = 0; i <= str.length() - 1; i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') {
                count++;
            }
        }
        return count;
    }

    // get separated string with the least quantity of words
    private static Integer getMinKey() {
        int minimumKey = Integer.MAX_VALUE;
        Set<Integer> keys = wordsMap.keySet();

        for (Integer key : keys) {
            if (minimumKey > key) {
                minimumKey = key;
            }
        }
        return minimumKey;
    }

    public static String separateWords(String str) {
        getSeparatedWords(0, str.toLowerCase(), str.length(), "");
        return wordsMap.get(getMinKey());
    }

    private static void getSeparatedWords(int lvl, String str, int length, String result) {
        for (int i = 1; i <= length; i++) {
            prefix.setLength(0);
            prefix.append(str.substring(0, i));

            if (isWord(prefix.toString())) {
                if (i == length) {
                    result += prefix;
                    if (countWords(result) < wordsQuantity) { // adding to HashMap all variants
                        wordsMap.put(countWords(result), result); // excluding variants with less words then the previous one
                        wordsQuantity = countWords(result);
                    //    System.out.println("res: " + result); // for testing
                    }
                }
                getSeparatedWords(lvl + 1, str.substring(i, length), length - i, result + prefix + (" "));
            }
        }
    }
}