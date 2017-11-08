package com.example.sqliteinsert;

/**
 * Created by niels on 6-10-2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.sqliteinsert.data.myContract.myEntry;

import org.w3c.dom.Text;

/**
 * {@link myCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class myCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link myCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public myCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView usernameTextView = (TextView) view.findViewById(R.id.username);
        TextView passwordTextView = (TextView) view.findViewById(R.id.password);

        // Find the columns of beetle attributes that we're interested in
        int usernameColumnIndex = cursor.getColumnIndex(myEntry.NAME);
        int passwordColumnIndex = cursor.getColumnIndex(myEntry.PASSWORD);

        // Read the pet attributes from the Cursor for the current beetle
        String myUsername = cursor.getString(usernameColumnIndex);
        String myPassword = cursor.getString(passwordColumnIndex);

        // Populate fields with extracted properties
        usernameTextView.setText(myUsername);
        passwordTextView.setText(myPassword);
    }
}