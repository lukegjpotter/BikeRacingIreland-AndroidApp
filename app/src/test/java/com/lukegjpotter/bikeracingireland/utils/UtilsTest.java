package com.lukegjpotter.bikeracingireland.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

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

    @Test
    public void convertIntegerToBoolean_True() {
        assertTrue(Utils.convertIntegerToBoolean(1));
    }

    @Test
    public void convertIntegerToBoolean_False() {
        assertFalse(Utils.convertIntegerToBoolean(0));
    }
}
