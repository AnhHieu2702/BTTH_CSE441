package com.hungthinh.student;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtMaLop;   // editTextText
    private EditText edtTenLop;  // editTextText2
    private EditText edtSiSo;    // editTextNumber

    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnQuery;
    private ListView lv;

    private ArrayList<String> myList;
    private ArrayAdapter<String> myAdapter;
    private SQLiteDatabase db;
}
