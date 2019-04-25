package net.eaustria.gps_demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RQ_ACCESS_FINE_LOCATION = 123;
    private boolean isGpsAllowed = false;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private TextView mLongitude;
    private TextView mLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLatitude = findViewById(R.id.txtLat);
        mLongitude = findViewById(R.id.txtLon);
        registerSystemService();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onPostResume();
        if (isGpsAllowed) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    3000,
                    0,
                    locationListener);
        }
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        if (isGpsAllowed) locationManager.removeUpdates(locationListener);
    }

    private void registerSystemService() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // from Api 23 and above you can call getSystemService this way:
        // locationManager = (LocationManager) getSystemService(LocationManager.class);
        checkPermissionGPS();
    }

    private void checkPermissionGPS() {
        Log.d(TAG, "checkPermissionGPS");
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (ActivityCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ permission },
                    RQ_ACCESS_FINE_LOCATION );
        } else {
            gpsGranted();
        }

    }

    private void gpsGranted() {
        Log.d(TAG, "gps permission granted!");
        isGpsAllowed = true;
        showAvailableProviders();
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "onLocationChanged");
                displayLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d(TAG, "onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d(TAG, "onProviderEnabled");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d(TAG, "onProviderDisabled");
            }
        };

    }

    private void showAvailableProviders() {
        Log.d(TAG, "showAvailableProviders");
        List<String> providers = locationManager.getAllProviders();
        StringBuilder message = new StringBuilder();
        for (String name : providers) {
            boolean isEnabled = locationManager.isProviderEnabled(name);
            message.append(name+" is"+(!isEnabled? " not":"")+" enabled \n\n");
        }
        makeToast(message.toString());
    }

    private void displayLocation(Location location) {
        Log.d(TAG, "displayLocation");
        double lat = location==null ? -1 : location.getLatitude();
        double lon = location==null ? -1 : location.getLongitude();
        Log.d(TAG, "latitude: " + lat);
        Log.d(TAG, "longitude: " + lon);
        mLongitude.setText(String.format("%.4f", lon));
        mLatitude.setText(String.format("%.4f", lat));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != RQ_ACCESS_FINE_LOCATION) return;
        if (grantResults.length > 0 &&
            grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            makeToast("Permission ACCESS_FINE_LOCATION denied!");
        } else {
            gpsGranted();
        }
    }

    private void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void btnClickUpdateCoordinates(View view) {
        Log.d(TAG, "btnClickUpdateCoordinates");
        if (isGpsAllowed) {
            Location location = locationManager.getLastKnownLocation(
                    LocationManager.GPS_PROVIDER);
            displayLocation(location);
        }
    }

    public void btnClickShowProviders(View view) {
        showAvailableProviders();
    }
}
