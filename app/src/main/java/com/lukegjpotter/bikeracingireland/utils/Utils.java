package com.lukegjpotter.bikeracingireland.utils;

import android.content.Context;

import com.lukegjpotter.bikeracingireland.constant.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private static Context APPLICATION_CONTEXT;

    public static String convertDateToString(Date date) {

        try {
            return new SimpleDateFormat(Constants.DATE_FORMAT, Locale.UK).format(date);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static String convertDateToUiString(Date date) {

        try {
            return new SimpleDateFormat(Constants.UI_DATE_FORMAT, Locale.UK).format(date);
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

    public static Context getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public static void setApplicationContext(Context applicationContext) {
        APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * Removes the last occurrence of {@code removeThis} in {@code baseString}.
     *
     * @param baseString The base string to remove the ending of.
     * @param removeThis The ending of the baseString to remove.
     * @return The baseString with the ending remove.
     */
    public static String removeLastOccurrenceInString(String baseString, String removeThis) {

        int positionOfLastOccurrence = baseString.lastIndexOf(removeThis);

        if (positionOfLastOccurrence > -1) {
            baseString = baseString.substring(0, positionOfLastOccurrence);
        }

        return baseString;
    }
}
