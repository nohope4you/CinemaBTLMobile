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
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Role;

import java.util.ArrayList;

public class SpinnerRoleAdapter extends ArrayAdapter<Role> {


    public SpinnerRoleAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Role> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_role,parent,false);
        TextView tvFilmSelected =convertView.findViewById(R.id.tv_selected_role);
        Role r = this.getItem(position);

        tvFilmSelected.setText(r.getNameRole());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_role,parent,false);
        TextView tvFilm =convertView.findViewById(R.id.tv_role);
        Role r = this.getItem(position);
        if(r!=null){
            tvFilm.setText((r.getNameRole()));
        }
        return convertView;
    }
}
