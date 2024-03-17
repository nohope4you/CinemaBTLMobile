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
import com.example.oucinema.model.RapPhim;

import java.util.ArrayList;

public class TheaterAdapter extends ArrayAdapter<RapPhim> {

    private Context mcontext;
    private int mResource;


    public TheaterAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RapPhim> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvTenRap = convertView.findViewById(R.id.textViewTenRap);
        TextView tvDiaChi = convertView.findViewById(R.id.textViewDiaChiRap);
        TextView tvSDT = convertView.findViewById(R.id.textViewsdt);

        tvTenRap.setText(getItem(position).getTenRap());
        tvDiaChi.setText(getItem(position).getDiaChi());
        tvSDT.setText(getItem(position).getSoDienThoaiLienHe());

        return convertView;
    }
}
