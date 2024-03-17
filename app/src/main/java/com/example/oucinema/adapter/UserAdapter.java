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
import com.example.oucinema.model.User;


import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {

    private Context mcontext;
    private int mResource;


    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
//        TextView tvID =  convertView.findViewById(R.id.textidUser);
        TextView tvHOTEN = convertView.findViewById(R.id.txtHoTenUserlist);
        TextView tvUSERNAME = convertView.findViewById(R.id.textusernamelist);

//        tvID.setText(getItem(position).getId());
        tvHOTEN.setText(getItem(position).getHoTen());
        tvUSERNAME.setText(getItem(position).getUsername());


        return convertView;
    }
}
