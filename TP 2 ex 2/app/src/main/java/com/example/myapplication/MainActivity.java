package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText p,t;
    RadioButton m,c;
    Button calc,rez;
    TextView res;
    float r=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.radio_metre), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        p=findViewById(R.id.p);
        t=findViewById(R.id.t);
        m=findViewById(R.id.radio_metre);
        c=findViewById(R.id.radio_centimètre);
        calc=findViewById(R.id.btn_cal_imc);
        rez=findViewById(R.id.btn_raz);
        res=findViewById(R.id.txt_res);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float p1=Float.valueOf(p.getText().toString().trim());
                float t1=Float.valueOf(t.getText().toString().trim());
                if(c.isChecked()){
                     r= (float) (p1/(Math.pow(t1,2)));
                }
                if(m.isChecked()){
                     r= (float) (p1/(Math.pow((t1/100),2)));
                }
                res.setText(String.valueOf(r));

            }
        });
        rez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.getText().clear();
                t.getText().clear();
                res.setText("Vous devez cliquer sur le bouton « Calculer l’IMC » pour obtenir un résultat.");



            }
        });



    }
}