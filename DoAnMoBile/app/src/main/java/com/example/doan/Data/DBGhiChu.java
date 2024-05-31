package com.example.doan.Data;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.doan.GhiChu;
import com.example.doan.LoaiGhiChu;


import java.util.ArrayList;
import java.util.List;


public class DBGhiChu extends SQLiteOpenHelper {
    public DBGhiChu(Context context) {
        super(context, "dbGhiChu_l", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table tbLoaiGhiChu (maLoai int , TenLoai text)";
        db.execSQL(sql);
        String sqlgc = "Create table tbGhiChu (tieude text, noidung text ,nhacnho text,loaigc text)";
        db.execSQL(sqlgc);
    }


    public List<GhiChu> xuatNN() {
        List<GhiChu> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "SELECT * FROM tbGhiChu WHERE nhacnho IS NOT NULL AND nhacnho <> ''";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                GhiChu gc = new GhiChu();
                gc.setTieuDe(cursor.getString(0));
                gc.setNoiDung(cursor.getString(1));
                gc.setNhacNho(cursor.getString(2));
                data.add(gc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }


    public List<GhiChu> Tim(GhiChu ghichu) {
        List<GhiChu> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "SELECT * FROM tbGhiChu WHERE noidung=?";
        sqLiteDatabase.execSQL(sql, new String[]{ghichu.getNoiDung()});
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null );
        if (cursor.moveToFirst()) {
            do {
                GhiChu gc = new GhiChu();
                gc.setTieuDe(cursor.getString(0));
                gc.setNoiDung(cursor.getString(1));
                gc.setNhacNho(cursor.getString(2));
                data.add(gc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    public List<GhiChu> TimTheoLoai(String loaiGhiChu) {
        List<GhiChu> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // Chú ý: Giả sử loaigc là cột trong bảng tbGhiChu để lưu trữ loại ghi chú.
        String sql = "SELECT * FROM tbGhiChu WHERE loaigc=?";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{loaiGhiChu});

        if (cursor.moveToFirst()) {
            do {
                GhiChu gc = new GhiChu();
                gc.setTieuDe(cursor.getString(0));
                gc.setNoiDung(cursor.getString(1));
                gc.setNhacNho(cursor.getString(2));
                gc.setLoaiGC(cursor.getString(3));
                data.add(gc);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return data;
    }


    public void ThemLoaiGC(LoaiGhiChu loaiGhiChu) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "insert into tbLoaiGhiChu values(?,?)";
        sqLiteDatabase.execSQL(sql, new String[]{loaiGhiChu.getMaLoai(), loaiGhiChu.getTenLoai()});
    }
    public List<LoaiGhiChu> DocLoaiGC() {
        List<LoaiGhiChu> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "select * from tbLoaiGhiChu";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                LoaiGhiChu loai = new LoaiGhiChu();
                loai.setMaLoai(cursor.getString(0));
                loai.setTenLoai(cursor.getString(1));
                data.add(loai);
            }
            while (cursor.moveToNext());
        }
        return data;
    }
    public void XoaLoaiGC(LoaiGhiChu loaiGhiChu) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // Xóa đổi tên bảng trong câu lệnh xóa dữ liệu
        String sql = "DELETE FROM tbLoaiGhiChu WHERE maloai = ?";
        sqLiteDatabase.execSQL(sql, new String[]{loaiGhiChu.getMaLoai()});
    }
    public void CapnhapLoaiGC(LoaiGhiChu loaiGhiChu) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // Sửa đổi tên bảng trong câu lệnh cập nhật dữ liệu
        String sql = "UPDATE tbGhiChu SET tenLoai=? WHERE maLoai=?";
        sqLiteDatabase.execSQL(sql, new String[]{loaiGhiChu.getTenLoai(), loaiGhiChu.getMaLoai()});
    }
    ///database cho ghi chu
    public void ThemGC(GhiChu ghiChu) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "insert into tbGhiChu values(?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new String[]{ghiChu.getTieuDe(), ghiChu.getNoiDung(), ghiChu.getNhacNho(), ghiChu.getLoaiGC()});
    }


    public List<GhiChu> DocDLGC() {
        List<GhiChu> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sqlgc = "select * from tbGhiChu";
        Cursor cursor = sqLiteDatabase.rawQuery(sqlgc, null);
        if (cursor.moveToFirst()) {
            do {
                GhiChu gc = new GhiChu();
                gc.setTieuDe(cursor.getString(0));
                gc.setNoiDung(cursor.getString(1));
                gc.setNhacNho(cursor.getString(2));
                gc.setLoaiGC(cursor.getString(3));
                data.add(gc);
            }
            while (cursor.moveToNext());
        }
        return data;
    }


    public void XoaDL(GhiChu ghiChu) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // Xóa đổi tên bảng trong câu lệnh xóa dữ liệu
        String sql = "DELETE FROM tbGhiChu WHERE tieude = ?";
        sqLiteDatabase.execSQL(sql, new String[]{ghiChu.getTieuDe()});
    }


    public void Capnhap(GhiChu ghiChu) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // Sửa đổi tên bảng trong câu lệnh cập nhật dữ liệu
        String sql = "UPDATE tbGhiChu SET noidung=?, nhacnho=?,loaigc=? WHERE tieude=?";
        sqLiteDatabase.execSQL(sql, new String[]{ghiChu.getNoiDung(),ghiChu.getNhacNho(),ghiChu.getLoaiGC(), ghiChu.getTieuDe()});
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}

