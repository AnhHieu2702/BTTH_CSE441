package com.hungthinh.sharepreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtA;
    private EditText edtB;
    private TextView txtKetQua;
    private TextView txtLichSu;
    private Button btnTong;
    private Button btnClear;

    private static final String PREF_NAME = "mysave";
    private static final String KEY_HISTORY = "ls";
    private String lichSu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // dùng layout trực tiếp

        // Ánh xạ view
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        txtKetQua = findViewById(R.id.txtKetQua);
        txtLichSu = findViewById(R.id.txtLichSu);
        btnTong = findViewById(R.id.btnTong);
        btnClear = findViewById(R.id.btnClear);

        // Load lịch sử đã lưu
        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        lichSu = sp.getString(KEY_HISTORY, "");
        if (lichSu == null) lichSu = "";
        txtLichSu.setText(lichSu);

        // Nút TỔNG
        btnTong.setOnClickListener(v -> {
            int a = 0, b = 0;
            try {
                String strA = edtA.getText().toString().trim();
                a = strA.isEmpty() ? 0 : Integer.parseInt(strA);
            } catch (NumberFormatException ignored) {}

            try {
                String strB = edtB.getText().toString().trim();
                b = strB.isEmpty() ? 0 : Integer.parseInt(strB);
            } catch (NumberFormatException ignored) {}

            int kq = a + b;
            txtKetQua.setText(String.valueOf(kq));
            lichSu += a + " + " + b + " = " + kq + "\n";
            txtLichSu.setText(lichSu);
        });

        // Nút CLEAR
        btnClear.setOnClickListener(v -> {
            lichSu = "";
            txtLichSu.setText(lichSu);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Lưu lịch sử
        getSharedPreferences(PREF_NAME, MODE_PRIVATE)
                .edit()
                .putString(KEY_HISTORY, lichSu)
                .apply();
    }
}
