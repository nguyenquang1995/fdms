package com.framgia.fdms.data.source.remote;

import com.framgia.fdms.data.model.Vendor;
import com.framgia.fdms.data.source.VendorDataSource;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by framgia on 03/07/2017.
 */

public final class VendorRemoteDataSource implements VendorDataSource.RemoteDataSource {
    private static VendorRemoteDataSource sInstances;

    private VendorRemoteDataSource() {
    }

    public static VendorRemoteDataSource getInstances() {
        if (sInstances == null) {
            sInstances = new VendorRemoteDataSource();
        }
        return sInstances;
    }

    @Override
    public Observable<List<Vendor>> getListVendor() {
        // // TODO: later
        List<Vendor> list = new ArrayList<>();
        list.add(new Vendor("laptop", "mini"));
        list.add(new Vendor("PC", "Cau hinh cao"));
        list.add(new Vendor("modern", "dat"));
        list.add(new Vendor("mobile", "re"));
        list.add(new Vendor("PC2", "Cau hinh cao1"));
        list.add(new Vendor("PC3", "Cau hinh cao2"));
        list.add(new Vendor("laptop4", "mini7"));
        list.add(new Vendor("PC5", "Cau hinh cao3"));
        list.add(new Vendor("PC6", "Cau hinh cao4"));
        list.add(new Vendor("laptop7", "mini2"));
        list.add(new Vendor("PC8", "Cau hinh cao5"));
        list.add(new Vendor("PC9", "Cau hinh cao6"));
        return Observable.just(list);
    }
}
