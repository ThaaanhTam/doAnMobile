package com.example.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.Data.DBGhiChu;

import java.util.ArrayList;
import java.util.List;

public class DSNhacNho extends AppCompatActivity {
    static DBGhiChu dbGhiChu;
    ListView lvDSNN;
    Button btnQuayLai;

    static List<GhiChu> data_gc = new ArrayList<>();
    static GhiChuArraydapter adapter_gc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsnhac_nho);
        setControl();
        setEvent();
    }


    private void setEvent() {

        adapter_gc = new GhiChuArraydapter(this, R.layout.layout_noidung, data_gc);
        lvDSNN.setAdapter(adapter_gc);

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DSNhacNho.this, MainActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(intent);
            }
        });

    }

    private void setControl() {
        lvDSNN = findViewById(R.id.lvDSNN);
        btnQuayLai = findViewById(R.id.btnQuayLai);
    }
}