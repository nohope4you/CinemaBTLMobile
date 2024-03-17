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
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Phong;


import java.util.ArrayList;

public class RoomAdapter extends ArrayAdapter<Phong> {

    private Context mcontext;
    private int mResource;


    public RoomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Phong> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvID = convertView.findViewById(R.id.textidphong);
        TextView tvTENPHONG = convertView.findViewById(R.id.texttenphong);
        TextView tvTENRAP = convertView.findViewById(R.id.texttenrap);

        tvID.setText(String.valueOf(getItem(position).getId()));
        tvTENPHONG.setText(getItem(position).getTenPhong());
//        tvTENRAP.setText(getItem(position).getRapPhimID().getTenRap());
        return convertView;
    }
}
