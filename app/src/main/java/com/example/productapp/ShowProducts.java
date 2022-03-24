package com.example.productapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ShowProducts extends AppCompatActivity {
    RecyclerView productRV;
    ProductRVAdapter adapter;
    ProductDBHelper dbHelper;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        productRV=findViewById(R.id.productRV);

        productList=new ArrayList<>();
        dbHelper=new ProductDBHelper(this);
        productList=dbHelper.getData();
        adapter=new ProductRVAdapter(productList,this);
        productRV.setLayoutManager(new GridLayoutManager(this,3));
        productRV.setAdapter(adapter);

    }
}