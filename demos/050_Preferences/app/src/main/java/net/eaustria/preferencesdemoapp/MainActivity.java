package net.eaustria.preferencesdemoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int RQ_PREFERENCES = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences prefs;
    private SharedPreferences.OnSharedPreferenceChangeListener preferencesChangeListener;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        assertPreferencesInFile();
        preferencesChangeListener = ( sharedPrefs, key ) -> preferenceChanged(sharedPrefs, key);
    }

    private void assertPreferencesInFile() {
        try {
            String versionKey = "longVersionCode";
            long currentVersion = getPackageManager()
                    .getPackageInfo(getPackageName(), 0)
                    .getLongVersionCode();
            int lastStoredVersion = prefs.getInt(versionKey, -1);
            if (lastStoredVersion == currentVersion) return;
            prefs.edit()
                    .putLong(versionKey, currentVersion)
                    .putString("username", prefs.getString("username", "Max"))
                    .putString("city", prefs.getString("city", "1100"))
                    .putBoolean("online", prefs.getBoolean("online", false))
                    .apply();
        } catch (Exception e) {
            Log.e(TAG, "asertPreferencesInFile: ", e);
        }
    }

    private void preferenceChanged(SharedPreferences sharedPrefs, String key) {
        Map<String, ?> allEntries = sharedPrefs.getAll();
        String sValue = "";
        if (allEntries.get(key) instanceof String)
            sValue = sharedPrefs.getString(key, "");
        else if (allEntries.get(key) instanceof Boolean)
            sValue = String.valueOf(sharedPrefs.getBoolean(key, false));
        Toast.makeText(this, key + " new Value: " + sValue, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_preferences) {
            Intent intent = new Intent(this, MySettingsActivity.class);
            startActivityForResult(intent, RQ_PREFERENCES);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showPrefs(View view) {
        String username = prefs.getString("username", "Not available");
        boolean isOnline = prefs.getBoolean("online", false);
        String zipCode = prefs.getString("city", "");
        String msg = String.format("user:%s - PLZ:%d - online:%b", username, zipCode, isOnline);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void editPrefs(View view) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("test", "" + random.nextInt(100));
        editor.commit();
    }
}

