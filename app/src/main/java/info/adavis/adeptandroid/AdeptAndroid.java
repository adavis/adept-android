package info.adavis.adeptandroid;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class AdeptAndroid extends Application {

    private static final String ADEPT_REALM = "adept.realm";

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        configureRealm();

        Timber.i("Creating our Application");
    }

    private void configureRealm ()
    {
        RealmConfiguration configuration = new RealmConfiguration.Builder( this )
                .name( ADEPT_REALM )
                .build();

        Realm.setDefaultConfiguration( configuration );
    }
}
