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
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Phong;


import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends ArrayAdapter<Phong> {

    private Context mcontext;
    private int mResource;
    DBHelper dbHelper;
    ArrayList<Phong> listPhong;


    public RoomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Phong> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
        dbHelper = new DBHelper(context); // Khởi tạo DBHelper
        listPhong = dbHelper.getPhong();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvID = convertView.findViewById(R.id.textidphong);
        TextView tvTENPHONG = convertView.findViewById(R.id.texttenphong);
        TextView tvTENRAP = convertView.findViewById(R.id.texttenrap);

        tvID.setText(String.valueOf(getItem(position).getId()));
        tvTENPHONG.setText(getItem(position).getTenPhong());
//        tvTENRAP.setText(getItem(position).getRapPhimID().getTenRap());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Phong> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // Trường hợp không có ràng buộc tìm kiếm, trả về danh sách ban đầu
                    filteredList.addAll(listPhong); // listPhim chứa toàn bộ danh sách phim
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    // Lọc danh sách theo filterPattern
                    for (Phong item : listPhong) {
                        if (item.getTenPhong().toLowerCase().contains(filterPattern)) {
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
                addAll((List<Phong>) results.values);
                notifyDataSetChanged();
            }
        };
    }

}
