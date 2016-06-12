package com.grayraven.ectest5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class ElectionGrid extends AppCompatActivity {
    private static final String TAG = "theGrid";
    TableLayout mTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTable  = (TableLayout)findViewById(R.id.election_table);
        final View row=mTable.getChildAt(1);
        row.setClickable(true);
        row.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                // TODO Auto-generated method stub
                Log.d(TAG, "row " + mTable.indexOfChild(row) + " tag: " + (String) row.getTag());
            }
        });





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void cellClick(View v) {
//Get the id of the clicked object and assign it to a Textview variable
        TextView cell = (TextView) findViewById(v.getId());
        String tag = (String) cell.getTag();
        String name = getResources().getResourceEntryName(cell.getId());
        Log.d(TAG, "Clicked: " + tag + " - " + name); //use name

        if(tag.equals("D")){
            cell.setBackgroundResource(R.color.dem_blue);
            Log.d(TAG, "report that state " + name + " voted D");
        } else {
            cell.setBackgroundResource(R.color.rep_red);
            Log.d(TAG, "report that state " + name + " voted R");
        }

    }

}


