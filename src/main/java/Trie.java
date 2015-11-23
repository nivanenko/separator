import java.util.ArrayList;
import java.util.HashMap;
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

    private Trie() {
        root = new TrieNode();
    }

    // Inserts the word into the trie
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

    // Fill the structure with the vocabulary's words
    protected static Trie fillTrie() {
        Trie t = new Trie();
        ArrayList<String> dic = Dictionary.setDictionary();
        for (String s : dic) {
            t.insert(s);
        }
        return t;
    }
}