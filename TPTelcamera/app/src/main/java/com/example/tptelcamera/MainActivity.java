package com.example.tptelcamera;
import android.Manifest;

import android.content.*;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callButton = findViewById(R.id.callButton);
        Button smsButton = findViewById(R.id.smsButton);
        Button openUrlButton = findViewById(R.id.openUrlButton);
        Button cameraButton = findViewById(R.id.cameraButton);

        callButton.setOnClickListener(v -> makePhoneCall());
        smsButton.setOnClickListener(v -> sendSMS());
        openUrlButton.setOnClickListener(v -> openURL());
        cameraButton.setOnClickListener(v -> startActivity(new Intent(this, CameraActivity.class)));
    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            Uri telnumber = Uri.parse("tel:0123456789");
            Intent call = new Intent(Intent.ACTION_CALL, telnumber);
            startActivity(call);
        }
    }

    private void sendSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
        } else {
            sendText();
        }

    }
    private void sendText() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("0123456789", null, "Message from TPTelCamera", null, null);
    }

    private void openURL() {
        String url = "https://google.com";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            makePhoneCall();
        } else if (requestCode == REQUEST_SMS && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendSMS();
        }
    }
}
