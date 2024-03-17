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
import com.example.oucinema.model.Ve;


import java.util.ArrayList;

public class TicketAdapter extends ArrayAdapter<Ve> {

    private Context mcontext;
    private int mResource;


    public TicketAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Ve> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvUSERID =  convertView.findViewById(R.id.textuseridticketlist);
        TextView tvGHE = convertView.findViewById(R.id.textgheuserticketlist);
        TextView tvTIEN = convertView.findViewById(R.id.texttienticketlist);
        TextView tvSUAT = convertView.findViewById(R.id.textsuatticketlist);
        TextView tvTIME = convertView.findViewById(R.id.textthoigianticketlist);
        TextView tvHINHTHUC = convertView.findViewById(R.id.texthinhthucticketlist);

        tvUSERID.setText("Khách hàng: "+getItem(position).getUserID().getHoTen());
        tvGHE.setText("Ghế: "+getItem(position).getGheID().getTenGhe());
        tvTIEN.setText("Giá tiền: "+String.valueOf(getItem(position).getGiaTien()));
        tvSUAT.setText("Ngày chiếu: "+getItem(position).getSuatID().getNgayChieu().toString());
        tvTIME.setText("Ngày đặt: "+getItem(position).getThoiGianDat().toString());
        tvHINHTHUC.setText("Hình thức: "+getItem(position).getHinhThuc());



        return convertView;
    }
}
