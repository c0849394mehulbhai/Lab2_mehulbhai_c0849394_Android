package com.example.lab2_mehulbhai_c0849394_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "product_databse";
    private static final String TABLE_NAME = "product_table";
    private static final String ID = "id";
    private static final String product_id = "p_id";
    private static final String product_name = "p_name";
    private static final String product_price = "p_price";
    private static final String product_description = "p_desc";


    public DBHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table_query = "CREATE TABLE if not EXISTS " + TABLE_NAME +
                "(" +
                ID + " INTEGER PRIMARY KEY," +
                product_id + " TEXT ," +
                product_name + " TEXT ," +
                product_price + " TEXT ," +
                product_description + " TEXT)";
        sqLiteDatabase.execSQL(table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    //insert new product data
    public void AddProduct(ModelClass modelClass) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(product_id, modelClass.getProduct_id());
        cv.put(product_name, modelClass.getProduct_name());
        cv.put(product_description, modelClass.getProduct_description());
        cv.put(product_price, modelClass.getProduct_price());
        sqLiteDatabase.insert(TABLE_NAME, null, cv);
        sqLiteDatabase.close();
    }

    public void DeleteProductItem(String pId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + "=?", new String[]{pId});
        sqLiteDatabase.close();
    }

    public ModelClass getProductRecord(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{ID, product_id, product_name, product_description, product_price}, ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ModelClass modelClass = new ModelClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        sqLiteDatabase.close();
        return modelClass;
    }

    public int updateProductRecord(ModelClass modelClass) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(product_id, modelClass.getProduct_id());
        cv.put(product_name, modelClass.getProduct_name());
        cv.put(product_description, modelClass.getProduct_description());
        cv.put(product_price, modelClass.getProduct_price());

        return db.update(TABLE_NAME, cv, ID + "=?", new String[]{String.valueOf(modelClass.getId())});
    }
}
