package com.example.doan;

import java.io.Serializable;

public class GhiChu implements Serializable {

    String tieuDe;
    String noiDung;
    String loaiGC;
    String nhacNho;

    public String getNhacNho() {
        return nhacNho;
    }

    public void setNhacNho(String nhacNho) {
        this.nhacNho = nhacNho;
    }

    public GhiChu(String tieuDe, String noiDung, String nhacNho, String loaiGC) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.nhacNho = nhacNho;
        this.loaiGC = loaiGC;
    }

    public GhiChu() {
    }

    public String getLoaiGC() {
        return loaiGC;
    }

    public void setLoaiGC(String loaiGC) {
        this.loaiGC = loaiGC;
    }

    public String   getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public String toString() {
        return "GhiChu{" +
                "TieuDe: '" + tieuDe + '\'' +
                ", NoiDung:'" + '\'' + noiDung + '\'' +
               ", LoaiGhiChu:'" + '\'' + loaiGC + '\'' +
                ", NhacNho:'" + '\'' + nhacNho + '\'' +
                '\'' +
                '}';
    }
}
