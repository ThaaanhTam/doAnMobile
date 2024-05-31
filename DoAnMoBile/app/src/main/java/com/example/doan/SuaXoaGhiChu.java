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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.doan.Data.DBGhiChu;


import java.util.ArrayList;
import java.util.List;


public class SuaXoaGhiChu extends AppCompatActivity {
    EditText edtTieuDe, edtNoiDung;
    TextView tvTime;
    Spinner spLoaiGC;
    Button btnThoat, btnSua, btnXoa, btnNhacNho;
    GhiChu ghiChu;
    ActivityResultLauncher<Intent> launcher;
    ArrayAdapter<LoaiGhiChu> spLoai;
    List<String> data_lgc = new ArrayList<>();


    //ArrayAdapter adapter_lgc;
    //List<String> data_lgc = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ghi_chu);
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
        ghiChu = (GhiChu) getIntent().getSerializableExtra("item");
        edtTieuDe.setText(ghiChu.getTieuDe());
        edtNoiDung.setText(ghiChu.getNoiDung());
        tvTime.setText(ghiChu.getNhacNho());
        int positionOfSelectedLoai = 0;
        for (int i = 0; i < data_Loai.size(); i++) {
            if (data_Loai.get(i).getTenLoai().equals(ghiChu.getLoaiGC())) {
                positionOfSelectedLoai = i;
                Log.d("sploai",data_Loai.get(i).getTenLoai().toString() );
                break;
            }
        }
        spLoaiGC.setSelection(positionOfSelectedLoai);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GhiChu ghichu = new GhiChu();
                // Xóa sản phẩm khỏi cơ sở dữ liệu
                ghichu.setTieuDe(edtTieuDe.getText().toString());
                MainActivity.dbGhiChu.XoaDL(ghichu);
                MainActivity.adapter_gc.notifyDataSetChanged();
                Toast.makeText(SuaXoaGhiChu.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MainActivity.dbGhiChu = new DBGhiChu(SuaXoaGhiChu.this);
                GhiChu ghiChu = new GhiChu();
                // Cập nhật thông tin sản phẩm trong cơ sở dữ liệu
                ghiChu.setTieuDe(edtTieuDe.getText().toString());
                ghiChu.setNoiDung(edtNoiDung.getText().toString());
                ghiChu.setNhacNho(tvTime.getText().toString());
                ghiChu.setLoaiGC(spLoaiGC.getSelectedItem().toString());
                MainActivity.dbGhiChu.Capnhap(ghiChu);
                Toast.makeText(SuaXoaGhiChu.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                onBackPressed();


            }
        });
        btnNhacNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuaXoaGhiChu.this, click_nhac_nho.class);
                launcher.launch(intent);
            }
        });
    }


    private void setControl() {
        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtNoiDung = findViewById(R.id.edtNDGhiChu);
        btnThoat = findViewById(R.id.btnQuayLai);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnNhacNho = findViewById(R.id.btnNhacNho);
        tvTime = findViewById(R.id.edtNhacNho);
        spLoaiGC = findViewById(R.id.spLoaiNN);
    }
}



