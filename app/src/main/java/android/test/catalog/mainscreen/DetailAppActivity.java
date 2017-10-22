package android.test.catalog.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.test.catalog.R;
import android.test.catalog.data.local.models.Data;
import android.test.catalog.mainscreen.base.BaseActivity;
import android.test.catalog.mainscreen.base.IRedirectOptions;
import android.test.catalog.mainscreen.loader.DataLoader;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class DetailAppActivity extends BaseActivity<DetailAppActivity> {

    private ProgressBar pbToolbarLoading;

    private Toolbar toolbar;

    private ImageView imgToolbarBack;

    private ImageView imgHeaderApp;

    private TextView txtName;

    private TextView txtHeader;

    private TextView txtDescription;

    private TextView txtTarget;

    private String categorySelected;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail_app);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgHeaderApp = (ImageView) findViewById(R.id.img_header_app);

        imgToolbarBack = (ImageView) findViewById(R.id.img_toolbar_back);
        onClickListenerToolbarBack();

        pbToolbarLoading = (ProgressBar) findViewById(R.id.pb_toolbar_loading);

        txtName = (TextView) findViewById(R.id.txt_name);

        txtHeader = (TextView) findViewById(R.id.txt_header);

        txtDescription = (TextView) findViewById(R.id.txt_description);

        txtTarget = (TextView) findViewById(R.id.txt_target);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String appId = extras.getString(APP_ID);
            categorySelected = extras.getString(CATEGORY_SELECTED);
            if (appId != null && !appId.isEmpty()) {
                initializeData(appId);
            }
        }



    }

    private void onClickListenerToolbarBack(){
        imgToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect(ListAppActivity.class, new IRedirectOptions() {
                    @Override
                    public void onRedirect(Intent intent) {
                        intent.putExtra(CATEGORY_SELECTED,categorySelected);
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //pbToolbarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //pbToolbarLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        redirect(ListAppActivity.class, new IRedirectOptions() {
            @Override
            public void onRedirect(Intent intent) {
                intent.putExtra(CATEGORY_SELECTED,categorySelected);
            }
        });
    }

    private void initializeData(String appId) {
        getSupportLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Data>() {
            @Override
            public Loader<Data> onCreateLoader(int id, Bundle args) {
                return new DataLoader(DetailAppActivity.this, appId);
            }

            @Override
            public void onLoadFinished(Loader<Data> loader, Data data) {
                if (data != null) {
                    txtName.setText(data.getDisplayName());
                    txtHeader.setText(data.getHeaderTitle());
                    txtDescription.setText(data.getDescription());
                    txtTarget.setText(data.getAudienceTarget());
                    Glide.with(getApplicationContext())
                            .load(data.getIconImg())
                            .error(R.mipmap.ic_launcher)
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imgHeaderApp);
                }
            }

            @Override
            public void onLoaderReset(Loader<Data> data) {

            }
        }).forceLoad();

    }
}
