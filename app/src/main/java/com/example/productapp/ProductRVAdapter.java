package com.example.productapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        holder.tvDesc.setText(product.getProdDescr());
        holder.tvPrice.setText((int) product.getProdPrice());
        holder.prodImage.setImageBitmap(product.getBitmapImage());

    }

    @Override
    public int getItemCount() {
        return lProducts.size();
    }
}
