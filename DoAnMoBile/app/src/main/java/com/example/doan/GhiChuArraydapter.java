package com.example.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GhiChuArraydapter extends ArrayAdapter {
    Context content;
    int resource;

    List<GhiChu> data;

    public GhiChuArraydapter(@NonNull Context context, int resource, List<GhiChu> data) {
        super(context, resource, data);
        this.content = context;
        this.resource = resource;
        this.data = data;
    }

   @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(content).inflate(resource, null);
        TextView tvTieuDe = convertView.findViewById(R.id.tvTieuDe);
        TextView tvNoiDung = convertView.findViewById(R.id.tvNoiDung);
        TextView tvNhacNho = convertView.findViewById(R.id.tvNhacNho);
        TextView tvLoai = convertView.findViewById(R.id.tvLoaiGhiChu);
        GhiChu ghiChu = data.get(position);
        tvTieuDe.setText(ghiChu.getTieuDe());
        tvNoiDung.setText(ghiChu.getNoiDung());
        tvNhacNho.setText(ghiChu.getNhacNho());
        tvLoai.setText(ghiChu.getLoaiGC());
        return convertView;
    }
}
