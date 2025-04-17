package com.example.crud_api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
public class ProdActivity extends AppCompatActivity {
    EditText edtid;
    EditText edtdes;
    EditText edtprix;
    Button btnSave;
    TextView txtUId;
    RepoServiceAPI myApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod);
        setTitle("Produits");

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtid= (EditText) findViewById(R.id.edtUId);
        edtdes = (EditText) findViewById(R.id.edtdes);
        edtid.setFocusable(false);

        edtprix = (EditText) findViewById(R.id.edtprix);
        btnSave = (Button) findViewById(R.id.btnSave);

        Bundle extras = getIntent().getExtras();
        final String prodId = extras.getString("id");
        String designation = extras.getString("des");
        String price = extras.getString("pri");
        edtid.setText(prodId);
        edtdes.setText(designation);
        edtprix.setText(price);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RepoServiceAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        myApi = retrofit.create(RepoServiceAPI.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produit p = new Produit();
                p.setNomProduit(edtdes.getText().toString());
                p.setPrixProduit(Double.valueOf(edtprix.getText().toString()));
                addProd(p);
                Intent intent = new Intent(ProdActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }
    public void addProd(Produit p){
        Call<Produit> call = myApi.addProd(p);
        call.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Call<Produit> call, Response<Produit> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProdActivity.this, "Product created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produit> call, Throwable t) {
                Log.i("ERROR: ", t.getMessage());
                Toast.makeText(ProdActivity.this, "Pb", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void updateProd(int id, Produit p){
        Call<Produit> call = myApi.updateProd(id,p);
        call.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Call<Produit> call, Response<Produit> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProdActivity.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produit> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteProd(int id){
        Call<Produit> call = myApi.deleteProd(id);
        call.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Call<Produit> call, Response<Produit> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProdActivity.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produit> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }


}




