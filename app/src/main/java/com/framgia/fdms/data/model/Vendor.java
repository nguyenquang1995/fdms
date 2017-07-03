package com.framgia.fdms.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by framgia on 03/07/2017.
 */

public class Vendor extends BaseObservable {
    private String mName;
    private String mDescription;

    public Vendor() {
    }

    public Vendor(String name, String description) {
        mName = name;
        mDescription = description;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
        notifyPropertyChanged(BR.description);
    }
}
