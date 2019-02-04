package ai.we_make.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        configActionBar();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, "onOptionsItemSelected: " + id );
        switch (id) {
            case R.id.menu_help:
                Toast.makeText(this, "Help Clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_preferences:
                Toast.makeText(this, "Preferences Clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_quit:
                Toast.makeText(this, "Help Clicked", Toast.LENGTH_LONG).show();
                break;
            case android.R.id.home:
                Toast.makeText(this, "Back on AB clicked!", Toast.LENGTH_LONG).show();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    private void configActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("My modified ActionBar");
        actionBar.setTitle("MyActionBar");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Drawable background = ContextCompat.getDrawable(this,
                android.R.drawable.status_bar_item_app_background);

    }
}
