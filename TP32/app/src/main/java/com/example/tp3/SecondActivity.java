package com.example.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Find the TextView in the layout
         textView = findViewById(R.id.txtSecond);

        // Get the Intent that started this activity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("message_key")) {
            // Retrieve the data from the Intent
            String message = intent.getStringExtra("message_key");

            // Display the message in the TextView
            textView.setText(message);
        } else {
            textView.setText("No message received!"); // Default message
        }
    }
}
