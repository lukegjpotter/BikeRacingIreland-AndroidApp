package com.lukegjpotter.bikeracingireland.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.lukegjpotter.bikeracingireland.enums.RaceType;

import java.util.HashSet;
import java.util.Set;

/**
 * This is the model Class for the {@link ProfileFilterEntity}, as part of the ProfileFilter feature.
 * Created by lukegjpotter on 27/11/2017.
 */
@Entity
public class ProfileFilterEntity {

    // There will only be one instance of the ProfileFiler, so the ID=1 is hardcoded.
    @PrimaryKey
    private long id = 1;
    @ColumnInfo(name = "racetype")
    private Set<RaceType> raceTypes;
    @ColumnInfo(name = "category")
    private Set<String> categories;

    public ProfileFilterEntity() {
        raceTypes = new HashSet<>();
        categories = new HashSet<>();
    }

    @Ignore
    public ProfileFilterEntity(ProfileFilterEntity profileFilterEntity) {
        this.raceTypes = profileFilterEntity.getRaceTypes();
        this.categories = profileFilterEntity.getCategories();
    }

    @Ignore
    public ProfileFilterEntity(Set<RaceType> raceTypes, Set<String> categories) {
        this.raceTypes = raceTypes;
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<RaceType> getRaceTypes() {
        return raceTypes;
    }

    public void setRaceTypes(Set<RaceType> raceTypes) {
        this.raceTypes = raceTypes;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
}
