package com.example.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LoaiGhiChuAdapter extends ArrayAdapter {
    Context content;
    int resource;

    List<LoaiGhiChu> data;

    public LoaiGhiChuAdapter(@NonNull Context context, int resource, List<LoaiGhiChu> data) {
        super(context, resource, data);
        this.content = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(content).inflate(resource, null);
        TextView tvTenLoai = convertView.findViewById(R.id.tvTenLoaiGhiChu);
        LoaiGhiChu loai = data.get(position);
        tvTenLoai.setText(loai.getTenLoai());
        return convertView;
    }
}
