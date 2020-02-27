package com.example.jobscheduler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void scheduleJob(View view) {

        ComponentName componentName = new ComponentName(this, ExampleJobService.class);

        JobInfo jobInfo = new JobInfo.Builder( 123, componentName )
                .setRequiresCharging(true)
                .setRequiredNetworkType( JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 1000 * 60)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int results = jobScheduler.schedule(jobInfo);

        if( results == JobScheduler.RESULT_SUCCESS){
            Log.d( TAG, "Job Scheduled");
        }else{
            Log.d( TAG, "Job Scheduled failed");
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelJob(View view) {

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel( 123 );
        Log.d( TAG, "Job Cancelled");

    }
}
