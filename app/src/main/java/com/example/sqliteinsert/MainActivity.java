package com.example.sqliteinsert;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sqliteinsert.data.myContract.myEntry;

public class MainActivity extends AppCompatActivity {
    EditText Name, Pass, oldName, newName, deleteName;
    Button addUser;
    Button viewData;
    Button updateUser;
    Button deleteData;
    Context context;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Name = (EditText) findViewById(R.id.editName);
        Pass = (EditText) findViewById(R.id.editPass);

        oldName = (EditText) findViewById(R.id.editTextCurrentName);
        newName = (EditText) findViewById(R.id.editTextNewName);
        deleteName = (EditText) findViewById(R.id.editTextUserNameToDelete);

        addUser = (Button) findViewById(R.id.buttonAddUser);
        addUser.setOnClickListener(myAddUserListener);
        viewData = (Button) findViewById(R.id.buttonViewData);
        viewData.setOnClickListener(myViewDataListener);
        updateUser = (Button) findViewById(R.id.buttonUpdate);
        updateUser.setOnClickListener(myUpdateUserListener);
        deleteData = (Button) findViewById(R.id.buttonDelete);
        deleteData.setOnClickListener(myDeleteUserListener);
        mHandler = new Handler();
    }

    private View.OnClickListener myAddUserListener = new View.OnClickListener() {
        public void onClick(View v) {
            addUser(v);
        }
    };

    private View.OnClickListener myViewDataListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener myUpdateUserListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(v.getContext(), getText(R.string.running).toString(), Toast.LENGTH_LONG).show();
            String oldUsernameString = oldName.getText().toString().trim();
            String newUsernameString = newName.getText().toString().trim();

            if (!oldUsernameString.isEmpty() && !newUsernameString.isEmpty()) {
                int cursorRowCount = -1;

                // Define a projection that specifies which columns from the database
                String[] projection = {
                        myEntry.UID,
                        myEntry.NAME,
                        myEntry.PASSWORD
                };

                String selection = myEntry.NAME + " = ?";
                String[] selectionArgs = new String[] { oldUsernameString };

                // Perform a query on the provider using the ContentResolver.
                // Use the {@link BeetleEntry#CONTENT_URI_BEETLE} to access the beetle data.
                Cursor cursor = getContentResolver().query(
                        myEntry.CONTENT_URI_USER,
                        projection,
                        selection,
                        selectionArgs,
                        null);

                cursorRowCount = cursor.getCount();

                if (cursorRowCount != -1) {

                    // Find the columns of beetle attributes that we're interested in
                    int idColumnIndex = cursor.getColumnIndex(myEntry.UID);

                    if(cursor.moveToFirst()) {
                        // Read the pet attributes from the Cursor for the current beetle
                        int myUID = cursor.getInt(idColumnIndex);

                        Uri currentBeetleUri = ContentUris.withAppendedId(myEntry.CONTENT_URI_USER, myUID);

                        ContentValues values = new ContentValues();
                        values.put(myEntry.NAME, newUsernameString);

                        int rowsAffected = getContentResolver().update(currentBeetleUri, values, null, null);

                        // Show a toast message depending on whether or not the update was successful.
                        if (rowsAffected == 0) {
                            // If no rows were affected, then there was an error with the update.
                            Toast.makeText(v.getContext(), getString(R.string.editor_update_failed),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Otherwise, the update was successful and we can display a toast.
                            Toast.makeText(v.getContext(), getString(R.string.editor_update_successful),
                                    Toast.LENGTH_SHORT).show();
                            oldName.setText("");
                            newName.setText("");
                        }

                    }
                } else {
                    Message.message(v.getContext(), getString(R.string.updateError));
                }

            } else {
                Message.message(v.getContext(), getString(R.string.giveNewAndOldUsername));
            }
        }
    };

    private View.OnClickListener myDeleteUserListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(v.getContext(), getText(R.string.running).toString(), Toast.LENGTH_LONG).show();
            String deletingUsernameString = deleteName.getText().toString().trim();

            if (!deletingUsernameString.isEmpty()) {
                int cursorRowCount = -1;

                // Define a projection that specifies which columns from the database
                String[] projection = {
                        myEntry.UID,
                        myEntry.NAME,
                        myEntry.PASSWORD
                };

                String selection = myEntry.NAME + " = ?";
                String[] selectionArgs = new String[] { deletingUsernameString };

                // Perform a query on the provider using the ContentResolver.
                // Use the {@link BeetleEntry#CONTENT_URI_BEETLE} to access the beetle data.
                Cursor cursor = getContentResolver().query(
                        myEntry.CONTENT_URI_USER,
                        projection,
                        selection,
                        selectionArgs,
                        null);

                cursorRowCount = cursor.getCount();

                if (cursorRowCount != -1) {

                    // Find the columns of beetle attributes that we're interested in
                    int idColumnIndex = cursor.getColumnIndex(myEntry.UID);

                    if(cursor.moveToFirst()) {
                        // Read the pet attributes from the Cursor for the current beetle
                        int myUID = cursor.getInt(idColumnIndex);

                        Uri currentBeetleUri = ContentUris.withAppendedId(myEntry.CONTENT_URI_USER, myUID);

                        ContentValues values = new ContentValues();
                        values.put(myEntry.NAME, deletingUsernameString);

                        int rowsDeleted = getContentResolver().delete(currentBeetleUri, null, null);

                        // Show a toast message depending on whether or not the update was successful.
                        if (rowsDeleted == 0) {
                            // If no rows were affected, then there was an error with the update.
                            Toast.makeText(v.getContext(), getString(R.string.editor_delete_failed),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Otherwise, the update was successful and we can display a toast.
                            Toast.makeText(v.getContext(), getString(R.string.editor_delete_successful),
                                    Toast.LENGTH_SHORT).show();
                            deleteName.setText("");
                        }

                    }

                } else {
                    Message.message(v.getContext(), getString(R.string.updateError));
                }
            } else {
                Message.message(v.getContext(), getString(R.string.giveNewAndOldUsername));
            }
        }
    };

    public void addUser(View view)
    {
        Toast.makeText(this, getText(R.string.running).toString(), Toast.LENGTH_LONG).show();
        String usernameString = Name.getText().toString().trim();
        String passwordString = Pass.getText().toString().trim();

        // This is a NEW user, so insert a new user into the provider,
        // returning the content URI for the new user.
        Uri newUri = null;

        ContentValues values = new ContentValues();

        if (usernameString.length() > 0 && passwordString.length() > 0) {
            values.put(myEntry.NAME, usernameString);
            values.put(myEntry.PASSWORD, passwordString);

            newUri = getContentResolver().insert(myEntry.CONTENT_URI_USER, values);
        }

        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            // If the new content URI is null, then there was an error with insertion.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Message.message(context, getText(R.string.unsuccessful).toString());
                }
            }, 6000);
        } else {
            // Otherwise, the insertion was successful and we can display a toast.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Message.message(context, getText(R.string.successful).toString());
                }
            }, 6000);

            Name.setText("");
            Pass.setText("");
        }
    }
}
