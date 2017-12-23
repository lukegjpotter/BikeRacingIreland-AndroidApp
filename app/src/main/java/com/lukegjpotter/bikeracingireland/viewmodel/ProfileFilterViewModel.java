package com.lukegjpotter.bikeracingireland.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.lukegjpotter.bikeracingireland.model.entity.ProfileFilterEntity;
import com.lukegjpotter.bikeracingireland.repository.ProfileFilterRepository;

/**
 * The ViewModel for the ProfileFilter
 *
 * Created by lukegjpotter on 04/12/2017.
 */

public class ProfileFilterViewModel extends AndroidViewModel {

    private ProfileFilterRepository repository;
    private LiveData<ProfileFilterEntity> profileFilter;
    private boolean isEnabled;


    public ProfileFilterViewModel(@NonNull Application application) {
        super(application);

        repository = ProfileFilterRepository.getInstance(application.getApplicationContext());

        profileFilter = repository.findProfileFilter();
    }

    public LiveData<ProfileFilterEntity> getProfileFilter() {
        return profileFilter;
    }

    public void updateProfileFilter(ProfileFilterEntity profileFilter) {
        repository.updateProfileFilters(profileFilter);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        repository.setEnabled(enabled);
        isEnabled = enabled;
    }

    public boolean isPopulated() {
        return repository.hasData();
    }
}
