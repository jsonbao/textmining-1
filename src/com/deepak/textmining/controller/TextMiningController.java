package com.deepak.textmining.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.deepak.textmining.util.MapUtils;
import com.deepak.textmining.util.POSUtil;
import com.deepak.textmining.util.StringUtils;

@ManagedBean
@SessionScoped
public class TextMiningController implements Serializable {

    private static final long serialVersionUID = 8232953245163786969L;

    private StringUtils stringUtils;

    private POSUtil posUtil;

    private MapUtils mapUtils;

    private String stringToBeTested = "";

    private boolean showResult = false;

    public void clear() {
        showResult = false;
        stringToBeTested = "";
    }

    public void apply() {
        //Checks if there are only spaces been given as input
        if (!getStringToBeTested().trim().isEmpty()) {
            showResult = true;
        }
    }

    public Long getTotalWordCount() {
        return stringUtils.totalWordCount(getStringToBeTested());
    }

    public int getNumberOfSentences() {
        return stringUtils.numberOfSentences(getStringToBeTested());
    }

    public StringUtils getStringUtils() {
        return stringUtils;
    }

    public POSUtil getPosUtil() {
        return posUtil;
    }

    public MapUtils getMapUtils() {
        return mapUtils;
    }

    public String getStringToBeTested() {
        return stringToBeTested;
    }

    public boolean isShowResult() {
        return showResult;
    }

    public void setStringUtils(StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    public void setPosUtil(POSUtil posUtil) {
        this.posUtil = posUtil;
    }

    public void setMapUtils(MapUtils mapUtils) {
        this.mapUtils = mapUtils;
    }

    public void setStringToBeTested(String stringToBeTested) {
        this.stringToBeTested = stringToBeTested;
    }

    public void setShowResult(boolean showResult) {
        this.showResult = showResult;
    }
}
