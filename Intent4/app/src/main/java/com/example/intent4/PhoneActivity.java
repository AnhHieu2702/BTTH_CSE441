package com.example.intent4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PhoneActivity extends AppCompatActivity {

    EditText edtcall;
    ImageButton btncallphone;
    Button btnback1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        edtcall = findViewById(R.id.edtcall);
        btnback1 = findViewById(R.id.btnback1);
        btncallphone = findViewById(R.id.btncall);

        btncallphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:" + edtcall.getText().toString()));

                if (ContextCompat.checkSelfPermission(PhoneActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PhoneActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(callIntent);
            }
        });

        btnback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}