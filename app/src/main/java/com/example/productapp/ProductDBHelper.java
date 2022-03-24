package com.example.productapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductDBHelper extends SQLiteOpenHelper {
    byte[] byteImage;
    ByteArrayOutputStream outputStream;
    Context context;
    public ProductDBHelper(@Nullable Context context) {
        super(context, "product.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table prodTable(prdId Integer primary key autoincrement, prdName TEXT, prdPrice FLOAT, prdDesc TEXT, prdImage BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists prodTable");

    }
    public void insertProduct(Product product){
        try {
            SQLiteDatabase myDB = getWritableDatabase();
            Bitmap bitmap=product.getBitmapImage();
            //change the bitmap to byte[]
            outputStream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            byteImage=outputStream.toByteArray();

            ContentValues values = new ContentValues();
            values.put("prdName",product.getProdName());
            values.put("prdPrice",product.getProdPrice());
            values.put("prdDesc",product.getProdDescr());
            values.put("prdImage",byteImage);
            long result=myDB.insert("ProdTable",null,values);
            if (result!=-1){
                Toast.makeText(context, "Data entry successful", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Data entry failed", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

       // myDB.
    }
    public List<Product> getData(){
        try {
            SQLiteDatabase db = getReadableDatabase();
            List<Product> productList =new ArrayList<>();
            Cursor cursor =db.rawQuery("select * from prodTable",null);
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    String name=cursor.getString(1);
                    Float price =cursor.getFloat(2);
                    String desc =cursor.getString(3);
                    byte[] imageByte=cursor.getBlob(4);
                    Bitmap bitmap= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                    productList.add(new Product(name,price,desc,bitmap));
                }
                return productList;
            }
            Toast.makeText(context, "Database is empty", Toast.LENGTH_SHORT).show();
            return null;


        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }

    }
}
