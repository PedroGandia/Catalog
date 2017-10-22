package android.test.catalog.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.catalog.App;
import android.test.catalog.R;
import android.test.catalog.data.AppRepository;
import android.test.catalog.data.local.models.Data;
import android.test.catalog.mainscreen.adapter.ListAppRecyclerAdapter;
import android.test.catalog.mainscreen.base.BaseActivity;
import android.test.catalog.mainscreen.base.IRedirectOptions;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class ListAppActivity extends BaseActivity<ListAppActivity> implements MainScreenContract.View, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvListApp;

    private ProgressBar pbToolbarLoading;

    private ImageView imgToolbarBack;

    private SwipeRefreshLayout swipeContainer;

    private MainScreenContract.Presenter mPresenter;

    private List<Data> listAppList;

    @Inject
    AppRepository repository;

    private int count;

    private String categorySelected;


    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_list);
        App.getAppComponent().inject(this);

        rvListApp = (RecyclerView) findViewById(R.id.rv_list);

        pbToolbarLoading = (ProgressBar) findViewById(R.id.pb_toolbar_loading);

        imgToolbarBack = (ImageView) findViewById(R.id.img_toolbar_back);
        onClickListenerToolbarBack();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            categorySelected = extras.getString(CATEGORY_SELECTED);
        }

        TextView txtToolbarTitle = (TextView) findViewById(R.id.txt_toolbar_title);
        if (categorySelected != null && !categorySelected.isEmpty()) {
            String categoryName = categorySelected.substring(0, 1).toUpperCase() + categorySelected.substring(1);
            txtToolbarTitle.setText(categoryName);
        }

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(this);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);


        listAppList = new ArrayList<>();

        new MainScreenPresenter(repository, this);

        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }



    @Override
    public void showDataList(List<Data> dataList) {
        pbToolbarLoading.setVisibility(View.INVISIBLE);

        listAppList.clear();

        for (int i = 0; i < dataList.size(); i++){
            String category = dataList.get(i).getAudienceTarget();
            if (categorySelected != null && !categorySelected.isEmpty()) {
                if (category != null && category.contains(categorySelected)) {
                    listAppList.add(dataList.get(i));
                }
            }else{
                if (category == null || category.isEmpty()) {
                    listAppList.add(dataList.get(i));
                }
            }
        }

        initializeRvListApp();

    }


    private void initializeRvListApp(){

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, getNumberItemsRow());
        rvListApp.setLayoutManager(layoutManager);

        if (listAppList.size() > 0 ) {
            ListAppRecyclerAdapter listAppRecyclerAdapter = new ListAppRecyclerAdapter(listAppList, getPxHeightItems(), this);

            listAppRecyclerAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    final int item_select = rvListApp.getChildLayoutPosition(view);
                    redirect(DetailAppActivity.class, new IRedirectOptions() {
                        @Override
                        public void onRedirect(Intent intent) {
                            intent.putExtra(APP_ID,listAppList.get(item_select).getId());
                            intent.putExtra(CATEGORY_SELECTED,categorySelected);
                        }
                    });
                }
            });

            rvListApp.setAdapter(listAppRecyclerAdapter);

        }else{
            showSnackbar(getString(R.string.title_list_empty));
        }


    }

    private void onClickListenerToolbarBack(){
        imgToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect(CategoryActivity.class, null);
            }
        });
    }

    @Override
    public void showError(String message) {
        pbToolbarLoading.setVisibility(View.INVISIBLE);

        showSnackbar(getString(R.string.title_in_mode_offline));
        if (swipeContainer != null)
            swipeContainer.post(new Runnable() {
                @Override
                public void run() {
                    swipeContainer.setRefreshing(false);
                }
            });
    }

    @Override
    public void showComplete(String msg) {
        if (swipeContainer != null)
            swipeContainer.post(new Runnable() {
                @Override
                public void run() {
                    swipeContainer.setRefreshing(false);
                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
            mPresenter.subscribe();
            pbToolbarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
        pbToolbarLoading.setVisibility(View.INVISIBLE);
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
    }

    @Override
    public void onBackPressed() {
        redirect(CategoryActivity.class, null);
    }

    @Override
    public void setPresenter(MainScreenContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        mPresenter.loadDataFromRemoteDataStore();
    }
}
