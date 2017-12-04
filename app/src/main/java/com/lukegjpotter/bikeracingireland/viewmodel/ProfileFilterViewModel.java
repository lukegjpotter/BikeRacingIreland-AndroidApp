package com.lukegjpotter.bikeracingireland.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.lukegjpotter.bikeracingireland.model.dao.ProfileFilterDao;
import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;
import com.lukegjpotter.bikeracingireland.model.roomdatabase.ApplicationDatabase;

/**
 * Created by lukegjpotter on 04/12/2017.
 */

public class ProfileFilterViewModel extends AndroidViewModel {

    private final ProfileFilterDao profileFilterDao;
    private LiveData<ProfileFilterEntity> profileFilter;
    private boolean isEnabled;


    public ProfileFilterViewModel(@NonNull Application application) {
        super(application);

        profileFilterDao = ApplicationDatabase.getInstance(application.getApplicationContext()).profileFilterDao();

        profileFilter = profileFilterDao.findProfileFilter();
    }

    public LiveData<ProfileFilterEntity> getProfileFilter() {
        return profileFilter;
    }

    public void updateProfileFilter(ProfileFilterEntity profileFilter) {
        profileFilterDao.updateProfileFilters(profileFilter);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isPopulated() {
        return profileFilterDao.rowCount() > 0;
    }
}
