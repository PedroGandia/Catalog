package android.test.catalog.data;


import android.test.catalog.data.local.AppLocalDataStore;
import android.test.catalog.data.local.models.Data;
import android.test.catalog.data.remote.AppRemoteDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Aditya on 23-Oct-16.
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
                        //Commeted this log due Arrayindexoutofbound exception at first lauch of application
//                        Log.d("Repo", datas.get(0).getTitle());
                        return dataList != null;
                    }
                });
    }
}
