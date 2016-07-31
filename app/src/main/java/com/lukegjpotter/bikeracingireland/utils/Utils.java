package com.lukegjpotter.bikeracingireland.utils;

import com.lukegjpotter.bikeracingireland.constant.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String convertDateToString(Date date) {

        try {
            return new SimpleDateFormat(Constants.DATE_FORMAT, Locale.UK).format(date);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static Date convertStringToDate(String dateString) {

        try {
            return new SimpleDateFormat(Constants.DATE_FORMAT, Locale.UK).parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Integer convertBooleanToInteger(boolean bool) {

        if (bool) return 1;

        return 0;
    }

    public static boolean convertIntegerToBoolean(int anInt) {

        return anInt == 1;
    }
}
