package com.agb.citysearchapp.data.datastructure

/**
 * Represents a node in the Trie data structure.
 *
 * @param Key The type of items stored in the Trie nodes.
 * @property children A mutable map of child nodes where each key is a character and the value is a TrieNode.
 * @property items A mutable list of items associated with the current node.
 */
class TrieNode<Key> {
    val children: MutableMap<Char, TrieNode<Key>> = mutableMapOf()
    val items = mutableListOf<Key>()
}
