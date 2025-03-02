package edu.ousl.lms.service.tree;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26]; // Assuming lowercase English letters a-z
        isEndOfWord = false;
    }
}
