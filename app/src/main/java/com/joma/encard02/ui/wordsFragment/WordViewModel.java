package com.joma.encard02.ui.wordsFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joma.encard02.common.Resource;
import com.joma.encard02.data.model.PixabayResponse;
import com.joma.encard02.ui.App;

public class WordViewModel extends ViewModel {
    public LiveData<Resource<PixabayResponse>> liveData = new MutableLiveData<>();

    public void getImageByWord(String keyWord) {
        liveData = App.repository.getImages(keyWord);
    }
}
