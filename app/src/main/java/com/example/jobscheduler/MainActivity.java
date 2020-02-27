package com.example.jobscheduler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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

        

    }

    public void cancelJob(View view) {
    }
}
