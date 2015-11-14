package info.adavis.adeptandroid;

import android.app.Application;

import timber.log.Timber;

public class AdeptAndroid extends Application {

    public static AdeptAndroid app = null;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Timber.i("Creating our Application");
    }
}
