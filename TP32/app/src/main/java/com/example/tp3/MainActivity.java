package com.example.tp3;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText inputField;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find Views
        inputField = findViewById(R.id.inputField); // Added EditText
        btnNext = findViewById(R.id.btnSend);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = inputField.getText().toString(); // Get text from input

                if (!str.isEmpty()) { // Prevent empty input
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("message_key", str);
                    startActivity(intent);
                } else {
                    inputField.setError("Please enter some text!"); // Error message
                    Log.e("MainActivity", "Input field is empty!"); // Log the error to Logcat
                }
            }
        });
    }
}
