package com.textmining.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.textmining.core.util.StringUtilsCompare;

public class CompareAnalysisController extends BaseController {

    private Map<Integer, String> dictionaryOne;

    private Map<Integer, String> dictionaryThree;

    private Map<Integer, String> dictionaryTwo;

    private boolean result = false;

    private String stringOne = "";

    private String stringTwo = "";

    private StringUtilsCompare stringUtilsCompare;

    public void apply() {
        ResourceBundle.clearCache();
        if (getStringOne().trim().isEmpty()) {
            error("Enter Text in the Left text area");
        }
        if (getStringTwo().trim().isEmpty()) {
            error("Enter Text in the Right text area");
        }
        if (!getStringOne().trim().isEmpty() && !getStringTwo().trim().isEmpty()) {
            result = true;
        }
    }

    public Map<Integer, String> getDictionaryOne() {
        Set<String> dictOne = stringUtilsCompare.uniqueWords(getStringOne(), getStringTwo());
        dictionaryOne = new HashMap<>();
        for (int i = 0; i < dictOne.size(); i++) {
            dictionaryOne.put(i, (String) dictOne.toArray()[i]);
        }
        dictOne.clear();
        return dictionaryOne;
    }

    public Map<Integer, String> getDictionaryTwo() {
        Set<String> dictTwo = stringUtilsCompare.uniqueWords(getStringTwo(), getStringOne());
        dictionaryTwo = new HashMap<>();
        for (int i = 0; i < dictTwo.size(); i++) {
            dictionaryTwo.put(i, (String) dictTwo.toArray()[i]);
        }
        dictTwo.clear();
        return dictionaryTwo;
    }

    public Map<Integer, String> getSimilarWords() {
        Set<String> similarWordsSet = stringUtilsCompare.similarWords(getStringOne(), getStringTwo());
        dictionaryThree = new HashMap<>();
        for (int i = 0; i < similarWordsSet.size(); i++) {
            dictionaryThree.put(i, (String) similarWordsSet.toArray()[i]);
        }
        similarWordsSet.clear();
        return dictionaryThree;

    }

    public String getStringOne() {
        return stringOne;
    }

    public String getStringTwo() {
        return stringTwo;
    }

    public StringUtilsCompare getStringUtilsCompare() {
        return stringUtilsCompare;
    }

    public boolean isResult() {
        return result;
    }

    public void reset() {
        result = false;
        stringOne = "";
        stringTwo = "";
        ResourceBundle.clearCache();
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setStringOne(String stringOne) {
        this.stringOne = stringOne;
    }

    public void setStringTwo(String stringTwo) {
        this.stringTwo = stringTwo;
    }

    public void setStringUtilsCompare(StringUtilsCompare stringUtilsCompare) {
        this.stringUtilsCompare = stringUtilsCompare;
    }
}
