package com.example.project_cal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edt1, edt2, edt3;
    Button btncong, btntru, btnnhan, btnchia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.edta);
        edt2 = findViewById(R.id.edtb);
        edt3 = findViewById(R.id.edtc);

        btncong = findViewById(R.id.btncong);
        btntru = findViewById(R.id.btntru);
        btnnhan = findViewById(R.id.btnnhan);
        btnchia = findViewById(R.id.btnchia);
        
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(edt1.getText().toString());
                    int b = Integer.parseInt(edt2.getText().toString());
                    int tong = a + b;
                    edt3.setText(a + " + " + b + " = " + tong);
                } catch (NumberFormatException e) {
                    edt3.setText("Vui lòng nhập số hợp lệ");
                }
            }
        });

        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(edt1.getText().toString());
                    int b = Integer.parseInt(edt2.getText().toString());
                    int hieu = a - b;
                    edt3.setText(a + " - " + b + " = " + hieu);
                } catch (NumberFormatException e) {
                    edt3.setText("Vui lòng nhập số hợp lệ");
                }
            }
        });

        btnnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(edt1.getText().toString());
                    int b = Integer.parseInt(edt2.getText().toString());
                    int tich = a * b;
                    edt3.setText(a + " * " + b + " = " + tich);
                } catch (NumberFormatException e) {
                    edt3.setText("Vui lòng nhập số hợp lệ");
                }
            }
        });

        btnchia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(edt1.getText().toString());
                    int b = Integer.parseInt(edt2.getText().toString());
                    if (b != 0) {
                        float thuong = (float) a / b;
                        edt3.setText(a + " / " + b + " = " + thuong);
                    } else {
                        edt3.setText("Không thể chia cho 0");
                    }
                } catch (NumberFormatException e) {
                    edt3.setText("Vui lòng nhập số hợp lệ");
                }
            }
        });
    }
}