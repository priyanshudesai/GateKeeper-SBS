package com.example.priyanshu1.ui.visi_entry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VisiEntryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VisiEntryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}