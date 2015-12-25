import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Segmentation {
    private static Map<Integer, String> memory = new HashMap<>();
    private static int words = Integer.MAX_VALUE;
    private static Trie trie = Trie.fillTrie();

    public String segment(String str) {
        segmentHelper(0, str.toLowerCase(), str.length(), "");
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

        for (Integer key : keys) {
            if (minimumKey > key) {
                minimumKey = key;
            }
        }
        return minimumKey;
    }

    private void segmentHelper(int lvl, String str, int len, String result) {
        for (int i = 1; i <= len; i++) {
            String prefix = str.substring(0, i);

            if (isWord(prefix)) {
                if (i == len) {
                    result += prefix;
                    if (countWords(result) < words) {
                        memory.put(countWords(result), result);
                        words = countWords(result);
                    }
                }
                segmentHelper(lvl + 1, str.substring(i, len), len - i, result + prefix + (" "));
            }
        }
    }
}