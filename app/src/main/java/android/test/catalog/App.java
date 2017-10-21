package android.test.catalog;

import android.app.Application;
import android.test.catalog.dagger.component.AppComponent;
import android.test.catalog.dagger.component.DaggerAppComponent;
import android.test.catalog.dagger.module.AppModule;
import android.test.catalog.dagger.module.DataModule;


public class App extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule("https://www.reddit.com/"))
                .build();
    }


    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
