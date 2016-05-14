package com.textmining.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

import com.textmining.core.converters.NumberToEnglishWordsConverter;

/**
 * @author Deepak
 *
 */
public class StringUtils {

    private NumberToEnglishWordsConverter ntewc;

    /**
     * Fetch count of words of all Sizes
     * 
     * @param string
     *            {@link String} from which words are extracted
     * @return {@link Map} which has key as size of words and value as number of words of that size
     */
    public Map<String, Long> fetchCountOfWordsBySize(String string) {
        Map<String, Long> countOfWordsBySize = new LinkedHashMap<>();
        String currentString = null;
        StringTokenizer strToken = new StringTokenizer(string.replaceAll("[^a-zA-Z0-9 ]", ""));
        while (strToken.hasMoreTokens()) {
            currentString = strToken.nextToken().toLowerCase();
            if (currentString != null) {
                currentString.length();
                if (!countOfWordsBySize.containsKey(ntewc.convert(currentString.length()))) {
                    countOfWordsBySize.put(ntewc.convert(currentString.length()), 1L);
                } else {
                    countOfWordsBySize.put(ntewc.convert(currentString.length()),
                            countOfWordsBySize.get(ntewc.convert(currentString.length())) + 1);
                }
            }
        }
        currentString = null;
        strToken = null;
        return countOfWordsBySize;
    }

    /**
     * Fetch count of words of all Sizes
     * 
     * @param string
     *            Array of {@link String} from which words are to be extracted
     * @return {@link Map} which has key as size of words and value as number of words of that size
     */
    public Map<String, Long> fetchCountOfWordsBySize(String[] string) {
        return fetchCountOfWordsBySize(Arrays.toString(string));
    }

    /**
     * Fetch words based on required size
     * 
     * @param string
     *            String from which words are to be extracted
     * @param size
     *            size of words requried
     * @return {@link Map} of words with required and the number of times repeated
     */
    public Map<String, Long> fetchWordsBasedOnSize(String string, int size) {
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
        TreeMap<String, Long> newMap = new TreeMap<>();
        newMap.putAll(map);
        map = null;
        str = null;
        return newMap;
    }

    /**
     * Fetch words based on required size
     * 
     * @param string
     *            Array of {@link String} from which words are to be extracted
     * @param size
     *            size of words required
     * @return {@link Map} of words with required and the number of times repeated
     */
    public Map<String, Long> fetchWordsBasedOnSize(String[] string, int size) {
        return fetchWordsBasedOnSize(Arrays.toString(string), size);
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

    public void setNtewc(NumberToEnglishWordsConverter ntewc) {
        this.ntewc = ntewc;
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
        Map<String, Long> wordCount = new HashMap<>();
        StringTokenizer strToken = new StringTokenizer(string.replaceAll("[^a-zA-Z0-9 ]", ""));
        String currentString = null;
        while (strToken.hasMoreTokens()) {
            currentString = strToken.nextToken().toLowerCase();
            if (currentString != null) {
                if (wordCount.containsKey(currentString)) {
                    wordCount.put(currentString, wordCount.get(currentString) + 1);
                } else {
                    wordCount.put(currentString, 1L);
                }
            }
            currentString = null;
        }
        currentString = null;
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

}
