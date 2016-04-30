package com.deepak.textmining.util;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URL;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

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
    public void dummy() {
        try {
            TokenizerModel tokModel = new TokenizerModel(
                    new URL("http://opennlp.sourceforge.net/models-1.5/en-token.bin"));
            TokenizerME tokenizer = new TokenizerME(tokModel);
            String[] stringTokens = tokenizer.tokenize("Hello How are you Deepak?I am Fine.");

            POSModel posModel = new POSModel(new URL("http://opennlp.sourceforge.net/models-1.5/en-pos-maxent.bin"));
            POSTaggerME posTagger = new POSTaggerME(posModel);
            String[] posTags = posTagger.tag(stringTokens);
            for (String string : posTags) {
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
