import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isLeaf;

    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    protected static Trie fillTrie() {
        Trie t = new Trie();
        HashSet<String> dic = DictionaryHelper.getDictionarySet();
        if (dic == null) return null;
        for (String s : dic) {
            t.insert(s);
        }
        return t;
    }

    public static int getDicSize() {
        HashSet<String> dic = DictionaryHelper.getDictionarySet();
        return dic != null ? dic.size() : -1;
    }

    private void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;

            if (i == word.length() - 1)
                t.isLeaf = true;
        }
    }

    // Returns true if the word is in the trie
    protected boolean search(String word) {
        TrieNode t = searchNode(word);
        return t != null && t.isLeaf;
    }

    private TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.children;
            } else {
                return null;
            }
        }
        return t;
    }
}