package android.test.catalog.data;


import android.test.catalog.data.local.AppLocalDataStore;
import android.test.catalog.data.local.models.Data;
import android.test.catalog.data.remote.AppRemoteDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class AppRepository implements AppDataStore {

    private AppLocalDataStore mAppLocalDataStore;
    private AppRemoteDataStore mAppRemoteDataStore;


    @Inject
    public AppRepository(AppLocalDataStore mAppLocalDataStore, AppRemoteDataStore mAppRemoteDataStore) {
        this.mAppLocalDataStore = mAppLocalDataStore;
        this.mAppRemoteDataStore = mAppRemoteDataStore;
    }

    @Override
    public Observable<List<Data>> getData() {
        return Observable.concat(mAppLocalDataStore.getData(), mAppRemoteDataStore.getData())
                .first(new Func1<List<Data>, Boolean>() {
                    @Override
                    public Boolean call(List<Data> dataList) {
                        return dataList != null;
                    }
                });
    }
}
