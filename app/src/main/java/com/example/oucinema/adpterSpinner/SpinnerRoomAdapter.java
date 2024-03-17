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
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Phong;
import com.example.oucinema.model.RapPhim;

import java.util.ArrayList;

public class SpinnerRoomAdapter extends ArrayAdapter<Phong> {


    public SpinnerRoomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Phong> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_room,parent,false);
        TextView tvFilmSelected =convertView.findViewById(R.id.tv_selected_room);
        Phong p = this.getItem(position);
        if(p!=null){
            tvFilmSelected.setText(p.getTenPhong());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room,parent,false);
        TextView tvFilm =convertView.findViewById(R.id.tv_room);
        Phong p = this.getItem(position);
        if(p!=null){
            tvFilm.setText(p.getTenPhong());
        }
        return convertView;
    }
}
