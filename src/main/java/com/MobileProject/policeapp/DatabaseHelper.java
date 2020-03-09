package com.example.policeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "User_Table";
    private static final String Col1 = "ID";
    private static final String Col2 = "Address";
    private static final String Col3 = "checkbox";
    private static final String Col4 = "description";
    private static final String Col5="uploadvideo";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }
//creating table in the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +Col2 +"TEXT" +
                Col3 +"TEXT" +Col4 +"TEXT)";
        db.execSQL(createTable);
    }
// upgrade table
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP Table exists" + TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String item1){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,item1);
        contentValues.put(Col3,item1);
        contentValues.put(Col4,item1);
        Log.d(TAG,"addData:Addin"+item1 +"to" +TABLE_NAME);
        long result=db.insert(TABLE_NAME,null,contentValues);
        // if postcode inserted incorrectly return -1
        if(result==-1)
        {
            return false;

        }else{
            return true;
        }

    }
    /**
     * return all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data=db.rawQuery(query,null);
        return data;
    }
    /**
     *
     */
    public Cursor getItemID(String item){
        SQLiteDatabase db= this.getWritableDatabase();
        String query ="SELECT" +Col1+"FROM" +TABLE_NAME +
                "WHERE"+Col2+"="+item+"'";
        Cursor data=db.rawQuery(query,null);
        return data;

    }
    public void updateName(String newItem,int id,String OldName){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE"+TABLE_NAME+ "SET" + Col2 + ","+newItem+ "WHERE" +Col1+ "," + id +"" +
                "AND"+ Col2 +","+ OldName+ "'";
        Log.d(TAG,"updateName: query:"+query);
        Log.d(TAG,"updateName: setting item to:"+newItem);
        db.execSQL(query);


    }
    public void deleteItem(int id,String item){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM" + TABLE_NAME +"WHERE"
                +Col1+ ","+id +"'"+
                "AND"+Col2 + ""+ item + "";
        Log.d(TAG,"deleteItem: query:"+query);
        Log.d(TAG,"deleteItem: setting item to:"+item + "from database");
        db.execSQL(query);
    }
}