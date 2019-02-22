package net.eaustria.preferencesdemoapp;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;

import android.os.Bundle;

public class MySettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new MySettingsFragment())
                .commit();
    }
}
