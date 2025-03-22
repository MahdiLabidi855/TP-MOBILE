package com.example.myrecyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
RecyclerView rv;
String[] titre;
String[] Soust;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rv=findViewById(R.id.RecyclerV);
        titre = getResources().getStringArray(R.array.tit);
        Soust = getResources().getStringArray(R.array.stit);
        ListAdapter ad;
        Integer[] img= {R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1,R.drawable.img,R.drawable.img1};

        ad =new ListAdapter(MainActivity.this,titre,Soust,img);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(ad);

    }
}