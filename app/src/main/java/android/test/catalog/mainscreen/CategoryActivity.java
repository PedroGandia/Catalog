package android.test.catalog.mainscreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.catalog.App;
import android.test.catalog.R;
import android.test.catalog.data.AppRepository;
import android.test.catalog.data.local.models.Data;
import android.test.catalog.mainscreen.adapter.CategoryRecyclerAdapter;
import android.test.catalog.mainscreen.base.BaseActivity;
import android.test.catalog.mainscreen.base.IRedirectOptions;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class CategoryActivity extends BaseActivity<CategoryActivity> implements MainScreenContract.View, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvCategory;

    private ProgressBar pbToolbarLoading;

    private SwipeRefreshLayout swipeContainer;

    private MainScreenContract.Presenter mPresenter;

    private List<String> categoryList;

    @Inject
    AppRepository repository;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_list);
        //Inject dependency
        App.getAppComponent().inject(this);

        rvCategory = (RecyclerView) findViewById(R.id.rv_list);

        pbToolbarLoading = (ProgressBar) findViewById(R.id.pb_toolbar_loading);

        ImageView imgToolbarBack = (ImageView) findViewById(R.id.img_toolbar_back);
        imgToolbarBack.setVisibility(View.INVISIBLE);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(this);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        categoryList = new ArrayList<>();

        new MainScreenPresenter(repository, this);
    }

    @Override
    public void showDataList(List<Data> dataList) {
        pbToolbarLoading.setVisibility(View.INVISIBLE);

        List<String> listWithDuplicates = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++){
            String category = dataList.get(i).getAudienceTarget();

            if(category != null && category.contains(",")){
                String[] categoryCommaList = category.split("\\s*,\\s*");
                for (String categoryComma : categoryCommaList) {
                    if (categoryComma != null && !categoryComma.isEmpty()) {
                        listWithDuplicates.add(categoryComma);
                    }
                }
            }else{
                if (category == null) {
                    category = "";
                }
                listWithDuplicates.add(category);
            }
        }


        categoryList = new ArrayList<>(new HashSet<>(listWithDuplicates));

        initializeRvCategory();

    }

    @Override
    public void onBackPressed() {
            moveTaskToBack(true);
    }

    private void initializeRvCategory(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCategory.setLayoutManager(layoutManager);

        if (categoryList.size() > 0 ) {
            CategoryRecyclerAdapter categoryRecyclerAdapter = new CategoryRecyclerAdapter(categoryList, this);

            categoryRecyclerAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    final int item_select = rvCategory.getChildLayoutPosition(view);
                    redirect(ListAppActivity.class, new IRedirectOptions() {
                        @Override
                        public void onRedirect(Intent intent) {
                            intent.putExtra(CATEGORY_SELECTED,categoryList.get(item_select));
                        }
                    });
                }
            });

            rvCategory.setAdapter(categoryRecyclerAdapter);

        }else{
            showSnackbar(getString(R.string.title_list_empty));
        }


    }



    @Override
    public void showError(String message) {
        pbToolbarLoading.setVisibility(View.INVISIBLE);

        if (swipeContainer != null)
            swipeContainer.post(new Runnable() {
                @Override
                public void run() {
                    swipeContainer.setRefreshing(false);
                }
            });
        showSnackbar(getString(R.string.title_in_mode_offline));
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
