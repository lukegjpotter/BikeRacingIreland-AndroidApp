package com.lukegjpotter.bikeracingireland.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.lukegjpotter.bikeracingireland.constant.Constants;
import com.lukegjpotter.bikeracingireland.enums.RaceType;
import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * The Utilities class for the functionality related to the ProfileFilter Feature.
 */
public class ProfileFilterUtils {

    public static boolean IS_PROFILE_FILTER_ENABLED = false;
    public static boolean IS_PROFILE_FILTER_SET = false;
    private static ProfileFilterEntity sProfileFilterEntity;

    public static void loadProfileFilter() {
        // TODO Move this to DatabaseInitializer.
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(Utils.getApplicationContext());
        String json = mPrefs.getString(Constants.SHARED_PREFS_PROFILE_FILTER_KEY, "");
        sProfileFilterEntity = new Gson().fromJson(json, ProfileFilterEntity.class);

        if (sProfileFilterEntity == null) {
            // TODO Implement This Properly with User Set Settings.
            ProfileFilterEntity profileFilterEntity = new ProfileFilterEntity();

            Set<RaceType> raceTypes = new HashSet<>();
            raceTypes.add(RaceType.A1);
            profileFilterEntity.setRaceTypes(raceTypes);

            Set<String> categories = new HashSet<>();
            categories.add("Road Race");
            categories.add("Time Trial");
            profileFilterEntity.setCategories(categories);

            ProfileFilterUtils.setProfileFilter(profileFilterEntity);

            storeProfileFilter();
        }
    }

    public static void storeProfileFilter() {
        // TODO Use Database to Store the ProfileFilter.
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(Utils.getApplicationContext());

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String json = new Gson().toJson(sProfileFilterEntity);
        prefsEditor.putString(Constants.SHARED_PREFS_PROFILE_FILTER_KEY, json);
        prefsEditor.apply();
    }

    public static ProfileFilterEntity getProfileFilter() {
        return sProfileFilterEntity;
    }

    private static void setProfileFilter(ProfileFilterEntity profileFilter) {
        sProfileFilterEntity = profileFilter;
        IS_PROFILE_FILTER_SET = true;
    }
}
