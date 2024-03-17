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
import com.example.oucinema.model.Phim;


import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Phim> {

    private Context mcontext;
    private int mResource;


    public FilmAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Phim> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvTenPhim = convertView.findViewById(R.id.textTenPhim);
        TextView tvTheLoai = convertView.findViewById(R.id.textTheLoai);
        TextView tvDaoDien = convertView.findViewById(R.id.textDaoDien);

        tvTenPhim.setText(getItem(position).getTenPhim());
        tvTheLoai.setText(getItem(position).getTheLoai());
        tvDaoDien.setText(getItem(position).getDaoDien());

        return convertView;
    }
}
