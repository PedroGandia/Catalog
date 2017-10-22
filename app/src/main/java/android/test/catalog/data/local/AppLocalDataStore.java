package android.test.catalog.data.local;


import android.content.Context;
import android.support.annotation.NonNull;
import android.test.catalog.data.AppDataStore;
import android.test.catalog.data.local.models.Data;
import android.test.catalog.data.local.models.DataStorIOContentResolverDeleteResolver;
import android.test.catalog.data.local.models.DataStorIOContentResolverGetResolver;
import android.test.catalog.data.local.models.DataStorIOContentResolverPutResolver;

import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.Query;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class AppLocalDataStore implements AppDataStore {

    private StorIOContentResolver mStorIOContentResolver;

    @Inject
    public AppLocalDataStore(@NonNull Context context) {
        mStorIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(context.getContentResolver())
                .addTypeMapping(Data.class, ContentResolverTypeMapping.<Data>builder()
                        .putResolver(new DataStorIOContentResolverPutResolver())
                        .getResolver(new DataStorIOContentResolverGetResolver())
                        .deleteResolver(new DataStorIOContentResolverDeleteResolver())
                        .build()
                ).build();
    }


    @Override
    public Observable<List<Data>> getData() {
        return mStorIOContentResolver.get()
                .listOfObjects(Data.class)
                .withQuery(Query.builder().uri(DatabaseContract.Data.CONTENT_URI)
                .build())
                .prepare()
                .asRxObservable();
    }

    public void saveDataToDatabase(List<Data> dataList) {
        List<Data> dataListDL = mStorIOContentResolver.get()
                .listOfObjects(Data.class)
                .withQuery(Query.builder().uri(DatabaseContract.Data.CONTENT_URI)
                        .build())
                .prepare()
                .executeAsBlocking();
        mStorIOContentResolver.delete().objects(dataListDL).prepare().executeAsBlocking();
        mStorIOContentResolver.put().objects(dataList).prepare().executeAsBlocking();
    }
}
