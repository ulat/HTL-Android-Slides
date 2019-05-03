package net.eaustria.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyStaticReceiver extends BroadcastReceiver {
    private static final String TAG = MyStaticReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: entered");
        String action = intent.getAction();
        Log.d(TAG, "onReceive: Action: " + action);
    }
}
