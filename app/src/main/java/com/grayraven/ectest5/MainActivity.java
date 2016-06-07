package com.grayraven.ectest5;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    Uri uri = MyContentProvider.CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnDoIt =(Button)findViewById(R.id.btn_doit);
        btnDoIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveElection(1996, "1996 stuff");
                SaveElection(2000, "2000 stuff");
            }
        });

        Button btnRead = (Button) findViewById(R.id.btn_read);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor c =  getContentResolver().query(uri, null, null, null, null);

                if(c.moveToFirst()) {
                    do {
                        String year = c.getString(c.getColumnIndex(MyContentProvider.ELECTION_YEAR));
                        String text = c.getString(c.getColumnIndex(MyContentProvider.ELECTION_TEXT));
                        Log.d(TAG, "Year: " + year + " - " + text );
                    } while (c.moveToNext());
                }

                c.close();

            }
        });

        Button btnClear = (Button)findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentResolver().delete(uri,null,null);
            }
        });


       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void SaveElection(int year, String text) {

        ContentValues values = new ContentValues();
        values.put(MyContentProvider.ELECTION_YEAR, year);
        values.put(MyContentProvider.ELECTION_TEXT, text);

        Uri uri = getContentResolver().insert(
                MyContentProvider.CONTENT_URI, values);
        Log.d(TAG, year + " saved at " + uri.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
