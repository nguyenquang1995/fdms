package com.framgia.fdms.data.source.remote;

import com.framgia.fdms.data.model.Producer;
import com.framgia.fdms.data.source.MakerDataSource;
import com.framgia.fdms.data.source.api.service.FDMSApi;
import java.util.List;
import java.util.Map;
import rx.Observable;

/**
 * Created by beepi on 05/07/2017.
 */

public class MakerRemoteDataSource extends BaseRemoteDataSource
        implements MakerDataSource.RemoteDataSource {
    public MakerRemoteDataSource(FDMSApi fdmsApi) {
        super(fdmsApi);
    }

    @Override
    public Observable<List<Producer>> getListMarkers(Map<String, String> params) {
        return null;
    }
}
