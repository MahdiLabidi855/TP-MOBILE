package com.example.myapi_exp1.Models;

import com.google.gson.annotations.SerializedName;

public class Produit {
    private int id;
    private String title; // Change from 'nomProduit' to 'title' (matches DummyJSON)
    private double price;
    private String brand;

    public String getNomProduit() { // Keep your existing method
        return title;  // 'title' is the correct field name from DummyJSON
    }
}

