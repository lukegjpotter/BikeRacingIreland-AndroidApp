package com.lukegjpotter.bikeracingireland.constant;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstantsTest {

    @Test
    public void checkDateFormat() {
        assertEquals("Someone Changed the DATE_FORMAT", Constants.DATE_FORMAT, "yyyyMMdd");
    }
}
