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
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Suat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SpinnerSeatAdapter extends ArrayAdapter<Ghe> {


    public SpinnerSeatAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Ghe> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_seat,parent,false);
        TextView tvFilmSelected =convertView.findViewById(R.id.tv_selected_seat);
        Ghe ghe = this.getItem(position);

        tvFilmSelected.setText(ghe.getTenGhe());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat,parent,false);
        TextView tvFilm =convertView.findViewById(R.id.tv_seat);
        Ghe ghe = this.getItem(position);
        if(ghe!=null){
            tvFilm.setText(ghe.getTenGhe());
        }
        return convertView;
    }
}
