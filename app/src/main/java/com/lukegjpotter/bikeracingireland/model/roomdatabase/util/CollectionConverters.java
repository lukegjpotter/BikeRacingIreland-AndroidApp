package com.lukegjpotter.bikeracingireland.model.roomdatabase.util;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lukegjpotter.bikeracingireland.enums.RaceType;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukegjpotter on 09/12/2017.
 */

public class CollectionConverters {

    @TypeConverter
    public String stringSetToString(Set<String> stringSet) {
        return (stringSet == null || stringSet.isEmpty()) ? null : new Gson().toJson(stringSet);
    }

    @TypeConverter
    public Set<String> stringSetfromString(String string) {
        if (string == null || string.isEmpty()) return null;

        Type setType = new TypeToken<HashSet<String>>() {
        }.getType();
        return new Gson().fromJson(string, setType);
    }

    @TypeConverter
    public String racetTypeSetToString(Set<RaceType> raceTypeSetSet) {
        return (raceTypeSetSet == null || raceTypeSetSet.isEmpty()) ? null : new Gson().toJson(raceTypeSetSet);
    }

    @TypeConverter
    public Set<RaceType> raceTypeSetfromString(String string) {
        if (string == null || string.isEmpty()) return null;

        Type setType = new TypeToken<HashSet<RaceType>>() {
        }.getType();
        return new Gson().fromJson(string, setType);
    }
}
