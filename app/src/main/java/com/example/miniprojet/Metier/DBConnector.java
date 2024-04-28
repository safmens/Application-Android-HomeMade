package com.example.miniprojet.Metier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;


public class DBConnector extends SQLiteOpenHelper {

    public  DBConnector(Context context){super(context,"DB",null,1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table clientInfo(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "UserName VARCHAR UNIQUE," +
                "Password,Phone VARCHAR," +
                "Adress VARCHAR," +
                "service VARCHAR," +
                "Image BLOB," +
                "Description VARCHAR(255))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS clientInfo");
        onCreate(sqLiteDatabase);
    }

    public Boolean checkUserName(String username){
        SQLiteDatabase mydatabase =  this.getWritableDatabase();
        Cursor cursor=mydatabase.rawQuery("select * from clientInfo where UserName = ?",new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean updatePasswd(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Password", password);
        long result =  db.update("clientInfo", values,"UserName= ?" ,new String[]{username});
        if(result == -1) return false;
        else return true;
    }



    public void update(String username, String phone, String adress,  String service,String description) {

        String where="UserName=?";
        String[] whereArgs = new String[] {username};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Phone", phone);
        values.put("Adress", adress);
        values.put("service", service);
        values.put("Description", description);
        db.update("clientInfo", values,where,whereArgs);
        db.close();
    }


    public ArrayList<Client> readAllSUGARData() {
        SQLiteDatabase myDB=this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from clientInfo where service = 'sucrés' ",null);
        ArrayList<Client> ClientArrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            byte[] imagebytes = cursor.getBlob(6);
            Bitmap imageBi = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
            ClientArrayList.add(new Client(
                    cursor.getString(1),
                    cursor.getString(3),
                    cursor.getString(4),
                    imageBi,
                    cursor.getString(7)
            ));
        }
        cursor.close();
        return ClientArrayList;
    }
    public ArrayList<Client> readAllSALTData(){
        SQLiteDatabase myDB=this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from clientInfo where service = 'salés' ",null);
        ArrayList<Client> ClientArrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            byte[] imagebytes = cursor.getBlob(6);
            Bitmap imageBi = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
            ClientArrayList.add(new Client(
                    cursor.getString(1),
                    cursor.getString(3),
                    cursor.getString(4),
                    imageBi,
                    cursor.getString(7)
            ));
        }
        cursor.close();
        return ClientArrayList;
    }

    public ArrayList<Client> readAllData() {
        SQLiteDatabase myDB=this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from clientInfo where service = 'pates' ",null);
        ArrayList<Client> ClientArrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            byte[] imagebytes = cursor.getBlob(6);
            Bitmap imageBi = BitmapFactory.decodeByteArray(imagebytes,0,imagebytes.length);
            ClientArrayList.add(new Client(
                    cursor.getString(1),
                    cursor.getString(3),
                    cursor.getString(4),
                    imageBi,
                    cursor.getString(7)
            ));
        }
        cursor.close();
        return ClientArrayList;
    }
}