package com.example.abhacharya.eventjockey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;


public class FirstIndex extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    public static boolean placeskip = false;
    public static boolean ratingskip = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_index);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(this.getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if(position==1){
                    Intent intent = new Intent(FirstIndex.this, FirstEventActivity.class);
                    startActivity(intent);
                }

                else if(position==0){
                    Intent intent = new Intent(FirstIndex.this,RealEvent.class);
                    startActivity(intent);
                }
                else if(position==2){
                    Intent intent = new Intent(FirstIndex.this,RealEvent.class);
                    startActivity(intent);
                }
                else if(position==3){
                    //TODO
                }
                //Log.i(LOG_TAG, " Clicked on Item " + position);

            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
       /*Create the required card objects
        }*/
        DataObject obj = new DataObject("Find events near you!",
                "Find the most happening events");
        results.add(0, obj);
        obj = new DataObject("Find the best restaurants!",
                "Find restaurants according to your needs." );
        results.add(1, obj);
        obj = new DataObject("Your Squad",
                "Find your friends and co-ordinate your scene");
        results.add(2, obj);
        obj = new DataObject("Randomness",
                "Can't decide? Let us decide for you!" );
        results.add(3, obj);

        return results;
    }
}


