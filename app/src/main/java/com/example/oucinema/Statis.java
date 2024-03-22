package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.SetFilmAdapter;
import com.example.oucinema.adapter.StatisAdapter;
import com.example.oucinema.model.Suat;
import com.example.oucinema.model.TempModel;

import java.util.ArrayList;


public class Statis extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listView,listTheater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic);
        listView = findViewById(R.id.listMovieStatis);
        listTheater = findViewById(R.id.listMovieStatisTheater);
        String user_id = getIntent().getStringExtra("user_id");
        String user_name = getIntent().getStringExtra("user_name");
        dbHelper = new DBHelper(Statis.this);
        ArrayList<TempModel> listSetFilm = dbHelper.getStatisMovie();
        ArrayList<TempModel> listRap = dbHelper.getStatisTheater();

        StatisAdapter statisAdapter = new StatisAdapter(this,R.layout.item_statis,listSetFilm);
        listView.setAdapter(statisAdapter);

        StatisAdapter statisAdapter2 = new StatisAdapter(this,R.layout.item_statis_theater,listRap);
        listTheater.setAdapter(statisAdapter2);
        ImageView btnMenuList= findViewById(R.id.menu_list);
        Intent intent = new Intent(this, NavBarManager.class);
        btnMenuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });
    }
}