package com.example.myapi_exp1.Models;

import java.util.List;

public class ProductResponse {
    private List<Produit> products; // Match JSON structure

    public List<Produit> getProducts() {
        return products;
    }
}
