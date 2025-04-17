package com.example.crud_api;

import retrofit2.Call;
import retrofit2.http.*;


public interface RepoServiceAPI {
    String BASE_URL = "https://dummyjson.com/";

    @GET("products")
    Call<ProductResponse> Affichage();

    @GET("products{id}")
    Call<Produit> GetOne(@Path("id") int id);

    @POST("products")
    Call<Produit> addProd(@Body Produit p);
    @DELETE("products{id}")
    Call<Produit> deleteProd(@Path("id") int id);
    @PUT("products{id}")
    Call<Produit> updateProd(@Path("id") int id, @Body Produit p);

    @POST("/products/add")
    Call<Produit> addProduct(@Body Produit produit);

}
