package com.example.policeapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
@Override

    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
//create a java object of button instance
Button button = (Button)findViewById(R.id.button);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,Map.class);
        startActivity(intent);

    }
});
Button button2 = (Button) findViewById(R.id.button2);
button2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        // action to the button click
        Intent startIntent=new Intent(getApplicationContext(), Menu.class);
        //
        startActivity(startIntent);

    }
});
// setting second button
Button button3 = (Button)findViewById(R.id.button3);
button3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent startIntent=new Intent(getApplicationContext(),Menu.class);
        //
        startActivity(startIntent);


    }
});
    Button button4 = (Button)findViewById(R.id.button4);
    button4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    Button button5 = (Button)findViewById(R.id.button5);
    button5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    TextView textview = (TextView)findViewById(R.id.textView);
    textview.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clicked_textView("https://www.gmp.police.uk/");


        }
    });
    TextView textview2 = (TextView)findViewById(R.id.textView2);
    textview2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    TextView textview3 = (TextView)findViewById(R.id.textView3);
    textview3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent startIntent=new Intent(getApplicationContext(),aboutUs.class);
            //
            startActivity(startIntent);

        }
    });
}
// access the website by reference
public void clicked_textView(String url){
    Intent intent=new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(url));
    startActivity(intent);
}
        };



