import org.apache.commons.math3.geometry.partitioning.utilities.AVLTree;
import org.w3c.dom.Node;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MyTrieSet implements TrieSet61B {

    private Node root;


    private class Node {
        private Character c;
        private boolean isKey;
        private int num;
        private HashMap<Character, Node> child;

        private Node(){
            isKey = false;
            num = 0;
            child = new HashMap<>();
        }
        private Node(Character c){
            this.c = c;
            isKey = false;
            num = 0;
            child = new HashMap<>();
        }
        private void setToKey(){
            isKey = true;
        }
    }
    public MyTrieSet() {
        clear();
    }

    /**
     * Clears all items out of Trie
     */
    @Override
    public void clear() {
        root = new Node();

    }

    /**
     * Returns true if the Trie contains KEY, false otherwise
     *
     * @param key
     */
    @Override
    public boolean contains(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            if(!curr.child.containsKey(key.charAt(i))) {
                return false;
            }
            curr = curr.child.get(key.charAt(i));
        }
        return true;
    }

    /**
     * Inserts string KEY into Trie
     *
     * @param key
     */
    @Override
    public void add(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            if (!curr.child.containsKey(key.charAt(i))){
                curr.child.put(key.charAt(i), new Node(key.charAt(i)));
            }

            if (i == key.length() - 1) {
                curr.child.get(key.charAt(i)).setToKey();
            }
            curr.child.get(key.charAt(i)).num++;
            curr = curr.child.get(key.charAt(i));
        }

    }

    /**
     * Returns a list of all words that start with PREFIX
     *
     * @param prefix
     */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            return null;
        }
        if(!contains(prefix)) {
            return null;
        }
        List<String> list = new ArrayList<>();

        Node curr = root;

        for (int i = 0; i < prefix.length(); i++) {
            curr = curr.child.get(prefix.charAt(i));
        }

        helpKeysWithPrefix(prefix, list, curr);
        return list;

    }
    private void helpKeysWithPrefix(String s, List<String> list, Node node) {

        if (node.isKey) {
            list.add(s);
        }

        Set<Character> allChars = node.child.keySet();
        for (Character c: allChars) {
            helpKeysWithPrefix(s + c, list, node.child.get(c));
        }
    }


    /**
     * Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
