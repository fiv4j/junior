package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {
    @Ignore
    @Test
    public void whenTemplateWorkWell() {
        Generator test = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");

        String result = test.produce(template, map);
        String expected = "I am a Petr Arsentev, Who are you?";

        assertThat(result, is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapNotContainsTemplateKey() {
        Generator test = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");

        String result = test.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapContainsExcessKey() {
        Generator test = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        map.put("object", "plane");

        String result = test.produce(template, map);
    }

}