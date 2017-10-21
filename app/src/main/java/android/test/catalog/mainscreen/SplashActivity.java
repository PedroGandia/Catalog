package android.test.catalog.mainscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.test.catalog.R;
import android.test.catalog.mainscreen.base.BaseActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity<SplashActivity> {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private static final long SPLASH_SCREEN_DELAY = 1500;

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        // To make activity full screen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                redirect(CategoryActivity.class, null);
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

}
