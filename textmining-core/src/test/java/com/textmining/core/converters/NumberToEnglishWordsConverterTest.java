package com.textmining.core.converters;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
public class NumberToEnglishWordsConverterTest {

    @Resource
    private NumberToEnglishWordsConverter ntewc;

    @Test
    public void test() {
        assertEquals("zero", ntewc.convert(0));
        assertEquals("one hundred", ntewc.convert(100));
        assertEquals("one thousand ", ntewc.convert(1000));
        assertEquals("one hundred ten", ntewc.convert(110));
        assertEquals("eleven thousand ", ntewc.convert(11_000));
        assertEquals("one million ", ntewc.convert(1_000_000));
        assertEquals("one billion one hundred twelve million one hundred ninety nine thousand one hundred ninety nine",
                ntewc.convert(1_112_199_199));
    }

}
