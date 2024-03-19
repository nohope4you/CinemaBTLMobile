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
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phim;


import java.util.ArrayList;
import java.util.List;

public class CouponAdapter extends ArrayAdapter<MaGiamGia> {
    DBHelper dbHelper;
    private Context mcontext;
    private int mResource;
    ArrayList<MaGiamGia> listCP;


    public CouponAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MaGiamGia> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
        dbHelper = new DBHelper(context); // Khởi tạo DBHelper
        listCP = dbHelper.getGoupon();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvmagiamgia = convertView.findViewById(R.id.textTenMaGiamGia);
        TextView tvngayhieuluc = convertView.findViewById(R.id.textThoiGianHieuLuc);
        TextView tvphantram = convertView.findViewById(R.id.textPhantram);

        tvmagiamgia.setText(getItem(position).getTenMaGiam());
        tvphantram.setText(String.valueOf(getItem(position).getPhanTramGiam()));
        tvngayhieuluc.setText(getItem(position).getThoiGianHieuLuc().toString());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<MaGiamGia> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // Trường hợp không có ràng buộc tìm kiếm, trả về danh sách ban đầu
                    filteredList.addAll(listCP); // listPhim chứa toàn bộ danh sách phim
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    // Lọc danh sách theo filterPattern
                    for (MaGiamGia item : listCP) {
                        if (item.getTenMaGiam().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((List<MaGiamGia>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}