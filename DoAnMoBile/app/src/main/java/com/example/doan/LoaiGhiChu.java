package com.example.doan;

import java.io.Serializable;

public class LoaiGhiChu implements Serializable {
    private String MaLoai, TenLoai;

    public LoaiGhiChu() {
    }

    public LoaiGhiChu(String maLoai, String tenLoai) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    @Override
    public String toString() {
//        return "LoaiGhiChu{" +
//                "MaLoai='" + MaLoai + '\'' +
//                ", TenLoai='" + TenLoai + '\'' +
//                '}';

        return  TenLoai;
    }
}
