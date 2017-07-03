package com.framgia.fdms.screen.vendor;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.fdms.BaseRecyclerViewAdapter;
import com.framgia.fdms.R;
import com.framgia.fdms.data.model.Vendor;
import com.framgia.fdms.databinding.ItemVendorBinding;
import java.util.List;

/**
 * Created by framgia on 03/07/2017.
 */

public class ListVendorAdapter
        extends BaseRecyclerViewAdapter<Vendor, ListVendorAdapter.ViewHolder> {
    private List<Vendor> mVendors;

    protected ListVendorAdapter(@NonNull Context context, @NonNull List<Vendor> vendors) {
        super(context);
        mVendors = vendors;
    }

    @Override
    public void onUpdatePage(List<Vendor> datas) {
        if (datas == null) {
            return;
        }
        mVendors.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public ListVendorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemVendorBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_vendor, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListVendorAdapter.ViewHolder holder, int position) {
        holder.bindData(mVendors.get(position));
    }

    @Override
    public int getItemCount() {
        return mVendors == null ? 0 : mVendors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemVendorBinding mBinding;

        public ViewHolder(ItemVendorBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bindData(Vendor device) {
            if (device == null) {
                return;
            }
            mBinding.setVendor(device);
            mBinding.executePendingBindings();
        }
    }
}
