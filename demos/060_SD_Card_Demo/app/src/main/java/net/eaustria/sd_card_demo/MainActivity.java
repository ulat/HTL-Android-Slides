package net.eaustria.sd_card_demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RQ_WRITE_STORAGE = 12345;
    private EditText txtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInput = findViewById(R.id.txt_input);
    }

    private void writeToFile(String filename) {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) return;
        File outFile = Environment.getExternalStorageDirectory();
        String path = outFile.getAbsolutePath();
        String fullPath = path + File.separator + filename;
        Log.d(TAG, "filename: " + fullPath);
        try {
            PrintWriter out = new PrintWriter(
                                    new OutputStreamWriter(
                                        new FileOutputStream(fullPath)));
            out.print(txtInput.getText().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==RQ_WRITE_STORAGE) {
            if (grantResults.length>0 && grantResults[0]!=PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "SD Card Permission denied");
                Toast.makeText(this,
                        "SD Car Permission wurde verweigert!",
                        Toast.LENGTH_LONG).show();
            } else {
                writeToFile("LogFile");
            }
        }
    }

    public void printInput(View view) {
        Log.d(TAG, "entered printInput");
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            !=PackageManager.PERMISSION_GRANTED) {
            // RQ_WRITE_STORAGE ist just any constant value to identify the request
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    RQ_WRITE_STORAGE);
        } else {
            writeToFile("LogFile");
        }
    }

}
