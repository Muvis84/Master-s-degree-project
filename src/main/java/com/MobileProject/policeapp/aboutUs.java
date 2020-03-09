package com.example.policeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class aboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        VideoView videoView=findViewById(R.id.videoView2);
        String videoPath="android.resource://"+getPackageName()+"/"+R.raw.police;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        TextView textview18 = (TextView)findViewById(R.id.textView18);
        textview18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_textView("https://www.gmp.police.uk/");


            }
        });

        TextView textview19 = (TextView)findViewById(R.id.textView19);
        textview18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_textView("https://www.gmp.police.uk/");


            }
        });

        TextView textview17= (TextView)findViewById(R.id.textView17);
        textview18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_textView("https://www.gmp.police.uk/");


            }
        });
        TextView textview20 = (TextView)findViewById(R.id.textView20);
        textview18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_textView("https://www.gmp.police.uk/");


            }
        });
        TextView textview21= (TextView)findViewById(R.id.textView21);
        textview18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_textView("https://www.gmp.police.uk/");


            }
        });


        TextView textview22 = (TextView)findViewById(R.id.textView22);
        textview22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_textView("https://www.gmp.police.uk/");


            }
        });

        TextView textview24 = (TextView)findViewById(R.id.textView24);
        textview24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(aboutUs.this,Login.class);
                startActivity(intent);

            }
        });

    }
    public void clicked_textView(String url){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
