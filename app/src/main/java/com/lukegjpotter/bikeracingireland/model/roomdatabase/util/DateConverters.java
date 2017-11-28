package com.lukegjpotter.bikeracingireland.model.roomdatabase.util;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by lukegjpotter on 28/11/2017.
 */

public class DateConverters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
