package com.framgia.fdms.data.source;

import com.framgia.fdms.data.model.Producer;
import java.util.List;
import java.util.Map;
import rx.Observable;

/**
 * Created by beepi on 05/07/2017.
 */

public interface MakerDataSource {
    interface RemoteDataSource {
        Observable<List<Producer>> getListMarkers(Map<String, String> params);
    }
}
