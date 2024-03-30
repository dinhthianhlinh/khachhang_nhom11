package com.example.khachhang;

import com.example.khachhang.DTO.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static ShoppingCart instance;
    private List<SanPham> productList;

    private ShoppingCart() {
        productList = new ArrayList<>();
    }

    public static synchronized ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addProduct(SanPham product) {
        productList.add(product);
    }

    public List<SanPham> getProductList() {
        return productList;
    }

    public int getProductCount() {
        return productList.size();
    }
}
