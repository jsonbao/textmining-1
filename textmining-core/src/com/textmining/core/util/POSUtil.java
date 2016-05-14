package com.textmining.core.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;

/**
 * Parts of Speech Utility Class
 * 
 * @author Deepak
 *
 */
public class POSUtil {

    private TokenizerME tokenizerME;

    private POSTaggerME posTaggerME;

    private Map<String, String> posMap;

    /**
     * @param string
     * @return {@linkp HashMap} containing word as key and values as POS Tags, POS, and the number
     *         of times the word is repeated
     */
    public Map<String, String[]> posDetect(String string) {
        //Remove all punctuation's from the given string  
        String[] stringTokens = tokenizerME.tokenize(string.replaceAll("[^a-zA-Z0-9 ]", ""));
        //Get the POS for the above Array of String
        String[] posTags = posTaggerME.tag(stringTokens);
        //Create a Map which has the words in string as key and
        //values as POS Tags, POS, and the number of times the word is repeated 
        Map<String, String[]> poss = new HashMap<>();
        for (int i = 0, j = 0; i < stringTokens.length && j < posTags.length; i++, j++) {
            if (poss.containsKey(stringTokens[i])) {
                poss.get(stringTokens[i])[2] = Integer.toString(Integer.parseInt(poss.get(stringTokens[i])[2]) + 1);
            } else {
                poss.put(stringTokens[i], new String[] { posTags[j],
                        posMap.containsKey(posTags[j]) ? posMap.get(posTags[j]) : "POS Not Found", "1" });
            }
        }
        stringTokens = null;
        posTags = null;
        return poss;
    }

    /**
     * @param string
     * @return {@linkp HashMap} containing word as key and values as POS Tags, POS, and the number
     *         of times the word is repeated
     */
    public Map<String, String[]> posDetect(String[] string) {
        return posDetect(Arrays.toString(string));
    }

    public Map<String, Long> numberOfSimilarPOS(Map<String, String[]> posDetectedMap) {
        Map<String, Long> posCount = new HashMap();
        for (String[] string : posDetectedMap.values()) {
            if (posCount.containsKey(string[0])) {
                posCount.put(string[0], posCount.get(string[0]) + 1);
            } else {
                posCount.put(string[0], 1L);
            }
        }
        return posCount;
    }

    public Map<String, Long> numberOfSimilarPOS(String string) {
        return numberOfSimilarPOS(posDetect(string));
    }

    public Map<String, Long> numberOfSimilarPOS(String[] string) {
        return numberOfSimilarPOS(Arrays.toString(string));
    }

    public void setTokenizerME(TokenizerME tokenizerME) {
        this.tokenizerME = tokenizerME;
    }

    public void setPosTaggerME(POSTaggerME posTaggerME) {
        this.posTaggerME = posTaggerME;
    }

    public void setPosMap(Map<String, String> posMap) {
        this.posMap = posMap;
    }
}
