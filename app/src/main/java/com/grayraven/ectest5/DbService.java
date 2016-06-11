package com.grayraven.ectest5;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


/*
 * A service to access Summary text for a past presidential election
 */
public class DbService extends IntentService {

    private static final String TAG = "DbService";
    Uri uri = MyContentProvider.CONTENT_URI;

    public static final String ACTION_READ = "com.grayraven.ectest5.action.READ";
    public static final String ACTION_WRITE = "com.grayraven.ectest5.action.WRITE";

    public static final String EXTRA_YEAR = "com.grayraven.ectest5.extra.YEAR";
    public static final String EXTRA_TEXT = "com.grayraven.ectest5.extra.TEXT";

    public DbService() {
        super("DbService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_READ.equals(action)) {

                handleActionRead();
            } else if (ACTION_WRITE.equals(action)) {
                final String year = intent.getStringExtra(EXTRA_YEAR);
                final String text = intent.getStringExtra(EXTRA_TEXT);
                handleActionWrite(year, text);
            }
        }
    }

    /**
     * Handle action Read in the provided background thread
     */
    private void handleActionRead() {

        Cursor c =  getContentResolver().query(uri, null, null, null, null);

        if(c.moveToFirst()) {
            do {
                String year = c.getString(c.getColumnIndex(MyContentProvider.ELECTION_YEAR));
                String text = c.getString(c.getColumnIndex(MyContentProvider.ELECTION_TEXT));
                Log.d(TAG, "Read Year: " + year + " - " + text );
            } while (c.moveToNext());
        }
        c.close();
        // send data back using event
    }

    /**
     * Handle action Write in the provided background thread with the provided
     * parameters.
     */
    private void handleActionWrite(String year, String text) {
        ContentValues values = new ContentValues();
        values.put(MyContentProvider.ELECTION_YEAR, Integer.parseInt(year));
        values.put(MyContentProvider.ELECTION_TEXT, text);

        Uri uri = getContentResolver().insert(
                MyContentProvider.CONTENT_URI, values);
        Log.d(TAG, year + " saved at " + uri.toString());
    }
}
