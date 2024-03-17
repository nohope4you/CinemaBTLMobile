package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.TheaterAdapter;
import com.example.oucinema.model.RapPhim;

import java.util.ArrayList;
import java.util.List;


public class ManageTheater extends AppCompatActivity {

    DBHelper dbHelper;
    ListView lvRap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_theater);
        dbHelper = new DBHelper(ManageTheater.this);

        lvRap = findViewById(R.id.listViewRapPhim);
        ArrayList<RapPhim> listRap = dbHelper.getRapPhim();

        TheaterAdapter theaterAdapter = new TheaterAdapter(this,R.layout.list_theater,listRap);
        lvRap.setAdapter(theaterAdapter);


        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddFilm= findViewById(R.id.manage_add_theater);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddTheater = new Intent(this, ManageAddTheater.class);

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
                startActivity(intentAddTheater);
            }
        });

    }
}
