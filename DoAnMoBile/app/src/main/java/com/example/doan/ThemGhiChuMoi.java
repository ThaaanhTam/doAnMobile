package com.example.doan;


import static com.example.doan.MainActivity.adapter_Loai;
import static com.example.doan.MainActivity.data_Loai;
import static com.example.doan.MainActivity.hienthiloaigc;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.doan.Data.DBGhiChu;


import java.util.ArrayList;
import java.util.List;


public class    ThemGhiChuMoi extends AppCompatActivity {


    EditText edtTieuDe, edtNoiDung, edtNhacNho;
    TextView tvTime;
    Button btnThoat, btnLuu, btnXoa, btnNhacNho;
    Spinner spLoaiGC;
    List<String> data_lgc = new ArrayList<>();
    ArrayAdapter<String> adapter_lgc;
    ArrayAdapter<LoaiGhiChu> spLoai;
    DBGhiChu dbGhiChu;
    ActivityResultLauncher<Intent> launcher;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        setControl();
        setEvent();
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                String Ngay = data.getStringExtra("ngay");
                                String tg = data.getStringExtra("tg");
                                tvTime.setText(Ngay+", "+tg);
                            }
                        }
                    }
                });
    }
    private void setEvent() {
        KhoiTao();
        dbGhiChu = new DBGhiChu(this);
        btnNhacNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemGhiChuMoi.this, click_nhac_nho.class);
                launcher.launch(intent);
            }
        });
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvTime.getText().toString().length() >= 1) {
                    Intent intent = new Intent(ThemGhiChuMoi.this, click_nhac_nho.class);
                    launcher.launch(intent);
                }


            }
        });
        adapter_Loai = new LoaiGhiChuAdapter(this, R.layout.layou_loai, data_Loai);
        hienthiloaigc.setAdapter(adapter_Loai);


        // Thiết lập sự kiện khi một mục của ListView được chọn
        hienthiloaigc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Lấy giá trị từ mục được chọn
                LoaiGhiChu selectedItem = data_Loai.get(position);
                // Thêm giá trị vào Spinner
                spLoai.add(selectedItem);
                spLoai.notifyDataSetChanged();
            }
        });


        // Khởi tạo ArrayAdapter và thiết lập dữ liệu cho Spinner
        spLoai = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_Loai);
        spLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLoaiGC.setAdapter(spLoai);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GhiChu ghiChu = new GhiChu();
                ghiChu.setTieuDe(edtTieuDe.getText().toString());
                ghiChu.setNoiDung(edtNoiDung.getText().toString());
                ghiChu.setNhacNho(tvTime.getText().toString());
                ghiChu.setLoaiGC(spLoaiGC.getSelectedItem().toString());
                dbGhiChu.ThemGC(ghiChu);
                MainActivity.data_gc.add(ghiChu);
                MainActivity.adapter_gc.notifyDataSetChanged();
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


    private void KhoiTao() {
        data_lgc.add("Gia Đình");
        data_lgc.add("Học Tập");
        data_lgc.add("Bạn Bè");
        data_lgc.add("Sức Khỏe");
    }


    private void setControl() {
        btnXoa = findViewById(R.id.btnXoa);
        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtNoiDung = findViewById(R.id.edtNDGhiChu);
        btnThoat = findViewById(R.id.btnBlack);
        btnLuu = findViewById(R.id.btnSave);
        spLoaiGC = findViewById(R.id.spLoaiNN);
        btnNhacNho = findViewById(R.id.btnNhacNho);
        tvTime = findViewById(R.id.edtNhacNho);
    }
}



