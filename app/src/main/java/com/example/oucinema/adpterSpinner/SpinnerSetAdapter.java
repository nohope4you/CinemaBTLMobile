package com.example.oucinema.adpterSpinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.oucinema.R;
import com.example.oucinema.model.Suat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SpinnerSetAdapter extends ArrayAdapter<Suat> {


    public SpinnerSetAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Suat> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_set,parent,false);
        TextView tvFilmSelected =convertView.findViewById(R.id.tv_selected_set);
        Suat u = this.getItem(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        tvFilmSelected.setText(sdf.format(u.getNgayChieu()));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set,parent,false);
        TextView tvFilm =convertView.findViewById(R.id.tv_set);
        Suat u = this.getItem(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(u!=null){
            tvFilm.setText(sdf.format(u.getNgayChieu()));
        }
        return convertView;
    }
}
