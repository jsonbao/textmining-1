package com.deepak.textmining.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/applicationContext.xml" })
public class MapUtilsTest {

    @Resource
    private MapUtils mapUtils;

    Map<String, String> map1 = new HashMap<>();

    Map<String, String> map2 = new HashMap<>();

    @Before
    public void setUp() {
        map1.put("Hello", "1");
        map1.put("how", "1");
        map1.put("are", "1");
        map1.put("you", "1");

        map2.put("I", "1");
        map2.put("am", "1");
        map2.put("fine", "1");
        map2.put("how", "1");
        map2.put("are", "1");
        map2.put("you", "1");
    }

    @Test
    public void commonKeysTest() {
        Set<String> commonKeys = mapUtils.commonKeys(map1, map2);
        assertThat(commonKeys.size(), is(3));
        assertThat(commonKeys.contains("are"), is(true));
        assertThat(commonKeys.contains("how"), is(true));
        assertThat(commonKeys.contains("you"), is(true));
        assertThat(commonKeys.contains("Hello"), is(false));
        assertThat(commonKeys.contains("I"), is(false));
        assertThat(commonKeys.contains("am"), is(false));
        assertThat(commonKeys.contains("fine"), is(false));
    }

    @Test
    public void uncommonKeysTest() {
        Set<String> uncommonKeys = mapUtils.uncommonKeys(map1, map2);
        assertThat(uncommonKeys.size(), is(4));
        assertThat(uncommonKeys.contains("are"), is(false));
        assertThat(uncommonKeys.contains("how"), is(false));
        assertThat(uncommonKeys.contains("you"), is(false));
        assertThat(uncommonKeys.contains("Hello"), is(true));
        assertThat(uncommonKeys.contains("I"), is(true));
        assertThat(uncommonKeys.contains("am"), is(true));
        assertThat(uncommonKeys.contains("fine"), is(true));
    }
}
