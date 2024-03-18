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
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Role;
import com.example.oucinema.model.User;

import java.util.ArrayList;

public class SpinnerCouponAdapter extends ArrayAdapter<MaGiamGia> {


    public SpinnerCouponAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MaGiamGia> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_coupon,parent,false);
        TextView tvFilmSelected =convertView.findViewById(R.id.tv_selected_cp);
        MaGiamGia m = this.getItem(position);

        tvFilmSelected.setText(m.getTenMaGiam());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cp,parent,false);
        TextView tvFilm =convertView.findViewById(R.id.tv_cp);
        MaGiamGia m = this.getItem(position);
        if(m!=null){
            tvFilm.setText(m.getTenMaGiam());
        }
        return convertView;
    }
}
