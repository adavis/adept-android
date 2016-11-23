package info.adavis.adeptandroid;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

import info.adavis.adeptandroid.jobs.AdeptAndroidJobCreator;
import info.adavis.adeptandroid.jobs.DemoJob;
import timber.log.Timber;

public class AdeptAndroid extends Application
{
    private static AdeptAndroid instance;

    @Override
    public void onCreate()
    {
        super.onCreate();

        instance = this;

        if (BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }

        JobManager.create(this).addJobCreator(new AdeptAndroidJobCreator());

        new JobRequest.Builder(DemoJob.JOB_TAG)
                .setExecutionWindow(TimeUnit.SECONDS.toMillis(2), TimeUnit.SECONDS.toMillis(5))
                .build()
                .schedule();

        Timber.i("Creating our Application");
    }

    public static AdeptAndroid getInstance ()
    {
        return instance;
    }

    public static boolean hasNetwork ()
    {
        return instance.checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
