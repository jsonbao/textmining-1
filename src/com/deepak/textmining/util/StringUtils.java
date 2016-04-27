package com.deepak.textmining.util;

import java.math.BigInteger;

/**
 * @author Deepak
 *
 */
public class StringUtils {

    public BigInteger getWordCount(String string) {
        long wordCount = 0;
        boolean word = false;
        int endOfLine = string.length() - 1;

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
        return BigInteger.valueOf(wordCount);
    }
}
