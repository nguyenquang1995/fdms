package com.framgia.fdms.data.source;

import com.framgia.fdms.data.model.Category;
import com.framgia.fdms.data.model.Device;
import com.framgia.fdms.data.source.api.request.RegisterDeviceRequest;
import com.framgia.fdms.data.source.remote.DeviceRemoteDataSource;
import java.util.List;
import rx.Observable;

public class DeviceRepository {
    private DeviceRemoteDataSource mDeviceRemoteDataSource;

    public DeviceRepository(DeviceRemoteDataSource remoteDataSource) {
        mDeviceRemoteDataSource = remoteDataSource;
    }

    public Observable<List<Device>> getListDevice() {
        return mDeviceRemoteDataSource.getListDevice();
    }

    public Observable<List<Device>> searchDevices(String keyWord) {
        return mDeviceRemoteDataSource.searchDevices(keyWord);
    }
    public Observable<List<Category>> getListCategory(){
        return  mDeviceRemoteDataSource.getListCategory();
    }
    public Observable<Device> registerdevice(RegisterDeviceRequest registerdevice) {
        return mDeviceRemoteDataSource.registerdevice(registerdevice);
    }
}
