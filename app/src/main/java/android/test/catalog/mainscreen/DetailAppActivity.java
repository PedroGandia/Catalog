package android.test.catalog.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.test.catalog.R;
import android.test.catalog.mainscreen.base.BaseActivity;
import android.test.catalog.mainscreen.base.IRedirectOptions;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailAppActivity extends BaseActivity<DetailAppActivity> {

    private ProgressBar pbToolbarLoading;

    private ImageView imgToolbarBack;

    private String appId;

    private String categorySelected;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail_app);

        imgToolbarBack = (ImageView) findViewById(R.id.img_toolbar_back);
        onClickListenerToolbarBack();

        pbToolbarLoading = (ProgressBar) findViewById(R.id.pb_toolbar_loading);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        TextView txtToolbarTitle = (TextView) findViewById(R.id.txt_toolbar_title);
        txtToolbarTitle.setText(this.getTitle());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            appId = extras.getString(APP_ID);
            categorySelected = extras.getString(CATEGORY_SELECTED);
        }

        if (appId != null && !appId.isEmpty()) {
            Toast.makeText(this, "appId: "+ appId, Toast.LENGTH_SHORT).show();
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
        pbToolbarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pbToolbarLoading.setVisibility(View.INVISIBLE);
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
}
