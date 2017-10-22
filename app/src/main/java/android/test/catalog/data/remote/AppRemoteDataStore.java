package android.test.catalog.data.remote;


import android.test.catalog.App;
import android.test.catalog.data.AppDataStore;
import android.test.catalog.data.local.AppLocalDataStore;
import android.test.catalog.data.local.models.Data;
import android.test.catalog.data.local.models.DataResponse;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class AppRemoteDataStore implements AppDataStore {

    @Inject
    Retrofit retrofit;

    @Inject
    AppLocalDataStore appLocalDataStore;

    public AppRemoteDataStore() {
        App.getAppComponent().inject(this);
    }


    @Override
    public Observable<List<Data>> getData() {
        Log.d("REMOTE","Loaded from remote");

        return retrofit.create(DataService.class).getDataList().map(new Func1<DataResponse, List<Data>>() {
            @Override
            public List<Data> call(DataResponse dataResponse) {

                List<DataResponse.Children> childrenList = dataResponse.getData().getChildren();
                List<Data> dataList = new ArrayList<Data>();
                for (int i = 0; i < childrenList.size(); i++) {
                    dataList.add(childrenList.get(i).getData());
                }
                appLocalDataStore.saveDataToDatabase(dataList);
                return dataList;
            }
        });
    }


    private interface DataService {
        @GET("/reddits.json")
        Observable<DataResponse> getDataList();
    }
}
