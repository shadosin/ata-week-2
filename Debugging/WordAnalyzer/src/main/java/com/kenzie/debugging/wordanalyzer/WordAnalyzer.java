package com.kenzie.debugging.wordanalyzer;

import com.google.common.annotations.VisibleForTesting;

/**
 * A class that finds character patterns in words.
 * Adapted from http://www.horstmann.com/bigj/help/debugger/tutorial.html
 */
public class WordAnalyzer {
    // The word that will be analyzed.
    private String word;

    /**
     * Constructs an analyzer for a given word.
     *
     * @param word the word to be analyzed
     */
    public WordAnalyzer(String word) {
        this.word = word;
    }

    /**
     * Gets the first repeated character. A character is <i>repeated</i>
     * if it occurs at least twice in adjacent positions. For example,
     * 'l' is repeated in "hollow", but 'o' is not.
     *
     * @return the first repeated character, or 0 if none found.
     */
    public char firstRepeatedCharacter() {
        for (int i = 1; i < word.length() - 1; i++) {
            char current = word.charAt(i);
            char adjacent = word.charAt(i + 1);
            if (current == adjacent) {
                return current;
            }
        }
        return 0;
    }

    /**
     * Gets the first multiply occurring character. A character is <i>multiple</i>
     * if it occurs at least twice in the word, not necessarily in adjacent positions.
     * For example, both 'o' and 'l' are multiple in "hollow", but 'h' is not.
     * Since the first 'o' comes before the first 'l', 'o' is the first multiply
     * occurring character. 'l' is also a repeating character.
     *
     * @return the first multiply occurring character, or 0 if none found
     */
    public char firstMultipleCharacter() {
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (find(ch, i) >= 0) {
                return ch;
            }
        }
        return 0;
    }

    /**
     * Returns the index greater than or equal to the start position where c occurs, or -1 if not found.
     *
     * @param c     The character to find.
     * @param start The position to start searching from.
     * @return -1 if c is not found, or the index >= start where it occurs.
     */
    private int find(char c, int start) {
        for (int i = start; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Counts the groups of repeated characters. For example, "mississippi!!!" has
     * four such groups: ss, ss, pp and !!!.  "Mooo" has only one group, "ooo".
     * "Frodo" has 0 groups of repeated characters.
     *
     * @return the number of repeated character groups
     */
    public int countRepeatedCharacters() {
        int numGroups = 0;
        for (int i = 1; i < word.length() - 1; i++) {
            // Is the next character part of the repeat?
            if (word.charAt(i) == word.charAt(i + 1)) {
                // Is this the start of the repeat?
                if (!repeatsPreviousCharacter(i)) {
                    numGroups++;
                }
            }
        }

        return numGroups;
    }

    /**
     * Checks if the letter at index i is the same as the previous character.
     *
     * @param i The location in {@code word} to inspect.
     * @return True if the character at i repeats the previous character, false otherwise.
     */
    boolean repeatsPreviousCharacter(int i) {
        if(i > 0) {
            return word.charAt(i - 1) == word.charAt(i);
        }
        return false;
    }

    // PARTICIPANTS: Do not alter this method or your tests will fail.
    public int findWrapper(char c, int start) {
        return find(c, start);
    }

    // PARTICIPANTS: Do not alter this method or your tests will fail.
    public boolean repeatsPreviousCharacterWrapper(int i) {
        return repeatsPreviousCharacter(i);
    }
}
