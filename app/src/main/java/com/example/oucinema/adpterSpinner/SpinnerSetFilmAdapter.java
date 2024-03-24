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
import com.example.oucinema.model.Suat;

import java.util.ArrayList;

public class SpinnerSetFilmAdapter extends ArrayAdapter<Suat> {


    public SpinnerSetFilmAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Suat> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_setfilm_user,parent,false);
        TextView tvFilmUserSelectedNgay =convertView.findViewById(R.id.tv_setfilm_ngaychieu_selected);
        TextView tvFilmUserSelectedGio =convertView.findViewById(R.id.tv_setfilm_giochieu);
        Suat p = this.getItem(position);
        if(p!=null){
            tvFilmUserSelectedNgay.setText(String.valueOf(p.getNgayChieu()));
            tvFilmUserSelectedGio.setText(String.valueOf(p.getGioChieu()));
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setfilm_user,parent,false);
        TextView tvNgay =convertView.findViewById(R.id.tv_setfilm_ngaychieu);
        TextView tvGio = convertView.findViewById(R.id.tv_setfilm_giochieu);

        Suat p = this.getItem(position);
        if(p!=null){
            tvNgay.setText(String.valueOf(p.getNgayChieu()));
            tvGio.setText(String.valueOf(p.getGioChieu()));
        }
        return convertView;
    }
}
