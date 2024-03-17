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
import com.example.oucinema.model.Suat;
import com.example.oucinema.model.User;


import java.util.ArrayList;

public class SetFilmAdapter extends ArrayAdapter<Suat> {

    private Context mcontext;
    private int mResource;


    public SetFilmAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Suat> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvPHIM =  convertView.findViewById(R.id.textphimsetfilm);
        TextView tvPHONG = convertView.findViewById(R.id.textphongsetfilm);
        TextView tvGIA = convertView.findViewById(R.id.textgiasetfilm);

        tvPHIM.setText(getItem(position).getPhimID().getTenPhim());
        tvPHONG.setText(getItem(position).getPhongID().getTenPhong());
        tvGIA.setText(getItem(position).getGiaMacDinh().toString());


        return convertView;
    }
}
