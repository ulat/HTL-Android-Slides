package net.eaustria.fragmentsdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class RightActivity extends AppCompatActivity {

    private static final String TAG = RightActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: entered");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right);
        intializeViews();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation != Configuration.ORIENTATION_PORTRAIT) {
            finish();
            return;
        }
        handleIntent();
    }

    private void handleIntent() {
        Log.d(TAG, "handleIntent: entered");
        Intent intent = getIntent();
        if (intent == null) return;
        RightFragment rightFragment = (RightFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragRight);
        int pos = intent.getIntExtra("pos", -1);
        String item = intent.getStringExtra("item");
        rightFragment.show(pos, item);
    }

    private void intializeViews() {

    }
}
