package com.lukegjpotter.bikeracingireland.utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;

import com.lukegjpotter.bikeracingireland.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    @SuppressLint("SimpleDateFormat")
    public static String convertDateToString(Date date) {
        String dateFormat = Resources.getSystem().getString(R.string.date_format);
        return new SimpleDateFormat(dateFormat).format(date);
    }

    public static Integer convertBooleanToInteger(boolean bool) {

        if (bool) return 1;

        return 0;
    }
}
