package com.example.crud_api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.*;
public class MainActivity extends AppCompatActivity {

    Button btnAddProd;
    Button btnGetProduitsList;
    RecyclerView Recycler;
    RepoServiceAPI myApi;
    List<Produit> list = new ArrayList<>();
    ProductAdapter ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Application CRUD");

        btnAddProd = findViewById(R.id.btnAddUser);
        btnGetProduitsList = findViewById(R.id.btnGetUsersList);
        Recycler = findViewById(R.id.RecyclerV);

        // Set up RecyclerView with a LinearLayoutManager
        Recycler.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Retrofit only once here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RepoServiceAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(RepoServiceAPI.class);

        // Set up button listeners
        btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProdActivity.class);
                intent.putExtra("Des", "");
                startActivity(intent);
            }
        });

        btnGetProduitsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProdsList();
            }
        });
    }

    public void getProdsList() {
        // Create a new adapter and set it to RecyclerView
        ad = new ProductAdapter(this, list); // Pass the list of Produit objects
        Recycler.setAdapter(ad);

        // Create the call to the API
        Call<ProductResponse> call = myApi.Affichage();

        // Log the request URL before making the API call
        Log.d("API_URL", "Request URL: " + call.request().url().toString());

        // Make the API request
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (!response.isSuccessful()) {
                    showToast("API Error: " + response.code());
                    return;
                }

                // Log the response body to check the returned data
                Log.d("API_RESPONSE", "Response body: " + response.body());

                ProductResponse productResponse = response.body();
                if (productResponse == null || productResponse.getProducts().isEmpty()) {
                    showToast("No products found");
                    return;
                }

                // Clear the list and add the new products
                list.clear();
                list.addAll(productResponse.getProducts());

                // Notify the adapter that the data has changed
                ad.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                showToast("Network error");
                Log.e("API_ERROR", "Request failed", t);
            }

            private void showToast(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
