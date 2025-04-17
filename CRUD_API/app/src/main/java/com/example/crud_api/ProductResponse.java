package com.example.crud_api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProductResponse {

    @SerializedName("products") // Mapping the "products" key in the JSON response
    private List<Produit> products;

    public List<Produit> getProducts() {
        return products;
    }

    public void setProducts(List<Produit> products) {
        this.products = products;
    }
}
