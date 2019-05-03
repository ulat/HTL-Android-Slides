package net.eaustria.broadcastdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: entered");
        new Thread( () -> {
            for (int i = 1; i <= 3; i++) {
                //showCounter(i);
                sendMessage(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.e(TAG, "onStartCommand: " + e.getMessage());
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendMessage(int counter) {
        Log.d(TAG, "sendMessage: entered");
        Intent intent = new Intent(getPackageName() + ".counter");
        intent.putExtra("counter", counter);
        sendBroadcast(intent);
    }
}
