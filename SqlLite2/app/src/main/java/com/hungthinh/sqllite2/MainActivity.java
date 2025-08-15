package com.hungthinh.sqllite2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data = new ArrayList<>();

    private DatabaseAssetHelper helper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);

        helper = new DatabaseAssetHelper(this);

        try {
            database = helper.openDatabase();
        } catch (Exception e) {
            Toast.makeText(this, "Open DB error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        loadData();
    }

    private void loadData() {
        data.clear();

        // Lấy 3 cột đầu của bảng tbsach
        Cursor c = database.query("tbsach", null, null, null, null, null, null);
        if (c != null) {
            try {
                if (c.moveToFirst()) {
                    do {
                        String s = c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2);
                        data.add(s);
                    } while (c.moveToNext());
                }
            } finally {
                c.close();
            }
        }

        adapter.notifyDataSetChanged();
    }
}
