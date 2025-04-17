package com.example.crud_api;

import com.google.gson.annotations.SerializedName;

public class Produit {

    @SerializedName("id") // Mapping the "id" field from the JSON response
    private int idProduit;

    @SerializedName("title") // Mapping the "title" field from the JSON response
    private String nomProduit;

    @SerializedName("price") // Mapping the "price" field from the JSON response
    private double prixProduit;

    // Default constructor
    public Produit() {}

    // Getter methods
    public int getIdProduit() {
        return idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    // Setter methods
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }
}
