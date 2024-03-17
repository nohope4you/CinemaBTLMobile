package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.SetFilmAdapter;
import com.example.oucinema.adapter.UserAdapter;
import com.example.oucinema.model.Suat;
import com.example.oucinema.model.User;

import java.util.ArrayList;


public class ManageSetFilm extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvsetFilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_setfilm);
        dbHelper = new DBHelper(ManageSetFilm.this);

        lvsetFilm = findViewById(R.id.listViewsetFilm);
        ArrayList<Suat> listSetFilm = dbHelper.getSetFilm();

        SetFilmAdapter setFilmAdapter = new SetFilmAdapter(this,R.layout.list_setfilm,listSetFilm);
        lvsetFilm.setAdapter(setFilmAdapter);

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

    }
}
