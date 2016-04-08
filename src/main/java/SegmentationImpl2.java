import java.util.HashMap;
import java.util.Set;

public class SegmentationImpl2 {
    private Trie trie;
    private static HashMap<Integer, String> memory;
    private static int words;

    public static HashMap<Integer, String> getMemory() {
        return memory;
    }

    public SegmentationImpl2() {
        trie = Trie.fillTrie();
        memory = new HashMap<>();
        words = Integer.MAX_VALUE;
    }

    public String segmentString(String str) {
        segment(str);
        return memory.get(getMinKey());
    }

    private boolean isWord(String str) {
        return trie.search(str);
    }

    private int countWords(String str) {
        int count = 1;
        for (int i = 0; i <= str.length() - 1; i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') {
                count++;
            }
        }
        return count;
    }

    private Integer getMinKey() {
        int minimumKey = Integer.MAX_VALUE;
        Set<Integer> keys = memory.keySet();
        memory.entrySet();

        for (Integer key : keys) {
            if (minimumKey > key) {
                minimumKey = key;
            }
        }
        return minimumKey;
    }

    private boolean[] segmentHelper(String s) {
        boolean[] segmentDP = new boolean[s.length() + 1];

        segmentDP[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String word = s.substring(j, i);
                if (segmentDP[j] && isWord(word)) {
                    segmentDP[i] = true;
                    break;
                }
            }
        }//end for i
        return segmentDP;
    }

    private HashMap<Integer, String> segment(String s) {
        // check if this string can be broken, what will save a lot of time
        boolean[] isBroken = segmentHelper(s);

        if (!isBroken[s.length()]) {
            return memory;
        }

        segmentIntoWords(s, 0, isBroken, new StringBuilder());
        return memory;
    }

    private void segmentIntoWords(String s, int start, boolean[] isBroken, StringBuilder wordStr) {
        if (start >= s.length()) {
            if (countWords(wordStr.toString()) < words) {
                memory.put(countWords(wordStr.toString()), wordStr.toString());
                words = countWords(wordStr.toString());
                System.err.println("Memory size: " + memory.size());
            }
            return;
        }

        int step = start;

        while (step < s.length()) {
            // first check if string [0, step + 1) can be broken
            if (!isBroken[step + 1]) {
                step++;
                continue;
            }
            // even if string [0, step + 1) can be broken, we still need to
            // check if the string [start, step + 1) is a valid word.
            String word = s.substring(start, step + 1);

            if (!isWord(word)) {
                step++;
                continue;
            }
            // remember original length, when backtracking, it is easy to get to the original state
            int len = wordStr.length();

            if (wordStr.length() == 0) {
                wordStr.append(word);
            } else {
                wordStr.append(" ").append(word);
            }

            segmentIntoWords(s, step + 1, isBroken, wordStr);

            wordStr.setLength(len);
            step++;
        }
    }
}