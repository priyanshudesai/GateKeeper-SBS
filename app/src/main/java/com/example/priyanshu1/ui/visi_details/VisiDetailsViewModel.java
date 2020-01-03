package com.example.priyanshu1.ui.visi_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VisiDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VisiDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}