package com.example.policeapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class dataActivity extends AppCompatActivity {
    private static final String TAG="dataActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView=(ListView)findViewById(R.id.listView);
        mDatabaseHelper= new DatabaseHelper(this);

        populateListView();

    }
    private void populateListView(){
        Log.d(TAG,"populateListView Display data in the ListView");

// get the data and append to a list
        Cursor data=mDatabaseHelper.getData();
        ArrayList<String> listdata= new ArrayList<>();
        while(data.moveToNext()){
            // get the value from the database
            //and add it to Arraylist
            listdata.add(data.getString(1));

        }
        // create the list adapter and set the adapter
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listdata);
        mListView.setAdapter(adapter);
        //set an onitemclicklistener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
                Log.d(TAG,"onItemclick: You Click on" + item);
                Cursor data =mDatabaseHelper.getItemID(item);
                int itemID=-1;
                while(data.moveToNext()){
                    itemID=data.getInt(0);
                }
                if(itemID>-1){
                    Log.d(TAG,"onItemClick: The ID is:" + itemID );
                    Intent editScreenIntent= new Intent(dataActivity.this,EditdataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("address",item);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID with that item");
                }
            }
        });
    }
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
