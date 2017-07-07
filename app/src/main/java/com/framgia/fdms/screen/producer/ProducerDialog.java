package com.framgia.fdms.screen.producer;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;

import com.framgia.fdms.FDMSApplication;
import com.framgia.fdms.R;
import com.framgia.fdms.data.model.Producer;
import com.framgia.fdms.databinding.DialogEditProducerBinding;
import com.framgia.fdms.utils.Constant;

/**
 * Created by framgia on 04/07/2017.
 */
public class ProducerDialog extends DialogFragment implements ProducerDialogContract {
    private ObservableField<String> mMessageError = new ObservableField<>();
    private Producer mProducer;

    public static ProducerDialog newInstant(Producer producer) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.BundleConstant.BUNDLE_PRODUCER, producer);
        ProducerDialog producerDialog = new ProducerDialog();
        producerDialog.setArguments(bundle);
        return producerDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mProducer = bundle.getParcelable(Constant.BundleConstant.BUNDLE_PRODUCER);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        DialogEditProducerBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_edit_producer, null, false);
        binding.setProducer(mProducer);
        binding.setDialog(this);
        builder.setView(binding.getRoot());
        return builder.create();
    }

    @Override
    public void onEditSubmitClick() {
        if (TextUtils.isEmpty(mProducer.getName())) {
            mMessageError.set(FDMSApplication.getInstant()
                .getResources().getString(R
                .string
                .msg_error_user_name));
            return;
        }
        dismiss();
        //todo later, send new data to server
    }

    @Override
    public void onEditCancelClick() {
        dismiss();
    }

    public ObservableField<String> getMessageError() {
        return mMessageError;
    }

    public void setMessageError(ObservableField<String> messageError) {
        mMessageError = messageError;
    }
}
