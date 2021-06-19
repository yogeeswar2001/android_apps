package com.yogeeswar.calculator;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    @DisplayName("conversion of string to integer")
    public void to_int_test() {
        ExpEval e = new ExpEval();
        int expected = e.to_int("2345");
        int actual = 2345;
        assertEquals("should convert number as string to integer", expected, actual);
    }

    @Test
    @DisplayName("parsing input expression")
    public void parseTest() {
        ExpEval e = new ExpEval();
        e.parse("2*(100-500/10)");
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("2","*","(","100","-","500","/","10",")"));
        assertArrayEquals("should parse the input to string", expected.toArray(), e.exp.toArray());
    }
}