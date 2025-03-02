package edu.ousl.lms.service.tree;
import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a student name
    public void insert(String name) {
        TrieNode node = root;
        for (char c : name.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Find student names with a given prefix
    public List<String> findStudentsWithPrefix(String prefix) {
        TrieNode node = root;
        List<String> result = new ArrayList<>();

        // Traverse the Trie to find the node corresponding to the prefix
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return result; // No students found with this prefix
            }
            node = node.children[index];
        }

        // Start DFS from the current node to find all names starting with the prefix
        findNamesFromNode(node, prefix, result);
        return result;
    }

    // Helper DFS function to find all student names from a given node
    private void findNamesFromNode(TrieNode node, String prefix, List<String> result) {
        if (node == null) return;

        // If this node is the end of a name, add it to the result list
        if (node.isEndOfWord) {
            result.add(prefix);
        }

        // Recursively search all child nodes
        for (char i = 'a'; i <= 'z'; i++) {
            int index = i - 'a';
            if (node.children[index] != null) {
                findNamesFromNode(node.children[index], prefix + i, result);
            }
        }
    }
}
