package com.lukegjpotter.bikeracingireland.testresources;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class TestUtils {

    static Date convertStringToDate(String string) {
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(string);
        } catch (ParseException e) {
            return null;
        }
    }
}
