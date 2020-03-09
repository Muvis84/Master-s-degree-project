package com.example.policeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

public class reportpage extends AppCompatActivity {
    private static final String TAG ="reportpage";
    private static final int ERROR_DIALOG_REQUEST=9001;

    DatabaseHelper mdatabaseHelper;
    private Button button9,btnsubmit;
    private EditText editText,editText3;
    private CheckBox checkBox,checkBox2;

    long queueid;
    DownloadManager dm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportpage);
        editText=(EditText) findViewById(R.id.editText);
        editText3=findViewById(R.id.editText3);
        button9=(Button)findViewById(R.id.button9);
        btnsubmit=(Button)findViewById(R.id.button8);
        checkBox=findViewById(R.id.checkBox);
        checkBox2=findViewById(R.id.checkBox2);
        mdatabaseHelper=new DatabaseHelper(this);
        TextView textView=findViewById(R.id.textView12);
        registerForContextMenu(textView);
        if(isServicesOK()){
            init();
        }

        BroadcastReceiver receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action=intent.getAction();
                if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action))
                {
                    DownloadManager.Query req_query=new DownloadManager.Query();
                    req_query.setFilterById(queueid);

                    Cursor c =dm.query(req_query);
                    if(c.moveToFirst())
                    {
                        int columnIndex=c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if(DownloadManager.STATUS_SUCCESSFUL==c.getInt(columnIndex))
                        {
                            VideoView videoView=(VideoView)findViewById(R.id.videoView);
                            String UriString=c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                            MediaController mediaControler=new MediaController(getApplicationContext());
                            mediaControler.setAnchorView(videoView);
                            videoView.requestFocus();
                            videoView.setVideoURI(Uri.parse(UriString));
                            videoView.start();
                        }
                    }
                }

            }
        };
        // register receiver the object
        registerReceiver(receiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Button button9=(Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntry=editText.getText().toString();
                if(editText.length() !=0){
                    AddData(newEntry);
                    editText.setText("");

                }else{
                    toastMessage("something is wrong");
                }

              String name=editText3.getText().toString();
                Intent startIntent=new Intent(getApplicationContext(), reportpage.class);
                //
                startActivity(startIntent);

            }
        });

        textView=(TextView)findViewById(R.id.textView12);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                startActivity(intent);

            }
        });
        checkBox.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String newEntry=checkBox.getText().toString();
                if (checkBox.isChecked()){
                    checkBox.setTextColor(getResources().getColor(R.color.colorTeal));
                }else{
                    checkBox.setTextColor(getResources().getColor(R.color.colorblack));

                }
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String newEntry=checkBox2.getText().toString();
                if (checkBox2.isChecked()){
                    checkBox2.setTextColor(getResources().getColor(R.color.colorTeal));
                }else{
                    checkBox2.setTextColor(getResources().getColor(R.color.colorblack));

                }
            }
        });


        btnsubmit.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View view) {
        String s="";
        if(checkBox.isChecked()){
            s +="\n Yes";

        }
        if(checkBox2.isChecked()){
            s +="\n No";
        }
        Intent intent =new Intent(reportpage.this,dataActivity.class);
        startActivity(intent);
    }
});
 EditText editText3=(EditText) findViewById(R.id.editText3);
 
 
    }
    public void AddData(String newEntry){
        boolean insertData=mdatabaseHelper.addData(newEntry);
        if(insertData){
            toastMessage("Data succefully inserted");
        }else{
            toastMessage("unable to insert data");
        }
    }
    public void Download_Click(View v)
    {
        dm=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(""));
        queueid=dm.enqueue(request);
    }
    public void view_click(View v)
    {
        Intent i =new Intent();
        i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(i);

    }

    private void init(){
        ImageButton imageButton2=(ImageButton)findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(reportpage.this,Map.class);
                startActivity(intent);
            }
        });


    }
    //creating the boolean method to check the map version
    public boolean isServicesOK(){
        Log.d(TAG,"isServicesOK:checking google services version");
        int available=GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(reportpage.this);
        if(available== ConnectionResult.SUCCESS){
            //the google is fine to use
            Log.d(TAG,"isServicesOK:Google play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG,"isServicesOK: an error occured");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(reportpage.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this,"we can't find you on the map, please try again", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.option,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_yes:
               Toast.makeText(this,"yes selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Menu_no:
                Toast.makeText(this,"yes selected",Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onContextItemSelected(item);

            }
        }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }
// creating edittext validation
    private boolean validateAddress(){
        String addressinput= editText3.getText().toString().trim();
        if(addressinput.isEmpty()){
            editText3.setError(" please fill the address field");
            return false;
        }else if(editText3.length()>20) {
            editText3.setError("Address to long, please try again");
            return false;


        }else{
            editText3.setError(null);
            return true;
        }
    }

    }

