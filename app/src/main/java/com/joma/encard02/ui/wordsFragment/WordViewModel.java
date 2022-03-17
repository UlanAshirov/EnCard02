package com.joma.encard02.ui.wordsFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joma.encard02.common.Resource;
import com.joma.encard02.data.model.imageModel.Hit;
import com.joma.encard02.data.model.imageModel.PixabayResponse;
import com.joma.encard02.repositories.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WordViewModel extends ViewModel {
    public MainRepository repository;
    private LiveData<Resource<PixabayResponse<Hit>>> liveData;


    protected void getImageByWord(String keyWord, int page) {
        liveData = repository.getImages(keyWord, page);
    }

    public LiveData<Resource<PixabayResponse<Hit>>> getLiveData() {
        return liveData;
    }

    @Inject
    public WordViewModel(MainRepository repository) {
        this.repository = repository;
        liveData = new MutableLiveData<>();
    }

}
