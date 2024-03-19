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
import com.example.oucinema.model.User;


import java.util.ArrayList;
import java.util.List;

public class SetFilmAdapter extends ArrayAdapter<Suat> {

    private Context mcontext;
    private int mResource;
    DBHelper dbHelper;
    ArrayList<Suat> listSuat;

    public SetFilmAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Suat> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
        dbHelper = new DBHelper(context); // Khởi tạo DBHelper
        listSuat = dbHelper.getSetFilm();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView tvPHIM =  convertView.findViewById(R.id.textphimsetfilm);
        TextView tvPHONG = convertView.findViewById(R.id.textphongsetfilm);
        TextView tvGIA = convertView.findViewById(R.id.textgiasetfilm);

        tvPHIM.setText(getItem(position).getPhimID().getTenPhim());
        tvPHONG.setText(getItem(position).getPhongID().getTenPhong());
        tvGIA.setText(getItem(position).getGiaMacDinh().toString());


        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Suat> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // Trường hợp không có ràng buộc tìm kiếm, trả về danh sách ban đầu
                    filteredList.addAll(listSuat); // listPhim chứa toàn bộ danh sách phim
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    // Lọc danh sách theo filterPattern
                    for (Suat item : listSuat) {
                        if (item.getTenSuat().toLowerCase().contains(filterPattern)) {
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
                addAll((List<Suat>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
