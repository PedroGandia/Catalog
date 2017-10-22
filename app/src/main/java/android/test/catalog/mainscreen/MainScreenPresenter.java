package android.test.catalog.mainscreen;


import android.test.catalog.data.AppRepository;
import android.test.catalog.data.local.models.Data;
import android.test.catalog.data.remote.AppRemoteDataStore;
import android.util.Log;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class MainScreenPresenter implements MainScreenContract.Presenter {

    private static final String TAG = MainScreenPresenter.class.getSimpleName();
    private Subscription mSubscription;
    private AppRepository mAppRepository;
    private MainScreenContract.View mView;

    public MainScreenPresenter(AppRepository mAppRepository, MainScreenContract.View mView) {
        this.mAppRepository = mAppRepository;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loadData(boolean isRemote) {
        mSubscription = mAppRepository.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Data>>() {
                    @Override
                    public void onCompleted(){
                        mView.showComplete("loadData");
                    }

                    @Override
                    public void onError(Throwable e){
                        Log.d(TAG, e.toString());
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onNext(List<Data> dataList){
                            mView.showDataList(dataList);
                    }
                });
    }

    @Override
    public void loadDataFromRemoteDataStore() {
        new AppRemoteDataStore().getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Data>>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete("loadDataFromRemote");
                        loadData(true);
                    }

                    @Override
                    public void onError(Throwable e){
                        Log.d(TAG, e.toString());
                        mView.showError(e.toString());
                        loadData(true);
                    }

                    @Override
                    public void onNext(List<Data> dataList){

                    }
                });
    }

    @Override
    public void subscribe() {
        loadDataFromRemoteDataStore();
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }
}
