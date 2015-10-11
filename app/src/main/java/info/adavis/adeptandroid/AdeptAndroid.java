package info.adavis.adeptandroid;

import android.app.Application;

import info.adavis.adeptandroid.utils.PaymentsManager;
import timber.log.Timber;

public class AdeptAndroid extends Application {

    private PaymentsManager paymentsManager;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        paymentsManager = new PaymentsManager(getApplicationContext());

        Timber.i("Creating our Application");
    }

    public PaymentsManager getPaymentsManager() {
        return paymentsManager;
    }

}
