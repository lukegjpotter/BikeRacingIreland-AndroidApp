package com.lukegjpotter.bikeracingireland.model;

import com.lukegjpotter.bikeracingireland.enums.RaceType;

import java.util.HashSet;
import java.util.Set;

/**
 * This is the model Class for the ProfileFilter, as part of the ProfileFilter feature.
 */
public class ProfileFilter {

    private Set<RaceType> mRaceTypes;
    private Set<String> mCategories;

    public ProfileFilter() {
        mRaceTypes = new HashSet<>();
        mCategories = new HashSet<>();
    }

    public Set<RaceType> getRaceTypes() {
        return mRaceTypes;
    }

    public void setRaceTypes(Set<RaceType> raceTypes) {
        this.mRaceTypes = raceTypes;
    }

    public Set<String> getCategories() {
        return mCategories;
    }

    public void setCategories(Set<String> categories) {
        this.mCategories = categories;
    }
}
