package com.example.policeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditdataActivity extends AppCompatActivity {
    private static final String TAG="EditdataActivity";
    private Button button11,button12;
    private EditText editText2;

    DatabaseHelper mDatabaseHelper;
    private String selectedName;
    private int selectedId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        editText2 = (EditText) findViewById(R.id.editText2);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra
        Intent receivedIntent = getIntent();

        // get the itemID
        selectedId = receivedIntent.getIntExtra("id", -1);

        //get the item
        selectedName = receivedIntent.getStringExtra("item");
        // get the text
        editText2.setText(selectedName);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editText2.getText().toString();
                if (!item.equals("")) {
                    mDatabaseHelper.updateName(item, selectedId, selectedName);
                } else {
                    toastMessage("you must enter detail");

                }
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mDatabaseHelper.deleteItem(selectedId,selectedName);
               editText2.setText("");
               toastMessage("item removed");
            }
        });

    }


    /**
     *
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
