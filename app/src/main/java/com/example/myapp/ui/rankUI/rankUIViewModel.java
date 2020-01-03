package com.example.myapp.ui.rankUI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class rankUIViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public rankUIViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rank fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}