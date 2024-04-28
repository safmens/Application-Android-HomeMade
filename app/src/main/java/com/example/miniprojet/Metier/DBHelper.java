package com.example.miniprojet.Metier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelper {

    private Context con;
    private SQLiteDatabase db;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    public DBHelper(Context con){ this.con = con ;}

    public DBHelper OpenDB() {
        DBConnector dbCon = new DBConnector(con);
        db = dbCon.getWritableDatabase();
        return this ;
    }


    public boolean RegistrationClient(Client client){
        try {
            Bitmap profileImage=client.getImage();
            byteArrayOutputStream= new ByteArrayOutputStream();
            profileImage.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            imageInBytes=byteArrayOutputStream.toByteArray();

            ContentValues cv = new ContentValues();
            cv.put("UserName",client.getUserName());
            cv.put("Password",client.getPassword());
            cv.put("Phone",client.getPhone());
            cv.put("Adress",client.getAdress());
            cv.put("Service",client.getService());
            cv.put("Image",imageInBytes);
            cv.put("Description",client.getDescription());


            db.insert("clientInfo",null,cv);
            return true;
        }catch (Exception e){
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }


    public ArrayList<Client> LoginClient(String UserName, String Password) {
        ArrayList<Client> userList = new ArrayList<Client>();
        try {
            Cursor cursor = db.rawQuery("select * from clientInfo where UserName='" + UserName + "' and Password='" + Password + "'", null);
            if (cursor.moveToFirst()) {
                do {
                    Client client = new Client();
                    client.setUserName(cursor.getString(1));
                    client.setPassword(cursor.getString(2));
                    client.setPhone(cursor.getString(3));
                    client.setAdress(cursor.getString(4));
                    client.setService(cursor.getString(5));
                    client.setDescription(cursor.getString(7));

                    userList.add(client);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Toast.makeText(con, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return userList;
    }



}
