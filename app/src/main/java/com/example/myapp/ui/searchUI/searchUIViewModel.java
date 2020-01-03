package com.example.myapp.ui.searchUI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class searchUIViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public searchUIViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is search fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}