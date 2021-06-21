package com.yogeeswar.converter;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void dectobinTest() {
        ConverterUtils con = new ConverterUtils();
        String expected = "1101";
        String actual = con.dectobin(13);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("decimal to hexadecimal converter")
    public void dectohexTest() {
        ConverterUtils con = new ConverterUtils();
        String expected = "4E";
        String actual = con.dectohex(78);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("binary to decimal converter")
    public void bintodecTest() {
        ConverterUtils con = new ConverterUtils();
        String expected = "78";
        String actual = con.bintodec("1001110");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("binary to hexadecimal converter")
    public void bintohexTest() {
        ConverterUtils con = new ConverterUtils();
        String expected = "4E";
        String actual = con.bintohex("1001110");
        assertEquals(expected, actual);
    }

    @Disabled
    @Test
    @DisplayName("hexadecimal to decimal converter")
    public void hextodecTest() {
        ConverterUtils con = new ConverterUtils();
        String expected = "78";
        String actual = con.hextodec("4E");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("hexadecimal to binary converter")
    public void hextobinTest() {
        ConverterUtils con = new ConverterUtils();
        String expected = "01001110";
        String actual = con.hextobin("4E");
        assertEquals(expected, actual);
    }
}