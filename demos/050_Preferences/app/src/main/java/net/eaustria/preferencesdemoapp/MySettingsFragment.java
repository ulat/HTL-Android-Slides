package net.eaustria.preferencesdemoapp;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;


public class MySettingsFragment extends PreferenceFragmentCompat {
    private static final String TAG = MySettingsActivity.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        Preference prefUsername = findPreference("username");
        prefUsername.setOnPreferenceChangeListener( (preference, newValue) -> {
            Log.d(TAG, "onPreferenceChange: " + preference.getKey() + " --> " +
                    newValue.toString());
            if (preference.getKey().equals("username")) {
                String newUsername = newValue.toString();
                boolean isValid = newUsername.length() > 3;
                Log.d(TAG, "onPreferenceChange: username " +
                        (isValid ? "" : "NOT ") + "accepted");
                return isValid;
            }
            return true;
        });
    }
}
