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

    private void insertRow() {
        String ma  = edtMaLop.getText().toString().trim();
        String ten = edtTenLop.getText().toString().trim();
        String sisoStr = edtSiSo.getText().toString().trim();

        if (ma.isEmpty() || ten.isEmpty() || sisoStr.isEmpty()) {
            toast("Vui lòng nhập MÃ LỚP, TÊN LỚP và SĨ SỐ hợp lệ");
            return;
        }

        Integer siso;
        try {
            siso = Integer.parseInt(sisoStr);
        } catch (NumberFormatException e) {
            toast("Sĩ số phải là số");
            return;
        }

        ContentValues values = new ContentValues();
        values.put("malop", ma);
        values.put("tenlop", ten);
        values.put("siso", siso);

        long res = db.insert("tbllop", null, values);
        if (res == -1) {
            toast("Thêm thất bại! (Trùng MÃ LỚP?)");
        } else {
            toast("Thêm thành công");
            clearInputs();
            loadAll();
        }
    }

    private void updateRow() {
        String ma  = edtMaLop.getText().toString().trim();
        String sisoStr = edtSiSo.getText().toString().trim();

        if (ma.isEmpty() || sisoStr.isEmpty()) {
            toast("Nhập MÃ LỚP và SĨ SỐ hợp lệ để cập nhật");
            return;
        }

        Integer siso;
        try {
            siso = Integer.parseInt(sisoStr);
        } catch (NumberFormatException e) {
            toast("Sĩ số phải là số");
            return;
        }

        ContentValues values = new ContentValues();
        values.put("siso", siso);

        int n = db.update("tbllop", values, "malop = ?", new String[]{ma});
        if (n == 0) {
            toast("Không tìm thấy MÃ LỚP để cập nhật");
        } else {
            toast("Đã cập nhật " + n + " bản ghi");
            loadAll();
        }
    }

    private void deleteRow() {
        String ma = edtMaLop.getText().toString().trim();
        if (ma.isEmpty()) {
            toast("Nhập MÃ LỚP để xóa");
            return;
        }
        int n = db.delete("tbllop", "malop = ?", new String[]{ma});
        if (n == 0) {
            toast("Không tìm thấy MÃ LỚP để xóa");
        } else {
            toast("Đã xóa " + n + " bản ghi");
            clearInputs();
            loadAll();
        }
    }

    private void loadAll() {
        myList.clear();
        Cursor c = db.query("tbllop", null, null, null, null, null, "malop ASC");
        if (c.moveToFirst()) {
            do {
                String ma   = c.getString(c.getColumnIndexOrThrow("malop"));
                String ten  = c.getString(c.getColumnIndexOrThrow("tenlop"));
                int siso    = c.getInt(c.getColumnIndexOrThrow("siso"));
                myList.add(ma + " - " + ten + " - " + siso);
            } while (c.moveToNext());
        }
        c.close();
        myAdapter.notifyDataSetChanged();
    }

    private void clearInputs() {
        edtMaLop.setText("");
        edtTenLop.setText("");
        edtSiSo.setText("");
        edtMaLop.requestFocus();
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
