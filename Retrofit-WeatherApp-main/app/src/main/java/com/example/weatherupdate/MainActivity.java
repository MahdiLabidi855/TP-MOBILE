package com.example.weatherupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weatherupdate.Retrofit.APIclient;
import com.example.weatherupdate.Retrofit.ApiInterface;
import com.example.weatherupdate.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Button get ;
    TextView temptext , desctext , humiditytext;
    EditText textfield ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         get=findViewById(R.id.search);
         temptext=findViewById(R.id.temptxt);
         desctext=findViewById(R.id.desctext);
         humiditytext=findViewById(R.id.humtext);
         textfield = findViewById(R.id.textField);

         get.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 getWeatherData(textfield.getText().toString().trim());
             }
         });


    }
    private void getWeatherData(String name)
    {
        ApiInterface apiInterface = APIclient.getclient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.d("Data",response.body().getMain().getTemp());
                temptext.setText("Temp : "+response.body().getMain().getTemp());
                desctext.setText("Feels like : "+response.body().getMain().getFeels_liek());
                humiditytext.setText("Humidity :"+response.body().getMain().getHumidity());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}