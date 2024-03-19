package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.SetFilmAdapter;
import com.example.oucinema.adapter.UserAdapter;
import com.example.oucinema.model.Suat;
import com.example.oucinema.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ManageSetFilm extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvsetFilm;
    SearchView tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_setfilm);
        dbHelper = new DBHelper(ManageSetFilm.this);
        tk= findViewById(R.id.manage_search_setfilm);


        lvsetFilm = findViewById(R.id.listViewsetFilm);
        ArrayList<Suat> listSetFilm = dbHelper.getSetFilm();

        SetFilmAdapter setFilmAdapter = new SetFilmAdapter(this,R.layout.list_setfilm,listSetFilm);
        lvsetFilm.setAdapter(setFilmAdapter);
        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MyActivity", "Before filtering: " + listSetFilm.size() + " items");
                setFilmAdapter.getFilter().filter(newText);
                Log.d("MyActivity", "After filtering: " + setFilmAdapter.getCount() + " items");
                return true;
            }
        });

        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddFilm= findViewById(R.id.manage_add_setfilm);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddSetFilm = new Intent(this, ManageAddSetFilm.class);

        // Quay về trang navbar
        btnMenuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        // Mở trang thêm sửa phim
        btnAddFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentAddSetFilm);
            }
        });
        lvsetFilm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Suat s = listSetFilm.get(i);
                int id = s.getId();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String ngayChieu = sdf.format(s.getNgayChieu());
                SimpleDateFormat sdfh = new SimpleDateFormat("hh:mm:ss");
                String gioChieu = sdfh.format(s.getGioChieu());
                double gia = s.getGiaMacDinh();
                int phimID = s.getPhimID().getId();
                int phongID = s.getPhongID().getId();

                intentAddSetFilm.putExtra("set_id",id);
                intentAddSetFilm.putExtra("set_ngay",ngayChieu);
                intentAddSetFilm.putExtra("set_gio",gioChieu);
                intentAddSetFilm.putExtra("set_gia",gia);
                intentAddSetFilm.putExtra("set_phim",phimID);
                intentAddSetFilm.putExtra("set_phong",phongID);
                startActivity(intentAddSetFilm);

            }
        });

    }
}
