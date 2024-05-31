package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.Data.DBGhiChu;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvDanhSach;
    TextView tvThem, tvThemLoai, tvDanhSach;
    static List<GhiChu> data_gc = new ArrayList<>();
    static List<LoaiGhiChu> data_Loai = new ArrayList<>();
        static DBGhiChu dbGhiChu;
    static DBGhiChu dbLoaiGhiChu;
   static GridView hienthiloaigc;

        static GhiChuArraydapter adapter_gc;
    static LoaiGhiChuAdapter adapter_Loai;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Activity fragment = null;

                if (item.getItemId() == R.id.Them) {
                    Intent intent=new Intent(MainActivity.this, ThemGhiChuMoi.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.DanhSach) {

                    DanhSachGhiChu.data_gc.clear();
                    DanhSachGhiChu.data_gc.addAll(dbGhiChu.DocDLGC());
                    if (DanhSachGhiChu.adapter_gc != null) {
                        DanhSachGhiChu.adapter_gc.notifyDataSetChanged();
                    }


                    Intent intent =new Intent(MainActivity.this, DanhSachGhiChu.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Danh sách ghi chú ", Toast.LENGTH_SHORT).show();
                }


                if (item.getItemId() == R.id.ToDoList) {
                    Intent intent=new Intent(MainActivity.this, Todolist.class);
                    startActivity(intent);
                }


                if (item.getItemId() == R.id.love) {
                    Intent intent=new Intent(MainActivity.this, Timghichu.class);
                    startActivity(intent);
                }


                if (item.getItemId() == R.id.NhacNho) {

                    DSNhacNho.data_gc.clear();
                    DSNhacNho.data_gc.addAll(dbGhiChu.xuatNN());
                    if (DSNhacNho.adapter_gc != null) {
                        DSNhacNho.adapter_gc.notifyDataSetChanged();
                    }
                    Intent intent=new Intent(MainActivity.this, DSNhacNho.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Danh sách nhắc nhở  ", Toast.LENGTH_SHORT).show();
                }
                if (item.getItemId() == R.id.mnThoat) {
                    finish();
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
        dbGhiChu = new DBGhiChu(this);
        dbLoaiGhiChu = new DBGhiChu(this);
        tvDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<GhiChu> danhsachGC = dbGhiChu.DocDLGC();
                // Xóa dữ liệu cũ và thêm dữ liệu mới từ cơ sở dữ liệu
                data_gc.clear();
                MainActivity.data_gc.addAll(danhsachGC);
                // Thông báo cho adapter biết có sự thay đổi
                MainActivity.adapter_gc.notifyDataSetChanged();

                List<LoaiGhiChu> danhsachLGC = dbLoaiGhiChu.DocLoaiGC();
                // Xóa dữ liệu cũ và thêm dữ liệu mới từ cơ sở dữ liệu
                data_Loai.clear();
                MainActivity.data_Loai.addAll(danhsachLGC);
                // Thông báo cho adapter biết có sự thay đổi
                MainActivity.adapter_Loai.notifyDataSetChanged();
            }
        });
        tvThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemGhiChuMoi.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        //KhoiTao();
        adapter_gc = new GhiChuArraydapter(this, R.layout.layout_noidung, data_gc);
        lvDanhSach.setAdapter(adapter_gc);


        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SuaXoaGhiChu.class);
                intent.putExtra("item", data_gc.get(i));
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                data_gc.remove(i);
                adapter_gc.notifyDataSetChanged();
                return false;
            }
        });
        tvThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoaiGhiChuMoi.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        adapter_Loai = new LoaiGhiChuAdapter(this, R.layout.layou_loai, data_Loai);
        hienthiloaigc.setAdapter(adapter_Loai);
        hienthiloaigc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SuaXoaLoai.class);
                intent.putExtra("item1", data_Loai.get(i));
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        hienthiloaigc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                data_Loai.remove(i);
                adapter_Loai.notifyDataSetChanged();
                return false;
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);


    }
    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
        tvThem = findViewById(R.id.tvThem);
        tvThemLoai = findViewById(R.id.tvThemLoai);
        hienthiloaigc = findViewById(R.id.hienThiLoai);
        tvDanhSach = findViewById(R.id.tvLoadDS);
        drawerLayout = findViewById(R.id.drawlayoyt);
        navigationView = findViewById(R.id.navView);
    }

}