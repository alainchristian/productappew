package com.example.productapp;

import android.graphics.Bitmap;

public class Product {
    String prodName;
    float prodPrice;
    String prodDescr;
    Bitmap bitmapImage;

    public Product(String prodName, float prodPrice, String prodDescr, Bitmap bitmapImage) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodDescr = prodDescr;
        this.bitmapImage = bitmapImage;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public float getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(float prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdDescr() {
        return prodDescr;
    }

    public void setProdDescr(String prodDescr) {
        this.prodDescr = prodDescr;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }
}
