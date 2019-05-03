package net.eaustria.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver receiver = new MyBroadcastReceiver();
    private IntentFilter filter = new IntentFilter("net.eaustria.broadcastdemo.counter");
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.txt_counter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, filter);
        registerReceiver(new MyStaticReceiver(),
                new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void StartService(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {

        private final String TAG = MyBroadcastReceiver.class.getSimpleName();

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: entered");
            if (intent.hasExtra("counter")) {
                int counter = intent.getIntExtra("counter", -1);
                Log.d(TAG, "onReceive: counter value: " + counter);
                mTextView.setText(String.valueOf(counter));
            }
        }
    }
}
