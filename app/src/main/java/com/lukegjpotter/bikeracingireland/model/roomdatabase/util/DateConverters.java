package com.lukegjpotter.bikeracingireland.model.roomdatabase.util;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Converters for the RoomDatabase.
 * This class keeps the Converters for Date Objects together.
 *
 * Created by lukegjpotter on 28/11/2017.
 */

public class DateConverters {

    /**
     * Preventing Instancing with a Private Constructor.
     */
    private DateConverters() {
        throw new AssertionError("Class should not be instantiated");
    }

    @TypeConverter
    public static Date timestampToDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
