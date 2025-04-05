package com.example.myapi_exp1;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapi_exp1.Models.ProductResponse;
import com.example.myapi_exp1.Models.Produit;
import com.example.myapi_exp1.service.RepoServiceAPI;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        ArrayList<String> listItems = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        // ✅ Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RepoServiceAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RepoServiceAPI myApi = retrofit.create(RepoServiceAPI.class);

        // ✅ Define the Call object properly
        Call<ProductResponse> call = myApi.Affichage();

        // Log the request URL before making the API call
        Log.d("API_URLhh", "Request URL: " + call.request().url().toString());

        // ✅ Make the API request
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

                for (Produit pr : productResponse.getProducts()) {
                    listItems.add("NOM: " + pr.getNomProduit());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                showToast("Network error");
                Log.e("API_ERROR", "Request failed", t);
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
