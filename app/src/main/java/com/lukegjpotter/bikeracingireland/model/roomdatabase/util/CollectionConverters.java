package com.lukegjpotter.bikeracingireland.model.roomdatabase.util;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lukegjpotter.bikeracingireland.model.enums.RaceType;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Converters for the RoomDatabase.
 * This class keeps the Converters for Collections together.
 *
 * Created by lukegjpotter on 09/12/2017.
 */
public class CollectionConverters {

    /**
     * Preventing Instancing with a Private Constructor.
     */
    private CollectionConverters() {
        throw new AssertionError("Class should not be instantiated");
    }

    @TypeConverter
    public static String stringSetToString(Set<String> stringSet) {
        return (stringSet == null || stringSet.isEmpty()) ? null : new Gson().toJson(stringSet);
    }

    @TypeConverter
    public static Set<String> stringToStringSet(String string) {
        if (string == null || string.isEmpty()) return null;

        Type setType = new TypeToken<HashSet<String>>() {
        }.getType();
        return new Gson().fromJson(string, setType);
    }

    @TypeConverter
    public static String raceTypeSetToString(Set<RaceType> raceTypeSet) {
        return (raceTypeSet == null || raceTypeSet.isEmpty()) ? null : new Gson().toJson(raceTypeSet);
    }

    @TypeConverter
    public static Set<RaceType> stringToRaceTypeSet(String string) {
        if (string == null || string.isEmpty()) return null;

        Type setType = new TypeToken<HashSet<RaceType>>() {
        }.getType();
        return new Gson().fromJson(string, setType);
    }
}
