package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.FilmAdapter;
import com.example.oucinema.adapter.SeatAdapter;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Phim;

import java.util.ArrayList;


public class ManageSeat extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvSeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_seat);
        dbHelper = new DBHelper(ManageSeat.this);

        lvSeat = findViewById(R.id.listViewSeat);
        ArrayList<Ghe> listGhe = dbHelper.getGhe();

        SeatAdapter seatAdapter = new SeatAdapter(this,R.layout.list_seat,listGhe);
        lvSeat.setAdapter(seatAdapter);
        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddFilm= findViewById(R.id.manage_add_seat);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddSeat = new Intent(this, ManageAddSeat.class);

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
                startActivity(intentAddSeat);
            }
        });

        lvSeat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ghe gheSelection= listGhe.get(position);
                int itemId = gheSelection.getId();
                String itemName = gheSelection.getTenGhe();
                String itemLoai = gheSelection.getLoaiGhe();


                intentAddSeat.putExtra("ghe_id",itemId);
                intentAddSeat.putExtra("ten_Ghe",itemName);
                intentAddSeat.putExtra("loai_Ghe",itemLoai);
                startActivity(intentAddSeat);

            }
        });

    }
}
