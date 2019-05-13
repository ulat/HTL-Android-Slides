package net.eaustria.fragmentsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity  implements  LeftFragment.OnSelectionChangedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private RightFragment rightFragment;
    private boolean showRight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
    }

    private void initializeView() {
        Log.d(TAG, "initializeView: entered");
        rightFragment  = (RightFragment) getSupportFragmentManager().findFragmentById(R.id.fragRight);
        showRight = rightFragment != null && rightFragment.isInLayout();
    }

    @Override
    public void onSelectionChanged(int pos, String item) {
        if (showRight) rightFragment.show(pos, item);
        else callRightActivity(pos, item);
    }

    private void callRightActivity(int pos, String item) {
        Log.d(TAG, "callRightActivity: entered");
        Intent intent = new Intent(this, RightActivity.class);
        intent.putExtra("pos", pos);
        intent.putExtra("item", item);
        startActivity(intent);
    }
}
