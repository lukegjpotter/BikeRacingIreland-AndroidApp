package com.lukegjpotter.bikeracingireland.utils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    // ------------------------ convertDateToString ------------------------ //
    @Test
    public void convertDateToString_RealDate() {
        String expected = "20160731";
        Date date = new Date(1469923200000L);
        String actual = Utils.convertDateToString(date);

        assertEquals(actual, expected);
    }

    @Test
    public void convertDateToString_NullDate() {
        String expected = "";
        String actual = Utils.convertDateToString(null);

        assertEquals("Exp: " + expected + ". Act: " + actual, actual, expected);
    }

    // ------------------------ convertStringToDate ------------------------ //
    @Test
    public void convertStringToDate_RealDate() {
        Date expected = new Date(1451606400000L);
        Date actual = Utils.convertStringToDate("20160101");

        assertEquals(actual, expected);
    }

    @Test
    public void convertStringToDate_NullDate() {
        assertNull(Utils.convertStringToDate("TurtleFoot"));
    }

    // --------------------- convertBooleanToInteger ----------------------- //
    @Test
    public void convertBooleanToInteger_True() {
        int expected = 1;
        int actual = Utils.convertBooleanToInteger(true);

        assertTrue(actual == expected);
    }

    @Test
    public void convertBooleanToInteger_False() {
        int expected = 0;
        int actual = Utils.convertBooleanToInteger(false);

        assertTrue(actual == expected);
    }

    // --------------------- convertIntegerToBoolean ----------------------- //
    @Test
    public void convertIntegerToBoolean_True() {
        assertTrue(Utils.convertIntegerToBoolean(1));
    }

    @Test
    public void convertIntegerToBoolean_False() {
        assertFalse(Utils.convertIntegerToBoolean(0));
    }

    // --------------------- removeLastOccurrenceInString ------------------ //
    @Test
    public void removeLastOccurrenceInString_OR() {
        String baseString = "isa1=1 OR isa2=1 OR ", replaceThis = " OR ",
                expected = "isa1=1 OR isa2=1";
        String actual = Utils.removeLastOccurrenceInString(baseString, replaceThis);

        assertEquals(expected, actual);
    }
}
