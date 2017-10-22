package android.test.catalog.dagger.component;


import android.test.catalog.dagger.module.AppModule;
import android.test.catalog.dagger.module.DataModule;
import android.test.catalog.data.remote.AppRemoteDataStore;
import android.test.catalog.mainscreen.CategoryActivity;
import android.test.catalog.mainscreen.ListAppActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    void inject(CategoryActivity activity);
    void inject(ListAppActivity activity);
    void inject(AppRemoteDataStore appRemoteDataStore);
}
