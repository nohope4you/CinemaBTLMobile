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
import android.widget.Filter;
import android.widget.TextView;

import com.example.oucinema.DBHelper;
import com.example.oucinema.R;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Suat;
import com.example.oucinema.model.TempModel;
import com.example.oucinema.model.User;


import java.util.ArrayList;
import java.util.List;

public class StatisAdapter extends ArrayAdapter<TempModel> {

    private Context mcontext;
    private int mResource;
    DBHelper dbHelper;
    ArrayList<TempModel> listTemp;

    public StatisAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TempModel> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
        dbHelper = new DBHelper(context); // Khởi tạo DBHelper
        listTemp = dbHelper.getStatisMovie();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView nameMovie =  convertView.findViewById(R.id.nameMovie);
        TextView number = convertView.findViewById(R.id.number);
        nameMovie.setText(getItem(position).getNameMovie());
        number.setText(getItem(position).getNumber());


        return convertView;
    }

}