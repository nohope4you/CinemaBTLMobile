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
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.User;


import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context mcontext;
    private int mResource;
    DBHelper dbHelper;
    ArrayList<User> listUser;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mResource = resource;
        dbHelper = new DBHelper(context); // Khởi tạo DBHelper
        listUser = dbHelper.getAllUser();
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<User> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // Trường hợp không có ràng buộc tìm kiếm, trả về danh sách ban đầu
                    filteredList.addAll(listUser); // listPhim chứa toàn bộ danh sách phim
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    // Lọc danh sách theo filterPattern
                    for (User item : listUser) {
                        if (item.getHoTen().toLowerCase().contains(filterPattern)) {
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
                addAll((List<User>) results.values);
                notifyDataSetChanged();
            }
        };
    }

}
