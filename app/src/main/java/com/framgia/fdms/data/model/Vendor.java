package com.framgia.fdms.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by framgia on 03/07/2017.
 */

public class Vendor extends BaseObservable implements Parcelable {
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

    protected Vendor(Parcel in) {
        mName = in.readString();
        mDescription = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mDescription);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Vendor> CREATOR = new Parcelable.Creator<Vendor>() {
        @Override
        public Vendor createFromParcel(Parcel in) {
            return new Vendor(in);
        }

        @Override
        public Vendor[] newArray(int size) {
            return new Vendor[size];
        }
    };
}