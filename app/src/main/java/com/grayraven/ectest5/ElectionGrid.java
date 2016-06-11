package com.grayraven.ectest5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;

public class ElectionGrid extends AppCompatActivity {
    private static final String TAG = "theGrid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TableLayout table = (TableLayout)findViewById(R.id.election_table);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked");
            }
        });






        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}


