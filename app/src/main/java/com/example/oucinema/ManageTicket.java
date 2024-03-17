package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.SeatAdapter;
import com.example.oucinema.adapter.TicketAdapter;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Ve;

import java.util.ArrayList;


public class ManageTicket extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_ticket);
        dbHelper = new DBHelper(ManageTicket.this);

        lvTicket = findViewById(R.id.listViewTicket);
        ArrayList<Ve> listVe = dbHelper.getTicket();

        TicketAdapter ticketAdapter = new TicketAdapter(this,R.layout.list_ticket,listVe);
        lvTicket.setAdapter(ticketAdapter);

        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddFilm= findViewById(R.id.manage_add_ticket);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddTicket = new Intent(this, ManageAddTicket.class);

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
                startActivity(intentAddTicket);
            }
        });

    }
}
