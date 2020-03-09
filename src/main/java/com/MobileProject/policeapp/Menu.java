package com.example.policeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
          textView=(TextView)findViewById(R.id.textView4);
          textView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent =new Intent(Menu.this,reportpage.class);
                  startActivity(intent);

              }
          });


        textView=(TextView)findViewById(R.id.textView6);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Menu.this,reportpage.class);
                startActivity(intent);

            }
        });
        textView=(TextView)findViewById(R.id.textView8);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Menu.this,reportpage.class);
                startActivity(intent);

            }
        });
        textView=(TextView)findViewById(R.id.textView9);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Menu.this,reportpage.class);
                startActivity(intent);

            }
        });
        textView=(TextView)findViewById(R.id.textView10);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Menu.this,reportpage.class);
                startActivity(intent);

            }
        });
        textView=(TextView)findViewById(R.id.textView11);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Menu.this,reportpage.class);
                startActivity(intent);

            }
        });
        textView=(TextView)findViewById(R.id.textView13);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Menu.this,reportpage.class);
                startActivity(intent);

            }
        });
        textView=(TextView)findViewById(R.id.textView14);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Menu.this,reportpage.class);
                startActivity(intent);

            }
        });
    }
};
