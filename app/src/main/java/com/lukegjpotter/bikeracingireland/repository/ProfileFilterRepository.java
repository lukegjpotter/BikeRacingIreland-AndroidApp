package com.lukegjpotter.bikeracingireland.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.lukegjpotter.bikeracingireland.model.dao.ProfileFilterDao;
import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;

/**
 * The Repository for the ProfileFilter.
 * <p>
 * Created by lukegjpotter on 23/12/2017.
 */

public class ProfileFilterRepository {

    private static ProfileFilterRepository INSTANCE;
    private final ProfileFilterDao profileFilterDao;
    private boolean enabled;

    private ProfileFilterRepository(Context context) {
        profileFilterDao = ApplicationDatabase.getInstance(context).profileFilterDao();
    }

    public static ProfileFilterRepository getInstance(Context context) {

        if (INSTANCE == null) INSTANCE = new ProfileFilterRepository(context);

        return INSTANCE;
    }

    public boolean hasData() {
        return profileFilterDao.rowCount() > 0;
    }

    public LiveData<ProfileFilterEntity> findProfileFilter() {
        return profileFilterDao.findProfileFilter();
    }

    public void updateProfileFilters(ProfileFilterEntity profileFilter) {
        profileFilterDao.updateProfileFilters(profileFilter);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
