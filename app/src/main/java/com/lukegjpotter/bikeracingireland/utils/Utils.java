package com.lukegjpotter.bikeracingireland.utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;

import com.lukegjpotter.bikeracingireland.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    @SuppressLint("SimpleDateFormat")
    public static String convertDateToString(Date date) {

        String dateFormat = Resources.getSystem().getString(R.string.date_format);

        return new SimpleDateFormat(dateFormat).format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static Date convertStringToDate(String dateString) {

        String dateFormat = Resources.getSystem().getString(R.string.date_format);

        try {
            return new SimpleDateFormat(dateFormat).parse(dateString);
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
