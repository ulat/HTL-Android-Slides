package net.eaustria.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static String TAG = MyService.class.getSimpleName();
    private Thread worker;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Service: onStartCommand");
        Log.d(TAG, "onStartCommand: Thread-name: "
                + Thread.currentThread().getName());
        if (intent.hasExtra("msg")) {
            Log.d(TAG, "onStartCommand: Message:" + intent.getStringExtra("msg"));
        }
        if (intent.hasExtra("startNewThread")) {
            if (!worker.isAlive()) worker.start();
        } else {
            new Thread(() -> {
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    Log.e(TAG, "onStartCommand: " + e.getMessage() +
                            "\n" + e.getStackTrace());
                }
                Log.d(TAG, "onStartCommand: selfstopping service");
                stopSelf();
            }).start();
        }
        return START_STICKY;
    }

    private void doWork() {
        try {
            Log.d(TAG, "doWork: entered");
            Log.d(TAG, "Thread start: thread-name: "
                    + Thread.currentThread().getName());
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            Log.e(TAG, "onStartCommand: " + e.getMessage() +
                    "\n" + e.getStackTrace());
        }
        Log.d(TAG, "Thread end: thread-name: "
                + Thread.currentThread().getName());

    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Service started");
        worker = new Thread(this::doWork);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service destroyed");
        worker.interrupt();
        worker = null;
        super.onDestroy();
    }
}
