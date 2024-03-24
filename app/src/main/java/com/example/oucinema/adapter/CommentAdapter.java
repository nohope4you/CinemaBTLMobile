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
import com.example.oucinema.ManageFilm;
import com.example.oucinema.R;
import com.example.oucinema.model.DanhGia;
import com.example.oucinema.model.Phim;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Handler;


import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter<DanhGia> {

    private Context mcontext;
    private int mResource;

    public CommentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DanhGia> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        TextView tvUser = convertView.findViewById(R.id.textTenUser);
        TextView tvBinhluan = convertView.findViewById(R.id.textBinhLuan);
        TextView tvSosao = convertView.findViewById(R.id.textSoSao);

        tvUser.setText(getItem(position).getNguoiDanhGia());
        tvBinhluan.setText(getItem(position).getDanhGia());
        tvSosao.setText(getItem(position).getRating().toString());

        return convertView;
    }

}