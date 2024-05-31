package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doan.Data.DBGhiChu;

import java.util.ArrayList;
import java.util.List;

public class Timghichu extends AppCompatActivity {

    EditText edttim;
    Button btnTim,btnQuayLai;
    ListView lvTimGC;
    List<GhiChu> data_gc;
    DBGhiChu dbGhiChu;
    GhiChuArraydapter adapter_gc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timghichu);

            setControl();
        String loaiGhiChuTim = edttim.getText().toString();
        // Khởi tạo dbGhiChu
        dbGhiChu = new DBGhiChu(this);
        data_gc = new ArrayList<>();

        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move this line inside the onClick method to get the latest input when the button is clicked.
                String loaiGhiChuTim = edttim.getText().toString();

                data_gc.clear();

                // Use enhanced for loop for cleaner code
                for (GhiChu gc : dbGhiChu.DocDLGC()) {
                    if (gc.getLoaiGC().equals(loaiGhiChuTim)) {
                        data_gc.add(gc);
                    }
                }

                adapter_gc = new GhiChuArraydapter(Timghichu.this, R.layout.layout_noidung, data_gc);
                lvTimGC.setAdapter(adapter_gc);

                // Notify the adapter after setting the data
                adapter_gc.notifyDataSetChanged();
            }
        });


        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setControl() {
        btnTim = findViewById(R.id.btnTim);
        edttim = findViewById(R.id.edtTim);
        lvTimGC = findViewById(R.id.lvDSTim);
        btnQuayLai = findViewById(R.id.btnQuayLai);
    }
}