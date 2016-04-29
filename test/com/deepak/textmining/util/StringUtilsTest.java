package com.deepak.textmining.util;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
public class StringUtilsTest {

    @Resource
    private StringUtils stringUtils;

    @Test
    public void testGetWordCountString() {
        assertThat(stringUtils.totalWordCount("Hello how do you do! ' ' "), is(equalTo(5L)));
    }

    @Test
    public void testGetWordCountStringArray() {
        String[] string = { "Hello how do you do! ' ' ", "I am fine how are you?" };
        assertThat(stringUtils.totalWordCount(string), is(equalTo(11L)));
    }

    @Test
    public void testSimilarWordCount() {
        String str = "Hello how do you do! ' ' ";
        assertEquals(4, stringUtils.similarWordCount(str).size());
        assertEquals(1, stringUtils.similarWordCount(str).get("Hello").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("how").intValue());
        assertEquals(2, stringUtils.similarWordCount(str).get("do").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("you").intValue());
    }

    @Test
    public void testSimilarWordCountArray() {
        String[] str = { "Hello how do you do! ' ' ", "I am fine how are you " };
        assertEquals(8, stringUtils.similarWordCount(str).size());
        assertEquals(1, stringUtils.similarWordCount(str).get("Hello").intValue());
        assertEquals(2, stringUtils.similarWordCount(str).get("how").intValue());
        assertEquals(2, stringUtils.similarWordCount(str).get("do").intValue());
        assertEquals(2, stringUtils.similarWordCount(str).get("you").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("I").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("am").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("fine").intValue());
        assertEquals(1, stringUtils.similarWordCount(str).get("are").intValue());
    }

}
