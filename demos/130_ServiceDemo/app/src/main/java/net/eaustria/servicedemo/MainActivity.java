package net.eaustria.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {
        Log.d(TAG, "startService: entered");
        Intent intent = new Intent(this, MyService.class);
        // the service can use the data from the intent
        String msg = "Service started from MainActivity";
        intent.putExtra("msg", msg);
        startService(intent);
    }

    public void stopService(View view) {
        Log.d(TAG, "stopService: entered");
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void startServiceWorker(View view) {
        Log.d(TAG, "startService: entered");
        Intent intent = new Intent(this, MyService.class);
        // the service can use the data from the intent
        String threadName = "Service_Worker_Thread";
        intent.putExtra("startNewThread", true);
        startService(intent);
    }

    public void startIntentService(View view) {
        Log.d(TAG, "startIntentService: entered");
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }
}
