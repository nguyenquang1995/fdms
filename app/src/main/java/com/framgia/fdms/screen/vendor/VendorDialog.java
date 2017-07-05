package com.framgia.fdms.screen.vendor;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.framgia.fdms.R;
import com.framgia.fdms.data.model.Vendor;
import com.framgia.fdms.databinding.DialogEditVendorBinding;
import com.framgia.fdms.utils.Constant;

/**
 * Created by framgia on 04/07/2017.
 */

public class VendorDialog extends DialogFragment {

    public static VendorDialog newInstant(VendorViewModel vendorViewModel, Vendor vendor) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.BundleConstant.BUNDLE_DEVICE, vendor);
        bundle.putParcelable(Constant.BundleConstant.BUNDLE_CONTENT, vendorViewModel);
        VendorDialog vendorDialog = new VendorDialog();
        vendorDialog.setArguments(bundle);
        return vendorDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Vendor vendor = bundle.getParcelable(Constant.BundleConstant.BUNDLE_DEVICE);
        VendorViewModel vendorViewModel =
                bundle.getParcelable(Constant.BundleConstant.BUNDLE_CONTENT);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        DialogEditVendorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_edit_vendor, null, false);
        binding.setVendor(vendor);
        binding.setViewModel(vendorViewModel);
        builder.setView(binding.getRoot());
        return builder.create();
    }
}
