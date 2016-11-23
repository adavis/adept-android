package info.adavis.adeptandroid.jobs;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class AdeptAndroidJobCreator implements JobCreator
{
    @Override
    public Job create(String tag)
    {
        return new DemoJob();
    }
}
