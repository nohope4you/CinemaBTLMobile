package com.example.oucinema.adpterSpinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.oucinema.DBHelper;
import com.example.oucinema.R;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Ve;

import java.util.ArrayList;

public class SpinnerTicketAdapter extends ArrayAdapter<Ve> {
DBHelper dbHelper;

    public SpinnerTicketAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Ve> objects) {
        super(context, resource, objects);
        dbHelper = new DBHelper(context); // Khởi tạo DBHelper
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_ve,parent,false);
        TextView tvFilmSelected =convertView.findViewById(R.id.tv_ve_ngaychieu_selected);
        TextView tvghe =convertView.findViewById(R.id.tv_ve_ghe);
        Ve p = this.getItem(position);
        if(p!=null){

            tvFilmSelected.setText(String.valueOf(dbHelper.getNgayChieuBySuatId(String.valueOf(p.getSuatID().getId()))));
            tvghe.setText(p.getGheID().getTenGhe());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ve,parent,false);
        TextView tvFilm =convertView.findViewById(R.id.tv_ve_ngaychieu);
        TextView tvghe =convertView.findViewById(R.id.tv_ve_ghe);
        Ve p = this.getItem(position);
        if(p!=null){
            tvFilm.setText(String.valueOf(dbHelper.getNgayChieuBySuatId(String.valueOf(p.getSuatID().getId()))));
//            tvFilm.setText(dbHelper.getNgayChieuBySuatId(String.valueOf(p.getId())));
            tvghe.setText(p.getGheID().getTenGhe());
        }
        return convertView;
    }
}
