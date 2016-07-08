package com.textmining.core.util;

import java.util.Arrays;
import java.util.Set;

/**
 * @author Deepak
 *
 */
public class StringUtilsCompare {

    private StringUtils stringUtils;

    public void setStringUtils(StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    /**
     * Get Similar words in both Strings
     * 
     * @param string1
     * @param string2
     * @return {@link Set} of Similar words in Both {@link String}s
     */
    public Set<String> similarWords(String string1, String string2) {
        Set<String> similarWordCount1 = stringUtils.similarWordCount(string1).keySet();
        Set<String> similarWordCount2 = stringUtils.similarWordCount(string2).keySet();
        similarWordCount1.retainAll(similarWordCount2);
        similarWordCount2.clear();
        return similarWordCount1;
    }

    public Set<String> similarWords(String[] string1, String[] string2) {
        return similarWords(Arrays.toString(string1), Arrays.toString(string2));
    }

    /**
     * Get Unique words present in string1 compared to string2
     * 
     * @param string1
     * @param string2
     * @return Unique words of string1
     */
    public Set<String> uniqueWords(String string1, String string2) {
        Set<String> similarWordCount1 = stringUtils.similarWordCount(string1).keySet();
        Set<String> similarWordCount2 = stringUtils.similarWordCount(string2).keySet();
        similarWordCount1.removeAll(similarWordCount2);
        similarWordCount2.clear();
        return similarWordCount1;
    }

    public Set<String> uniqueWords(String[] string1, String[] string2) {
        return uniqueWords(Arrays.toString(string1), Arrays.toString(string2));
    }
}
