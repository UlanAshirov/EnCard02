package com.joma.encard02.ui.videoFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joma.encard02.common.Resource;
import com.joma.encard02.data.model.videoModel.MainVideoResponce;
import com.joma.encard02.repositories.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class VideoViewModel extends ViewModel {
    private MainRepository repository;
    private LiveData<Resource<MainVideoResponce>> liveData;

    @Inject
    public VideoViewModel(MainRepository repository) {
        this.repository = repository;
        liveData = new MutableLiveData<>();
    }

    protected void getVideoByTag(String tags, int page) {
        liveData = repository.getVideo(tags, page);
    }

    public LiveData<Resource<MainVideoResponce>> getLiveData() {
        return liveData;
    }
}
