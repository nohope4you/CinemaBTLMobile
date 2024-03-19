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
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Suat;

import java.util.ArrayList;
import java.util.List;

public class TheaterAdapter extends ArrayAdapter<RapPhim> {

    private Context mcontext;
    private int mResource;
    DBHelper dbHelper;
    ArrayList<RapPhim> listRap;


    public TheaterAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RapPhim> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
        dbHelper = new DBHelper(context); // Khởi tạo DBHelper
        listRap = dbHelper.getRapPhim();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvTenRap = convertView.findViewById(R.id.textViewTenRap);
        TextView tvDiaChi = convertView.findViewById(R.id.textViewDiaChiRap);
        TextView tvSDT = convertView.findViewById(R.id.textViewsdt);

        tvTenRap.setText(getItem(position).getTenRap());
        tvDiaChi.setText(getItem(position).getDiaChi());
        tvSDT.setText(getItem(position).getSoDienThoaiLienHe());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<RapPhim> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // Trường hợp không có ràng buộc tìm kiếm, trả về danh sách ban đầu
                    filteredList.addAll(listRap); // listPhim chứa toàn bộ danh sách phim
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    // Lọc danh sách theo filterPattern
                    for (RapPhim item : listRap) {
                        if (item.getTenRap().toLowerCase().contains(filterPattern)) {
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
                addAll((List<RapPhim>) results.values);
                notifyDataSetChanged();
            }
        };
    }

}
