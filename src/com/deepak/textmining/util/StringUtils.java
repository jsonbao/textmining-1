package com.deepak.textmining.util;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

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
     * @return The number of words in the given @link String}
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
        long wordCount = 0;
        for (String str : string) {
            wordCount += totalWordCount(str).longValue();
        }
        return wordCount;
    }

    /**
     * Gives a Map with String as Key and the number of occurrences of the string as value
     * 
     * @param string
     *            {@link String} whose similar word count is required
     * @return {@link ConcurrentMap} which has String as key and the number of occurrences of the
     *         string as value
     */
    public Map<String, AtomicLong> similarWordCount(String string) {
        ConcurrentMap<String, AtomicLong> wordCount = new ConcurrentHashMap<String, AtomicLong>();
        StringTokenizer strToken = new StringTokenizer(string.replaceAll("[^a-zA-Z0-9 ]", ""));
        String currentString = null;
        while (strToken.hasMoreTokens()) {
            currentString = strToken.nextToken();
            if (currentString != null) {
                if (wordCount.containsKey(currentString)) {
                    wordCount.get(currentString).addAndGet(1L);
                } else {
                    wordCount.put(currentString, new AtomicLong(1));
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
    public Map<String, AtomicLong> similarWordCount(String[] string) {
        ConcurrentMap<String, AtomicLong> wordCount = new ConcurrentHashMap<String, AtomicLong>();
        for (String str : string) {
            StringTokenizer strToken = new StringTokenizer(str.replaceAll("[^a-zA-Z0-9 ]", ""));
            String currentString = null;
            while (strToken.hasMoreTokens()) {
                currentString = strToken.nextToken();
                if (currentString != null) {
                    if (wordCount.containsKey(currentString)) {
                        wordCount.get(currentString).addAndGet(1L);
                    } else {
                        wordCount.put(currentString, new AtomicLong(1));
                    }
                }
                currentString = null;
            }
        }
        return wordCount;
    }
}
