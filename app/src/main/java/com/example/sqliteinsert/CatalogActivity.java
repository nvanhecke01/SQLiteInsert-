package com.example.sqliteinsert;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.sqliteinsert.data.myContract.myEntry;


public class CatalogActivity extends AppCompatActivity {

    boolean passShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Find the ListView which will be populated with the beetle data
        ListView userListView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        userListView.setEmptyView(emptyView);
    }

    /**
     * This method is called when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the beetles database.
     */
    private void displayDatabaseInfo() {
        // Define a projection that specifies which columns from the database
        String[] projection = {
                myEntry.UID,
                myEntry.NAME,
                myEntry.PASSWORD
        };

        // Perform a query on the provider using the ContentResolver.
        // Use the {@link BeetleEntry#CONTENT_URI_BEETLE} to access the beetle data.
        Cursor cursor = getContentResolver().query(
                myEntry.CONTENT_URI_USER,
                projection,
                null,
                null,
                null);

        // Find the ListView which will be populated with the beetle data
        ListView beetleListView = (ListView) findViewById(R.id.list);

        // Setup an Adapter to create a list item for each row of beetle data in the Cursor.
        myCursorAdapter adapter = new myCursorAdapter(this, cursor);

        // Attach the adapter to the ListView.
        beetleListView.setAdapter(adapter);

    }
}
