package com.example.doan;

import static com.example.doan.MainActivity.dbLoaiGhiChu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doan.Data.DBGhiChu;

import java.util.ArrayList;
import java.util.List;

public class LoaiGhiChuMoi extends AppCompatActivity {
    EditText edtMa, edtTen;
    Button btnThoat, btnLuu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_ghi_chu_moi);
        setControl();
        setEvent();
    }

    private void setEvent() {

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiGhiChu loai = new LoaiGhiChu();
                loai.setMaLoai(edtMa.getText().toString());
                loai.setTenLoai(edtTen.getText().toString());
                dbLoaiGhiChu.ThemLoaiGC(loai);
                MainActivity.data_Loai.add(loai);
                MainActivity.adapter_Loai.notifyDataSetChanged();
                onBackPressed();
            }
        });


        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void setControl() {
        edtMa = findViewById(R.id.edtMaLoai);
        edtTen = findViewById(R.id.edtTenLoai);
        btnThoat = findViewById(R.id.btnBlack);
        btnLuu = findViewById(R.id.btnSave);

    }
}