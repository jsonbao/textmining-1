package com.textmining.core.util;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.textmining.core.util.StringUtils;

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

    @Test
    public void testNumberOfSentences() {
        String string = "Hello.you.Hello.you.Hello.you.Hello.you.Hello.you.";
        assertEquals(10, stringUtils.numberOfSentences(string));
    }

    @Test
    public void testNumberOfSentencesArray() {
        String[] str = { "Hello.you.Hello.you.", "Hello.you.Hello.you." };
        assertEquals(8, stringUtils.numberOfSentences(str));
    }

    @Test
    public void testFetchWordsBasedOnSize() {
        String[] string = { "Hello how are you?", "I am fine. How about you?", "Me too." };
        assertEquals(2, stringUtils.fetchWordsBasedOnSize(string, 2).size());
        assertThat(1L, is(equalTo(stringUtils.fetchWordsBasedOnSize(string, 2).get("am"))));
        assertThat(1L, is(equalTo(stringUtils.fetchWordsBasedOnSize(string, 2).get("Me"))));
    }

    @Test
    public void testFetchCountOfWordsBySize() {
        String[] string = { "Hello how are you?", "I am fine. How about you?", "Me too." };
        Map<String, Long> map = stringUtils.fetchCountOfWordsBySize(string);
        assertThat(1L, is(equalTo(map.get("one"))));
        assertThat(2L, is(equalTo(map.get("two"))));
        assertThat(6L, is(equalTo(map.get("three"))));
        assertThat(1L, is(equalTo(map.get("four"))));
        assertThat(2L, is(equalTo(map.get("five"))));
    }

}
