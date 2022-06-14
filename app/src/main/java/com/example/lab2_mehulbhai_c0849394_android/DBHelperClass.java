package com.example.lab2_mehulbhai_c0849394_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "product_databse";
    private static final String TABLE_NAME = "product_table";
    private static final String ID ="id";
    private static final String product_id = "p_id";
    private static final String product_name = "p_name";
    private static final String product_price = "p_price";
    private static final String product_description = "p_desc";


    public DBHelperClass(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table_query="CREATE TABLE if not EXISTS "+TABLE_NAME+
                "("+
                ID+" INTEGER PRIMARY KEY,"+
                product_id +" TEXT ,"+
                product_name +" TEXT ,"+
                product_price + " TEXT ,"+
                product_description +" TEXT)";
        sqLiteDatabase.execSQL(table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    //insert new product data
    public void AddProduct(ModelClass modelClass){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(product_id, modelClass.getProduct_id());
        contentValues.put(product_name, modelClass.getProduct_name());
        contentValues.put(product_description, modelClass.getProduct_description());
        contentValues.put(product_price, modelClass.getProduct_price());
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
    }

    public void DeleteProductItem(String pId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,ID+"=?",new String[]{pId});
        sqLiteDatabase.close();
    }

}
