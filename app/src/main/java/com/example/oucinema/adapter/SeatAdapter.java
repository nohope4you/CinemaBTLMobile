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
import android.widget.Button;
import android.widget.TextView;

import com.example.oucinema.DBHelper;
import com.example.oucinema.ManageSeat;
import com.example.oucinema.R;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Phim;


import java.util.ArrayList;

public class SeatAdapter extends ArrayAdapter<Ghe> {

    private Context mcontext;
    private int mResource;


    public SeatAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Ghe> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvTenGhe = convertView.findViewById(R.id.textTenGhe);
        TextView tvLoaiGhe = convertView.findViewById(R.id.textLoaiGhe);

        tvTenGhe.setText(getItem(position).getTenGhe());
        tvLoaiGhe.setText(getItem(position).getLoaiGhe());
        return convertView;
    }
}
