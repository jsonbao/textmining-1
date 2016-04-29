package com.deepak.textmining.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Deepak
 *
 */
public class StringUtils {

    /**
     * Get number of words present in a string. Counts the number of words by excluding the
     * punctuation marks and any other special characters
     * 
     * @param string
     * @return The number of words in the given {@link String}
     */
    public Long totalWordCount(String string) {
        long wordCount = 0;
        boolean word = false;
        Integer endOfLine = string.length() - 1;

        for (int i = 0; i < string.length(); i++) {
            //if the character is a letter then set word = true
            if (Character.isLetter(string.charAt(i)) && i != endOfLine) {
                word = true;
                // If character isn't a letter then increment the wordCount
            } else if (!Character.isLetter(string.charAt(i)) && word) {
                wordCount++;
                word = false;
                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetter(string.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        word = false;
        endOfLine = null;
        return wordCount;
    }

    /**
     * Get number of words present in a string. Counts the number of words by excluding the
     * punctuation marks and any other special characters
     * 
     * @param string
     *            Array of {@link String}
     * @return The number of words present in the given array of {@link String}
     */
    public Long totalWordCount(String[] string) {
        return totalWordCount(Arrays.toString(string));
    }

    /**
     * Gives a Map with String as Key and the number of occurrences of the string as value
     * 
     * @param string
     *            {@link String} whose similar word count is required
     * @return {@link ConcurrentMap} which has String as key and the number of occurrences of the
     *         string as value
     */
    public Map<String, Long> similarWordCount(String string) {
        Map<String, Long> wordCount = new HashMap<String, Long>();
        StringTokenizer strToken = new StringTokenizer(string.replaceAll("[^a-zA-Z0-9 ]", ""));
        String currentString = null;
        while (strToken.hasMoreTokens()) {
            currentString = strToken.nextToken();
            if (currentString != null) {
                if (wordCount.containsKey(currentString)) {
                    wordCount.put(currentString, wordCount.get(currentString) + 1);
                } else {
                    wordCount.put(currentString, 1L);
                }
            }
            currentString = null;
        }
        strToken = null;
        return wordCount;
    }

    /**
     * Gives a Map with String as Key and the number of occurrences of the string array as value
     * 
     * @param string
     *            Array of {@link String} whose similar word count is required
     * @return {@link ConcurrentMap} which has String as key and the number of occurrences of the
     *         string as value
     */
    public Map<String, Long> similarWordCount(String[] string) {
        return similarWordCount(Arrays.toString(string));
    }

    /**
     * Find the number of sentences in the given {@link String}
     * 
     * @param string
     *            {@link String}
     * @return Number of sentences in the given {@link String}
     */
    public int numberOfSentences(String string) {
        return new StringTokenizer(string, "[?.]+").countTokens();
    }

    /**
     * Find the number of sentences in the given Array of {@link String}
     * 
     * @param string
     *            Array of {@link String}
     * @return Number of sentences in the given Array of {@link String}
     */
    public int numberOfSentences(String[] string) {
        return numberOfSentences(Arrays.toString(string));
    }

    public Map<String, Long> sizeOfWords(String string, int size) {
        Map<String, Long> map = similarWordCount(string);
        List<String> str = new ArrayList<>();
        for (Map.Entry<String, Long> mapEntry : map.entrySet()) {
            if (mapEntry.getKey().length() != size) {
                str.add(mapEntry.getKey());
            }
        }
        for (String st : str) {
            map.remove(st);
        }
        TreeMap<String, Long> newMap = new TreeMap<String, Long>();
        newMap.putAll(map);
        map = null;
        str = null;
        return newMap;
    }

    public static void main(String[] args) {
        StringUtils st = new StringUtils();
        System.out.println(st.sizeOfWords("Hi aa How Are you. I Am ba Ba AA fine how are you?", 2));
    }

}
