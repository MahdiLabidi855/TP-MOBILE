package com.example.myapi_exp1.service;


import com.example.myapi_exp1.Models.ProductResponse;
import com.example.myapi_exp1.Models.Produit;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RepoServiceAPI {
    String BASE_URL = "https://dummyjson.com/"; // Replace with your API URL

    @GET("products")  // Replace with your API endpoint
    Call<ProductResponse> Affichage();
}
