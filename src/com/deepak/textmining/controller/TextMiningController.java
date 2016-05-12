package com.deepak.textmining.controller;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.PieChartModel;

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

    private BarChartModel barModel;

    private Long time;

    private PieChartModel pieChartModel;

    public void clear() {
        showResult = false;
        stringToBeTested = "";
        ResourceBundle.clearCache();
    }

    public void apply() {
        ResourceBundle.clearCache();
        Long start = System.currentTimeMillis();
        if (!getStringToBeTested().trim().isEmpty()) {
            showResult = true;
            initBarModelForNumbeCount();
        }
        Long end = System.currentTimeMillis();
        setTime(end - start);
    }

    public Long getTotalWordCount() {
        return stringUtils.totalWordCount(getStringToBeTested());
    }

    public int getNumberOfSentences() {
        return stringUtils.numberOfSentences(getStringToBeTested());
    }

    public Map<String, Long> getSimilarWordCount() {
        return stringUtils.similarWordCount(getStringToBeTested());
    }

    public Map<String, String[]> getPosDetect() {
        return posUtil.posDetect(getStringToBeTested());
    }

    public Map<String, Long> getPosCount() {
        return posUtil.numberOfSimilarPOS(getPosDetect());
    }

    public Map<String, Long> getWordsOfSizeOne() {
        return stringUtils.fetchWordsBasedOnSize(getStringToBeTested(), 1);
    }

    public Map<String, Long> getWordsOfSizeTwo() {
        return stringUtils.fetchWordsBasedOnSize(getStringToBeTested(), 2);
    }

    public Map<String, Long> getWordsOfSizeThree() {
        return stringUtils.fetchWordsBasedOnSize(getStringToBeTested(), 3);
    }

    public Map<String, Long> getWordsOfSizeFour() {
        return stringUtils.fetchWordsBasedOnSize(getStringToBeTested(), 4);
    }

    public Map<String, Long> getCountOfWordsBySize() {
        return stringUtils.fetchCountOfWordsBySize(getStringToBeTested());
    }

    public void initBarModelForNumbeCount() {
        barModel = new BarChartModel();
        barModel.setAnimate(true);
        barModel.setBarWidth(350);
        barModel.setTitle("Total Number of Sentences and Words");
        barModel.setLegendPlacement(LegendPlacement.INSIDE);
        barModel.setLegendPosition("nw");

        ChartSeries sentences = new ChartSeries();
        sentences.setLabel("Sentences");
        sentences.set(" ", getNumberOfSentences());

        ChartSeries words = new ChartSeries();
        words.setLabel("Words");
        words.set(" ", getTotalWordCount());

        barModel.addSeries(sentences);
        barModel.addSeries(words);

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Property");
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total");
        yAxis.setMin(0);
        if (getTotalWordCount() <= Long.MAX_VALUE) {
            long y = getTotalWordCount() + (200L - (getTotalWordCount() % 100));
            yAxis.setMax((getTotalWordCount() % 100 < 100) ? y : getTotalWordCount());
        } else {
            yAxis.setMax(Long.MAX_VALUE);
        }
    }

    /*
     * Setters and Getters of Objects and Variables
     */
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

    public BarChartModel getBarModel() {
        return barModel;
    }

    public Long getTime() {
        return time;
    }

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
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
