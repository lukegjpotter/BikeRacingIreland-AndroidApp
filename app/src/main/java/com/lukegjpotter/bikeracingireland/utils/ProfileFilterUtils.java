package com.lukegjpotter.bikeracingireland.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.lukegjpotter.bikeracingireland.constant.Constants;
import com.lukegjpotter.bikeracingireland.enums.RaceType;
import com.lukegjpotter.bikeracingireland.model.ProfileFilter;

import java.util.HashSet;
import java.util.Set;

/**
 * The Utilities class for the functionality related to the ProfileFilter Feature.
 */
public class ProfileFilterUtils {

    public static boolean IS_PROFILE_FILTER_ENABLED = false;
    public static boolean IS_PROFILE_FILTER_SET = false;
    private static ProfileFilter sProfileFilter;

    public static void loadProfileFilter() {

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(Utils.getApplicationContext());
        String json = mPrefs.getString(Constants.SHARED_PREFS_PROFILE_FILTER_KEY, "");
        sProfileFilter = new Gson().fromJson(json, ProfileFilter.class);

        if (sProfileFilter == null) {
            // TODO Implement This Properly with User Set Settings.
            ProfileFilter profileFilter = new ProfileFilter();

            Set<RaceType> raceTypes = new HashSet<>();
            raceTypes.add(RaceType.A1);
            profileFilter.setRaceTypes(raceTypes);

            Set<String> categories = new HashSet<>();
            categories.add("Road Race");
            categories.add("Time Trial");
            profileFilter.setCategories(categories);

            ProfileFilterUtils.setProfileFilter(profileFilter);

            storeProfileFilter();
        }
    }

    public static void storeProfileFilter() {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(Utils.getApplicationContext());

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String json = new Gson().toJson(sProfileFilter);
        prefsEditor.putString(Constants.SHARED_PREFS_PROFILE_FILTER_KEY, json);
        prefsEditor.apply();
    }

    public static ProfileFilter getProfileFilter() {
        return sProfileFilter;
    }

    public static void setProfileFilter(ProfileFilter profileFilter) {
        sProfileFilter = profileFilter;
        IS_PROFILE_FILTER_SET = true;
    }
}
