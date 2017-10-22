package android.test.catalog.mainscreen;


import android.test.catalog.BasePresenter;
import android.test.catalog.BaseView;
import android.test.catalog.data.local.models.Data;

import java.util.List;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class MainScreenContract {

    interface View extends BaseView<Presenter> {

        void showDataList(List<Data> dataList);

        void showError(String message);

        void showComplete(String message);
    }

    interface Presenter extends BasePresenter {
        void loadData(boolean isRemote);
        void loadDataFromRemoteDataStore();
    }
}
