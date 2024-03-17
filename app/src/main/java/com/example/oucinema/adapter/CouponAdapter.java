package com.example.oucinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.oucinema.R;
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phim;


import java.util.ArrayList;

public class CouponAdapter extends ArrayAdapter<MaGiamGia> {

    private Context mcontext;
    private int mResource;


    public CouponAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MaGiamGia> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvmagiamgia = convertView.findViewById(R.id.textTenMaGiamGia);
        TextView tvngayhieuluc = convertView.findViewById(R.id.textThoiGianHieuLuc);
        TextView tvphantram = convertView.findViewById(R.id.textPhantram);

        tvmagiamgia.setText(getItem(position).getTenMaGiam());
        tvphantram.setText(String.valueOf(getItem(position).getPhanTramGiam()));
        tvngayhieuluc.setText(getItem(position).getThoiGianHieuLuc().toString());

        return convertView;
    }
}