package info.adavis.adeptandroid.jobs;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;

import timber.log.Timber;

public class DemoJob extends Job
{
    public static final String JOB_TAG = "demo_job";

    @NonNull
    @Override
    protected Result onRunJob(Params params)
    {
        Timber.i("successful: %s", params.getTag());

        return Result.SUCCESS;
    }
}
