package net.eaustria.contactsprovider;

import android.database.Cursor;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int CONTACT_CREATE = 0;
    private static final int CONTACT_EDIT = 1;

    //select the second one, Android view menu
    private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;


    private Cursor c;
    private ListView listview;
    private SimpleCursorAdapter cursorAdapter;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listview);
        fillData();
    }

    private void fillData() {
        // A "projection" defines the columns that will be returned for each row
        String[] mProjection =
                {
                        UserDictionary.Words._ID,    // Contract class constant for the _ID column name
                        UserDictionary.Words.WORD,   // Contract class constant for the word column name
                        UserDictionary.Words.LOCALE  // Contract class constant for the locale column name
                };

        // Defines a string to contain the selection clause
        String selectionClause = null;
        // Initializes an array to contain selection arguments
        String[] selectionArgs = {""};
        // Does a query against the table and returns a Cursor object
        String[] projection = {
                UserDictionary.Words._ID,    // Contract class constant for the _ID column name
                UserDictionary.Words.WORD,   // Contract class constant for the word column name
                UserDictionary.Words.LOCALE
        };
        String sortOrder = "asc";
        mCursor = getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,  // The content URI of the words table
                projection,                       // The columns to return for each row
                selectionClause,                  // Either null, or the word the user entered
                selectionArgs,                    // Either empty, or the string the user entered
                sortOrder);                       // The sort order for the returned rows

        // Defines a list of columns to retrieve from the Cursor and load into an output row
        String[] wordListColumns =
                {
                        UserDictionary.Words.WORD,   // Contract class constant containing the word column name
                        UserDictionary.Words.LOCALE  // Contract class constant containing the locale column name
                };

        // Defines a list of View IDs that will receive the Cursor columns for each row
                int[] wordListItems = { R.id.dictWord, R.id.locale};

        // Creates a new SimpleCursorAdapter
                cursorAdapter = new SimpleCursorAdapter(
                        getApplicationContext(),               // The application's Context object
                        R.layout.wordlistrow,                  // A layout in XML for one row in the ListView
                        mCursor,                               // The result from the query
                        wordListColumns,                      // A string array of column names in the cursor
                        wordListItems,                        // An integer array of view IDs in the row layout
                        0);                                    // Flags (usually none are needed)

        // Sets the adapter for the ListView
                listview.setAdapter(cursorAdapter);
    }
}
