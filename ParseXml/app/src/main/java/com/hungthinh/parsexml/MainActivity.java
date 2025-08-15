package com.hungthinh.parsexml;

import android.os.Bundle;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnParse;
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> display = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParse = findViewById(R.id.btnparse);
        lv = findViewById(R.id.lv);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, display);
        lv.setAdapter(adapter);

        btnParse.setOnClickListener(v -> {
            try {
                List<Employee> employees = parseEmployeesFromAssets("employee.xml");
                display.clear();
                for (int i = 0; i < employees.size(); i++) {
                    Employee e = employees.get(i);
                    display.add((i + 1) + "-" + e.getTitle() + "-" + e.getName() + "-" + e.getPhone());
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                Toast.makeText(this, "Parse lỗi: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Employee> parseEmployeesFromAssets(String fileName) throws Exception {
        List<Employee> list = new ArrayList<>();

        try (InputStream input = getAssets().open(fileName)) {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(input, "UTF-8");

            int event = parser.getEventType();
            Employee current = null;

            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if ("employee".equals(parser.getName())) {
                            current = new Employee();
                            String idValue = parser.getAttributeValue(null, "id");
                            if (idValue != null) {
                                try {
                                    current.setId(Integer.parseInt(idValue));
                                } catch (NumberFormatException ignored) {
                                }
                            }
                            String titleValue = parser.getAttributeValue(null, "title");
                            if (titleValue != null) {
                                current.setTitle(titleValue);
                            }
                        } else if ("name".equals(parser.getName())) {
                            if (current != null) {
                                current.setName(parser.nextText());
                            }
                        } else if ("phone".equals(parser.getName())) {
                            if (current != null) {
                                current.setPhone(parser.nextText());
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("employee".equals(parser.getName()) && current != null) {
                            list.add(current);
                            current = null;
                        }
                        break;
                }
                event = parser.next();
            }
        }

        return list;
    }
}
