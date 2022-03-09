package com.joma.encard02.ui.videoFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joma.encard02.common.Resource;
import com.joma.encard02.data.videoModel.MainVideoResponce;
import com.joma.encard02.ui.App;

public class VideoViewModel extends ViewModel {
    protected LiveData<Resource<MainVideoResponce>> liveData = new MutableLiveData<>();

    protected void getVideoByTag(String tags) {
        liveData = App.repository.getVideo(tags);
    }
}
