package info.adavis.adeptandroid.jobs;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.support.PersistableBundleCompat;

import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class DemoJob extends Job
{
    private static final String JOB_TAG = "demo_job";
    private static final String PARAM_NAME = "param_name";

    @NonNull
    @Override
    protected Result onRunJob(Params params)
    {
        PersistableBundleCompat extras = params.getExtras();
        String name = extras.getString(PARAM_NAME, "nobody");

        Timber.i("Hello, %s!", name);

        return Result.SUCCESS;
    }

    public static void scheduleSimple ()
    {
        new JobRequest.Builder(DemoJob.JOB_TAG)
                .setExecutionWindow(TimeUnit.SECONDS.toMillis(2), TimeUnit.SECONDS.toMillis(5))
                .build()
                .schedule();
    }

    public static void scheduleWithExtras (String name)
    {
        PersistableBundleCompat extras = new PersistableBundleCompat();
        extras.putString(PARAM_NAME, name);

        new JobRequest.Builder(DemoJob.JOB_TAG)
                .setExecutionWindow(TimeUnit.SECONDS.toMillis(2), TimeUnit.SECONDS.toMillis(5))
                .setExtras(extras)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setRequirementsEnforced(true)
                .build()
                .schedule();
    }

}
