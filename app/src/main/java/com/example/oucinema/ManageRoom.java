package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.RoomAdapter;
import com.example.oucinema.adapter.SeatAdapter;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Phong;

import java.util.ArrayList;


public class ManageRoom extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_room);
        dbHelper = new DBHelper(ManageRoom.this);

        lvRoom = findViewById(R.id.listViewRoom);
        ArrayList<Phong> listPhong = dbHelper.getPhong();

        RoomAdapter roomAdapter = new RoomAdapter(this,R.layout.list_room,listPhong);
        lvRoom.setAdapter(roomAdapter);
        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddFilm= findViewById(R.id.manage_add_room);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddRoom = new Intent(this, ManageAddRoom.class);

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
                startActivity(intentAddRoom);
            }
        });

    }
}
