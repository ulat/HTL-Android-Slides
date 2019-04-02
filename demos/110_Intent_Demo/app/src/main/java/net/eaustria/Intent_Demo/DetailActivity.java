package net.eaustria.Intent_Demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.net.URI;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String msg1 = bundle.getString("msg");
        MainActivity.SuperMessage superMessage =
                (MainActivity.SuperMessage)bundle.getSerializable("myMsg");

    }

    public void closeActivity(View view) {
        finish();
    }


}
