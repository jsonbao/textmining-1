package com.textmining.core.util;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
public class POSUtilTest {

    @Resource
    private POSUtil posUtil;

    @Test
    public void numberOfSimilarPOSTest() {
        String[] string = { "Hello How are you Mike?", "I am Fine Hello." };
        Map<String, Long> posMap = posUtil.numberOfSimilarPOS(string);

        assertThat(posMap.size(), is(6));

        assertThat(posMap.get("UH"), is(1L));
        assertThat(posMap.get("WRB"), is(1L));
        assertThat(posMap.get("VBP"), is(2L));
        assertThat(posMap.get("JJ"), is(1L));
        assertThat(posMap.get("NNP"), is(1L));
        assertThat(posMap.get("PRP"), is(2L));
        posMap.clear();

    }

    @Test
    public void posDetectTest() {
        String[] string = { "Hello How are you Mike?", "I am Fine Hello." };
        Map<String, String[]> posMap = posUtil.posDetect(string);

        assertThat(posMap.get("Hello")[0], is(equalTo("UH")));
        assertThat(posMap.get("How")[0], is(equalTo("WRB")));
        assertThat(posMap.get("are")[0], is(equalTo("VBP")));
        assertThat(posMap.get("Fine")[0], is(equalTo("JJ")));
        assertThat(posMap.get("I")[0], is(equalTo("PRP")));
        assertThat(posMap.get("am")[0], is(equalTo("VBP")));
        assertThat(posMap.get("Mike")[0], is(equalTo("NNP")));
        assertThat(posMap.get("you")[0], is(equalTo("PRP")));

        assertThat(posMap.get("Hello")[2], is(equalTo("2")));
        assertThat(posMap.get("How")[2], is(equalTo("1")));
        assertThat(posMap.get("are")[2], is(equalTo("1")));
        assertThat(posMap.get("you")[2], is(equalTo("1")));
        assertThat(posMap.get("Mike")[2], is(equalTo("1")));
        assertThat(posMap.get("I")[2], is(equalTo("1")));
        assertThat(posMap.get("am")[2], is(equalTo("1")));
        assertThat(posMap.get("Fine")[2], is(equalTo("1")));
        posMap.clear();
    }
}
