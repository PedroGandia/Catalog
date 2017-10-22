package android.test.catalog.mainscreen.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.test.catalog.mainscreen.CategoryActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public abstract class BaseActivity<T> extends AppCompatActivity {

    public static final String CATEGORY_SELECTED = "CATEGORY_SELECTED";

    public static final String APP_ID = "APP_ID";

    public static final float ASPECT_RATIO_ITEM = 0.75f; //4:3, 75%

    public static final int MIN_DP_WIDTH_ITEM = 190;

    public static final int MIN_ROW_ITEM = 2;


    private Context context;

    private static String TAG;

    protected CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        context = getApplicationContext();
        Object intActivity = (T)this;
        TAG = intActivity.getClass().getName();

    }

    abstract protected void init(@Nullable Bundle savedInstanceState);

    protected T getInstanceSpecify(){
        return (T)this;
    }

    public Context getActualContext(){
        return context;
    }

    public String getActualTag(){
        return  TAG;
    }


    protected void redirect(Class clazz, IRedirectOptions iRedirectOptions){
        Intent intent = new Intent(((Activity)getInstanceSpecify()), clazz);

        if(iRedirectOptions != null){
            iRedirectOptions.onRedirect(intent);
        }

        startActivity(intent);
        finish();
    }


    protected void redirect(String clazzName, IRedirectOptions iRedirectOptions){
        Intent intent;
        try {
            intent = new Intent(((Activity)getInstanceSpecify()), Class.forName(clazzName));
        } catch (ClassNotFoundException | NullPointerException e) {
            Log.w(BaseActivity.class.getName(), e.getMessage()!=null?e.getMessage():"NullPointerException");
            intent = new Intent(((Activity)getInstanceSpecify()), CategoryActivity.class);
        }

        if(iRedirectOptions != null){
            iRedirectOptions.onRedirect(intent);
        }

        startActivity(intent);
        finish();


    }

    protected void showSnackbar(String message){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }


    protected int getNumberItemsRow(){
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;

        int items = (int) dpWidth / MIN_DP_WIDTH_ITEM;

        return items==1?MIN_ROW_ITEM:items;


    }

    protected int getPxHeightItems(){
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;


        return convertDpToPixel((Math.round(dpWidth / getNumberItemsRow())) * ASPECT_RATIO_ITEM);

    }

    protected int convertDpToPixel(float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return Math.round(dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
