package com.deepak.textmining.util;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deepak.textmining.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
public class StringUtilsTest {

    @Resource
    private StringUtils stringUtils;

    @Test
    public void test() {
        assertEquals(new BigInteger("5"), stringUtils.getWordCount("Hello how do you do! ' ' "));
    }

}
