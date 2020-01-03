package com.example.myapp.ui.postUI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class postUIViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public postUIViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is posting fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}