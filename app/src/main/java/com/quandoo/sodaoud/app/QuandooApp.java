package com.quandoo.sodaoud.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import com.quandoo.sodaoud.app.job.ReservationScheduler;

import io.realm.Realm;

/**
 * Created by sofiane on 10/25/16.
 */

public class QuandooApp extends Application {

    int jobId = 1234;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        scheduleApiCall();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void scheduleApiCall() {
        ComponentName mServiceComponent = new ComponentName(this, ReservationScheduler.class);
        JobInfo.Builder builder = new JobInfo.Builder(jobId, mServiceComponent);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPeriodic(1000 * 60 * 10); //
        builder.setPersisted(true);
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
}
