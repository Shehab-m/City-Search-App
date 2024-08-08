package com.agb.citysearchapp.data.datastructure

import com.agb.citysearchapp.domain.model.City

/**
 * Trie data structure for efficient prefix-based city name searches.
 *
 * This Trie (prefix tree) is optimized for scenarios where rapid retrieval of cities
 * based on the starting characters of their names is required. Unlike a linear search,
 * which examines each city name sequentially, the Trie organizes the data hierarchically,
 * allowing for quick access to all city names sharing a common prefix.
 *
 * ## Why Trie is More Efficient Than Linear Search:
 *
 * 1. **Prefix-Based Search**:
 *    - In linear search, checking each city's name individually results in a time complexity
 *      of O(n) for n city names. This approach can be slow for large datasets.
 *    - The Trie, allows direct traversal from the root to the end of the prefix
 *      in O(m) time, where m is the length of the prefix. Once at the prefix node, all matching
 *      cities can be retrieved directly from its subtree, making it faster than a linear search.
 *
 * 2. **Space-Time Trade-off**:
 *    - While the Trie consume more memory due to the additional nodes, it compensates
 *      by reducing the time complexity for searching. The hierarchical structure eliminates
 *      the need to repeatedly check each character in the city names against the prefix, as done
 *      in a linear search.
 *
 * 3. **Scalability**:
 *    - As the dataset grows, the Trie continues to offer efficient searches, whereas the
 *      performance of a linear search degrades proportionally to the size of the dataset.
 *    - This makes the Trie suited for handling large lists of cities, such as
 *      the 200k entries provided in this assignment.
 *
 * Implements the ITrieCity interface to support insertion and search operations.
 */
class TrieCity: ITrieCity {
    private val root = TrieNode<City>()

    /**
     * Inserts a city into the Trie.
     *
     * This method adds a city to the Trie by inserting each character of the city's name into the Trie nodes.
     * The city's object is stored at the end node corresponding to the full name.
     *
     * @param city The city object to be inserted into the Trie.
     */
    private fun insert(city: City) {
        var currentNode = root
        val key = city.toString().lowercase()
        for (char in key) {
            currentNode = currentNode.children.getOrPut(char) { TrieNode() }
        }
        currentNode.items.add(city)
    }

    /**
     * Inserts a batch of cities into the Trie.
     *
     * This method iterates over a list of cities and inserts each city into the Trie using the `insert` method.
     *
     * @param cities A list of cities to be inserted into the Trie.
     */
    override suspend fun insertBatch(cities: List<City>) {
        cities.forEach { city ->
            insert(city)
        }
    }

    /**
     * Searches for cities in the Trie that match the given prefix.
     *
     * This method traverses the Trie to locate the node corresponding to the end of the prefix.
     * Then it collects and returns all cities from that node and its descendants.
     *
     * @param prefix The prefix string used for searching in the Trie.
     * @return A list of cities that match the given prefix.
     */
    override fun search(prefix: String): List<City> {
        var currentNode = root
        val lowerCasePrefix = prefix.lowercase()
        for (char in lowerCasePrefix) {
            currentNode = currentNode.children[char] ?: return emptyList()
        }
        return collectCities(currentNode)
    }

    /**
     * Collects all cities from the given Trie node and its descendants.
     *
     * This recursive method traverses the Trie starting from the provided node, accumulating all cities
     * stored in the node and its children nodes.
     *
     * @param node The Trie node from which to collect cities.
     * @return A list of cities collected from the node and its descendants.
     */
    private fun collectCities(node: TrieNode<City>): List<City> {
        val results = mutableListOf<City>()
        results.addAll(node.items)
        node.children.values.forEach { childNode ->
            results.addAll(collectCities(childNode))
        }
        return results
    }
}