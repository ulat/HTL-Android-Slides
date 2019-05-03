package net.eaustria.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: entered");
        Log.d(TAG, "onHandleIntent: Thread: " +
                Thread.currentThread().getName());
        for (int i=1; i<=3; i++) {
            Log.d(TAG, "onHandleIntent: ....working");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "onHandleIntent: " + e.getMessage());
            }
        }
        Log.d(TAG, "onHandleIntent: finished");
    }
}
