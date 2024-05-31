package com.example.doan;

import static com.example.doan.MainActivity.dbLoaiGhiChu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.Data.DBGhiChu;

public class SuaXoaLoai extends AppCompatActivity {
    EditText edtMa, edtTen;
    Button btnThoat, btnXoa, btnSua;
    LoaiGhiChu loaiGhiChu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sualoaighichu);
        setControl();
        setEvent();
    }

    private void setEvent() {

        loaiGhiChu = (LoaiGhiChu) getIntent().getSerializableExtra("item1");
        edtMa.setText(loaiGhiChu.getMaLoai());
        edtTen.setText(loaiGhiChu.getTenLoai());

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiGhiChu loai = new LoaiGhiChu();
                loai.setMaLoai(edtMa.getText().toString());
                // Xóa loại ghi chú khỏi cơ sở dữ liệu
                MainActivity.dbLoaiGhiChu.XoaLoaiGC(loai);
                MainActivity.adapter_Loai.notifyDataSetChanged();
                Toast.makeText(SuaXoaLoai.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.dbLoaiGhiChu = new DBGhiChu(SuaXoaLoai.this);
                LoaiGhiChu loai = new LoaiGhiChu();
                // Cập nhật thông tin sản phẩm trong cơ sở dữ liệu
                loai.setMaLoai(edtMa.getText().toString());
                loai.setTenLoai(edtTen.getText().toString());
                MainActivity.dbLoaiGhiChu.CapnhapLoaiGC(loai);
                Toast.makeText(SuaXoaLoai.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }
    private void setControl() {
        btnXoa = findViewById(R.id.btnXoa);
        edtMa = findViewById(R.id.edtMaLoai);
        edtTen = findViewById(R.id.edtTenLoai);
        btnThoat = findViewById(R.id.btnBlack);
        btnSua = findViewById(R.id.btnSua);
    }
}