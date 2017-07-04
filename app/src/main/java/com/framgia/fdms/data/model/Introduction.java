package com.framgia.fdms.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import com.framgia.fdms.BR;
import java.io.Serializable;

/**
 * Created by MyPC on 04/07/2017.
 */

public class Introduction extends BaseObservable implements Serializable {
    private String mTitle;
    private String mHeader;
    private String mContent;
    private Drawable mImage;
    private int mColor;

    public Introduction(String title, String header, String content, Drawable image, int color) {
        mTitle = title;
        mHeader = header;
        mContent = content;
        mImage = image;
        mColor = color;
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getHeader() {
        return mHeader;
    }

    public void setHeader(String header) {
        mHeader = header;
        notifyPropertyChanged(BR.header);
    }

    @Bindable
    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
        notifyPropertyChanged(BR.content);
    }

    @Bindable
    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable image) {
        mImage = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
        notifyPropertyChanged(BR.color);
    }

    public static class Builder {
        private String mNestedTitle;
        private String mNestedHeader;
        private String mNestedContent;
        private Drawable mNestedImage;
        private int mNestedColor;

        public Builder setTitle(String title) {
            mNestedTitle = title;
            return this;
        }

        public Builder setHeader(String header) {
            mNestedHeader = header;
            return this;
        }

        public Builder setContent(String content) {
            mNestedContent = content;
            return this;
        }

        public Builder setImage(Drawable image) {
            mNestedImage = image;
            return this;
        }

        public Builder setColor(int color) {
            mNestedColor = color;
            return this;
        }

        public Introduction create() {
            return new Introduction(mNestedTitle, mNestedHeader, mNestedContent, mNestedImage,
                    mNestedColor);
        }
    }
}
