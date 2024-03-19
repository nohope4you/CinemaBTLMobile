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
import com.example.oucinema.model.Phim;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Handler;


import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Phim> {
    DBHelper dbHelper;
    private Context mcontext;
    private int mResource;
    ArrayList<Phim> listPhim;


    public FilmAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Phim> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
        dbHelper = new DBHelper(context); // Khởi tạo DBHelper
        listPhim = dbHelper.getPhim(); // Lấy danh sách phim từ DBHelper
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvTenPhim = convertView.findViewById(R.id.textTenPhim);
        TextView tvTheLoai = convertView.findViewById(R.id.textTheLoai);
        TextView tvNgayPhatHanh = convertView.findViewById(R.id.textngayphathanh);

        tvTenPhim.setText(getItem(position).getTenPhim());
        tvTheLoai.setText(getItem(position).getTheLoai());
        tvNgayPhatHanh.setText(getItem(position).getNgayPhatHanh().toString());

        return convertView;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Phim> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // Trường hợp không có ràng buộc tìm kiếm, trả về danh sách ban đầu
                    filteredList.addAll(listPhim); // listPhim chứa toàn bộ danh sách phim
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    // Lọc danh sách theo filterPattern
                    for (Phim item : listPhim) {
                        if (item.getTenPhim().toLowerCase().contains(filterPattern)) {
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
                addAll((List<Phim>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
