package ai.we_make.myapplication;


import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configActionBar();
        // register context menu to view component
        TextView textView = findViewById(R.id.text_view);
        registerForContextMenu(textView);
        listView = findViewById(R.id.listview);
        registerForContextMenu(listView);
        createListAdapter(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void createListAdapter(ListView listView) {
        items.add("Hias");
        items.add("Mizi");
        items.add("Rüdiger");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
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
            case R.id.menu_newItem:
                Toast.makeText(this, "Super neuer Eintrag", Toast.LENGTH_LONG).show();
                break;
            case android.R.id.home:
                Toast.makeText(this, "Back on AB clicked!", Toast.LENGTH_LONG).show();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int viewId = v.getId();
        if (viewId == R.id.text_view || viewId == R.id.listview) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.context_show) {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            String name = "";
            if (info != null) {
                long id = info.id;
                int pos = info.position;
                name = info != null ? listView.getAdapter().getItem(pos).toString() : "";
            }
            Toast.makeText(this, "Showing details! "+name ,
                    Toast.LENGTH_LONG).show();
            return true;
        }
        if (item.getItemId() == R.id.context_delete) {
            Toast.makeText(this, "Deleting item", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void configActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("My modified ActionBar");
        actionBar.setTitle("MyActionBar");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Drawable background = ContextCompat.getDrawable(this,
                android.R.drawable.status_bar_item_app_background);

    }

    public void showPopupMenu(View view) {
        PopupMenu menu = new PopupMenu(this, view);
        menu.inflate(R.menu.popup_menu);
        menu.setOnMenuItemClickListener( (item) -> {
            Toast.makeText(this, "Popup Menu id: " + item.getItemId(),
                            Toast.LENGTH_LONG).show();
            return true;
        });
        menu.show();
    }
}
