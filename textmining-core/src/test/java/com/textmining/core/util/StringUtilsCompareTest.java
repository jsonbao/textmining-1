package com.textmining.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
public class StringUtilsCompareTest {

    static String[] s1 = { "Hello how are you?" };

    static String[] s2 = { "I am fine. How are you?" };

    @AfterClass
    public static void terminate() {
        s1 = null;
        s2 = null;
    }

    @Resource
    private StringUtilsCompare stringUtilsCompare;

    @Test
    public void similarWordsTest() {
        Set<String> similarWords = stringUtilsCompare.similarWords(s1, s2);
        assertEquals(3, similarWords.size());
        assertTrue(similarWords.contains("how"));
        assertTrue(similarWords.contains("are"));
        assertTrue(similarWords.contains("you"));
        similarWords.clear();
    }

    @Test
    public void uniqueWordsTest() {
        Set<String> uniqueWords = stringUtilsCompare.uniqueWords(s1, s2);
        assertEquals(1, uniqueWords.size());
        assertEquals("hello", uniqueWords.toArray()[0]);
        uniqueWords.clear();
    }

}
