package com.framgia.fdms.screen.vendor;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.framgia.fdms.BR;
import com.framgia.fdms.FDMSApplication;
import com.framgia.fdms.R;
import com.framgia.fdms.data.model.Producer;
import com.framgia.fdms.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fdms.utils.Constant.OUT_OF_INDEX;

/**
 * Exposes the data to be used in the Vendor screen.
 */
public class VendorViewModel extends BaseObservable
    implements VendorContract.ViewModel, Parcelable {
    private VendorContract.Presenter mPresenter;
    private ListVendorAdapter mAdapter;
    private List<Producer> mVendors = new ArrayList<>();
    private AppCompatActivity mActivity;
    private VendorDialog mVendorDialog;
    private int mPositionScroll = OUT_OF_INDEX;

    public VendorViewModel(Activity activity) {
        mActivity = (AppCompatActivity) activity;
        mAdapter = new ListVendorAdapter(FDMSApplication.getInstant(), this, mVendors);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(VendorContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public ListVendorAdapter getAdapter() {
        return mAdapter;
    }

    @Bindable
    public int getPositionScroll() {
        return mPositionScroll;
    }

    public void setPositionScroll(int positionScroll) {
        mPositionScroll = positionScroll;
        notifyPropertyChanged(BR.positionScroll);
    }

    @Override
    public void onEditVendorClick(Producer vendor) {
        mVendorDialog = VendorDialog.newInstant(this, vendor);
        mVendorDialog.show(mActivity.getSupportFragmentManager(),
            Constant.BundleConstant.BUNDLE_DEVICE);
    }

    @Override
    public void onDeleteVendorClick(final Producer vendor) {
        final int indexRemove = mVendors.indexOf(vendor);
        mVendors.remove(vendor);
        mAdapter.notifyItemRemoved(indexRemove);
        Snackbar.make(mActivity.findViewById(android.R.id.content), R.string.title_vendor_delete,
            Snackbar.LENGTH_LONG)
            .setAction(R.string.title_undo, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mVendors.add(indexRemove, vendor);
                    mAdapter.notifyItemInserted(indexRemove);
                    setPositionScroll(indexRemove);
                }
            })
            .addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    setPositionScroll(OUT_OF_INDEX);
                    // TODO: next task
                }
            })
            .show();
    }

    @Override
    public void onLoadVendorSuccess(List<Producer> vendors) {
        mVendors.clear();
        mVendors.addAll(vendors);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadVendorFailed() {
        Toast.makeText(FDMSApplication.getInstant(), R.string.msg_load_data_fails,
            Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditSubmitClick(Producer vendor) {
        mVendorDialog.dismiss();
    }

    @Override
    public void onEditCancelClick(Producer vendor) {
        mVendorDialog.dismiss();
    }

    protected VendorViewModel(Parcel in) {
        mPresenter = (VendorContract.Presenter) in.readValue(
            VendorContract.Presenter.class.getClassLoader());
        mAdapter = (ListVendorAdapter) in.readValue(ListVendorAdapter.class.getClassLoader());
        if (in.readByte() == 0x01) {
            mVendors = new ArrayList<Producer>();
            in.readList(mVendors, Producer.class.getClassLoader());
        } else {
            mVendors = null;
        }
        mActivity = (AppCompatActivity) in.readValue(AppCompatActivity.class.getClassLoader());
        mVendorDialog = (VendorDialog) in.readValue(VendorDialog.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mPresenter);
        dest.writeValue(mAdapter);
        if (mVendors == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mVendors);
        }
        dest.writeValue(mActivity);
        dest.writeValue(mVendorDialog);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VendorViewModel> CREATOR =
        new Parcelable.Creator<VendorViewModel>() {
            @Override
            public VendorViewModel createFromParcel(Parcel in) {
                return new VendorViewModel(in);
            }

            @Override
            public VendorViewModel[] newArray(int size) {
                return new VendorViewModel[size];
            }
        };
}