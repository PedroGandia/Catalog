package android.test.catalog.data;


import android.test.catalog.data.local.models.Data;

import java.util.List;

import rx.Observable;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public interface AppDataStore {
    Observable<List<Data>> getData();
}
