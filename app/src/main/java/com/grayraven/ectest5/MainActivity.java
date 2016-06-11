package com.grayraven.ectest5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
                SaveElection("1996", "1996 stuff");
                SaveElection("2000", "2000 stuff");
            }
        });

        Button btnRead = (Button) findViewById(R.id.btn_read);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadElection();
            }
        });

        Button btnClear = (Button)findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentResolver().delete(uri,null,null);
            }
        });

        Button btnShowGrid = (Button)findViewById(R.id.btn_show_grid);
        btnShowGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ElectionGrid.class);
                startActivity(intent);

            }
        });

        Button btnShowTable = (Button)findViewById(R.id.btn_style_table);
        btnShowTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StyleTable.class);
                startActivity(intent);
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

    private void SaveElection(String year, String text) {

        Intent intent = new Intent(this, DbService.class);
        intent.setAction(DbService.ACTION_WRITE);
        intent.putExtra(DbService.EXTRA_YEAR, year);
        intent.putExtra(DbService.EXTRA_TEXT, text);
        startService(intent);
    }

    private void ReadElection() {
        Intent intent = new Intent(this, DbService.class);
        intent.setAction(DbService.ACTION_READ);
        startService(intent);
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


//http://stackoverflow.com/questions/2108456/how-can-i-create-a-table-with-borders-in-android
//https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=android%20table%20layout%20detect%20click%20on%20cell
//http://stackoverflow.com/questions/12734793/android-get-position-of-clicked-item-in-gridview
//http://techlovejump.com/android-multicolumn-listview/

//http://www.androidhive.info/2016/01/android-working-with-recycler-view/

//https://github.com/InQBarna/TableFixHeaders
