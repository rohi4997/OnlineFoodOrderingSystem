package com.example.Food_App.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_NAME
                + " TEXT, " + Params.KEY_PRICE + " TEXT" + ")";
        Log.d("dbharry", "Query being run is : "+ create);
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addItems(CartItemss cartItemss){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, cartItemss.getName());
        values.put(Params.KEY_PRICE, cartItemss.getPrice());


        db.insert(Params.TABLE_NAME, null, values);
        Log.d("dbharry", "Successfully inserted");
        db.close();}

    public List<CartItemss> getAllCartItems(){
        List<CartItemss> cartItemssList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                CartItemss cartItemss = new CartItemss();
                cartItemss.setId(Integer.parseInt(cursor.getString(0)));
                cartItemss.setName(cursor.getString(1));
                cartItemss.setPrice(cursor.getString(2));
                cartItemssList.add(cartItemss);
            }while(cursor.moveToNext());
        }
        return cartItemssList;
    }

    public int updateItems(CartItemss cartItemss){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, cartItemss.getName());
        values.put(Params.KEY_PRICE, cartItemss.getPrice());

        //Lets update now
        return db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?",
                new String[]{String.valueOf(cartItemss.getId())});


    }

    public void deleteItemById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteItem(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ Params.TABLE_NAME);

        //db.delete(Params.TABLE_NAME, Params.KEY_ID +"=?", new String[]{String.valueOf(cartItemss.getId())});
        db.close();
    }
}
