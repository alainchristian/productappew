package com.example.productapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView prodImage;
    public static final int MYREQ_COD=1001;
    Uri imagePath;
    Bitmap btmpImage;
    Button btnSend,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuProdAdd:
                showProductForm();
                break;
            case R.id.mnuProdView:
                showProducts();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProducts() {
        startActivity(new Intent(MainActivity.this,ShowProducts.class));

    }
    AlertDialog.Builder builder;
    Dialog dialog;
    private void showProductForm() {
        dialog=new Dialog(this);
        //builder=new AlertDialog.Builder(this);
        //builder.setTitle("Product recording");
        //builder.setMessage("Record all products as reuired");
        //builder.setIcon(R.drawable.ic_record);
        //View view = LayoutInflater.from(this).inflate(R.layout.productform,null);
        //builder.setView(view);
        dialog.setContentView(R.layout.productform);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        prodImage=dialog.findViewById(R.id.prdImage);
        btnSend=dialog.findViewById(R.id.btnSend);
        btnCancel=dialog.findViewById(R.id.btnCancel);
        prodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
       //builder.create(dialog);
        dialog.show();
    }
    public void hideDialog(View view){
        dialog.dismiss();
    }
    private void openGallery() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,MYREQ_COD);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==MYREQ_COD && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            try {
                imagePath =data.getData();
                btmpImage = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                prodImage.setImageBitmap(btmpImage);

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}