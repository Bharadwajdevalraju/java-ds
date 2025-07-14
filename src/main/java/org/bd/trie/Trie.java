package org.bd.trie;

import java.util.HashMap;

public class Trie {

    private Character c;
    private final HashMap<Character, HashMap> root;

    public Trie() {
        root = new HashMap<>();
    }

    public void addWord(final String word) {
        char[] letters = word.toCharArray();
        var current = root;
        for (char c : letters) {
            if (!current.containsKey(c)) {
                current.put(c, new HashMap<Character, HashMap>());
            }
            current = current.get(c);
        }
        current.put('0', null);
    }

    public boolean search(final String word) {
        char[] letters = word.toCharArray();
        var current = root;
        for (char c : letters) {
            if (!current.containsKey(c)) {
                return false;
            }
            current = current.get(c);
        }
        return current.containsKey('0');
    }

    public boolean findByPrefix(final String word) {
        char[] letters = word.toCharArray();
        var current = root;
        for (char c : letters) {
            if (!current.containsKey(c)) {
                return false;
            }
            current = current.get(c);
        }
        return true;
    }
}
