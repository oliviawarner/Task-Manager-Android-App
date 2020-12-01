package com.example.taskmanager.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {
    final MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    public void setIndex(int index) {
        mIndex.setValue(index);
    }
}