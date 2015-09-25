package info.adavis.adeptandroid;

import android.app.Application;

import timber.log.Timber;

public class AdeptAndroid extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Timber.i("Creating our Application");
    }
}
