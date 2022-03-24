package com.example.productapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductRVAdapter extends RecyclerView.Adapter<VH> {
    List<Product> lProducts;
    Context context;

    public ProductRVAdapter(List<Product> lProducts, Context context) {
        this.lProducts = lProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.productrow,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Product product=lProducts.get(position);
        holder.tvName.setText(product.getProdName());
        holder.tvPrice.setText(""+(int) product.getProdPrice());
        holder.prodImage.setImageBitmap(product.getBitmapImage());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+product.getProdName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "The whole item is "+product.getProdName()+"  \n"+product.getProdPrice(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return lProducts.size();
    }
}
