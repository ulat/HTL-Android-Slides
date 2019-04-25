package net.eaustria.Intent_Demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private static final int RQ_CALL_PHONE = 123456;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startActivity(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("msg", "Hello World on Detail Activity");
        intent.putExtra("myMsg", new SuperMessage("Extra Message"));
        startActivity(intent);
    }

    public void openMap(View view) {
        String pos="geo:48.2206636,16.3100208?z=12";
        Uri uri = Uri.parse(pos);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
    }

    public void openUnknownIntent(View view) {
        try {
            startActivity(new Intent("Unknown Action"));
        } catch (Exception exc) {
            Log.d("TAG", exc.getLocalizedMessage());
        }
    }

    public void openPhoneCall(View view) {
        String phone = "tel:(+43) 732 1234567";
        Uri uri = Uri.parse(phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    public void openPhoneCallWithDialing(View view) {
        String perm = Manifest.permission.CALL_PHONE;
        if (ActivityCompat.checkSelfPermission(this, perm) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{perm}, RQ_CALL_PHONE);
        } else {
            dial();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode!=RQ_CALL_PHONE) return;
        if (grantResults.length>0 && grantResults[0]!=PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Sorry, no permission!", Toast.LENGTH_LONG).show();
        } else {
            dial();
        }
    }

    private void dial() {
        String phone = "tel:(+43)732 123456";
        Uri uri = Uri.parse(phone);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        try {
            startActivity(intent);
        } catch (SecurityException e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
    }

    public void openDetailImplicit(View view) {
        Intent intent = new Intent("net.eaustria.intent.action.Intent_Demo.SHOW_DETAIL");
        intent.setData(Uri.parse("msg:Hello! Started implicitly"));
        intent.putExtra("msg", "Hello! Start implicitly");
        startActivity(intent);
    }

    class SuperMessage implements Serializable {
        private String msg;

        public SuperMessage(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
