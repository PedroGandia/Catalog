package android.test.catalog.data;


import android.test.catalog.data.local.models.Data;

import java.util.List;

import rx.Observable;

/**
 * Created by Aditya on 23-Oct-16.
 */

public interface AppDataStore {
    Observable<List<Data>> getData();
}
