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

import com.example.oucinema.adapter.SeatAdapter;
import com.example.oucinema.adapter.TicketAdapter;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Ve;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ManageTicket extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvTicket;
    SearchView tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_ticket);
        dbHelper = new DBHelper(ManageTicket.this);
        tk= findViewById(R.id.manage_search_ticket);
        String user_id = getIntent().getStringExtra("user_id");
        String user_name = getIntent().getStringExtra("user_name");
        if(user_id !=null)
            Log.d("test","user id from ticket "+user_id);
        else
            Log.d("test","error ");
        lvTicket = findViewById(R.id.listViewTicket);
        ArrayList<Ve> listVe = dbHelper.getTicket();

        TicketAdapter ticketAdapter = new TicketAdapter(this,R.layout.list_ticket,listVe);
        lvTicket.setAdapter(ticketAdapter);

        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MyActivity", "Before filtering: " + listVe.size() + " items");
                ticketAdapter.getFilter().filter(newText);
                Log.d("MyActivity", "After filtering: " + ticketAdapter.getCount() + " items");
                return true;
            }
        });

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
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });
        // Mở trang thêm sửa phim
        btnAddFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAddTicket.putExtra("user_name",user_name);
                intentAddTicket.putExtra("user_id",user_id);
                startActivity(intentAddTicket);
            }
        });
        lvTicket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ve v = listVe.get(i);

                int id = v.getId();
                String hoten = v.getUserID().getHoTen();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String thoiGianDat = sdf.format(v.getThoiGianDat());
                int suatID = v.getSuatID().getId();
                int cpID = v.getMaID().getId();
                int userID = v.getUserID().getId();

                Double giatien = v.getGiaTien();
                String hinhthuc = v.getHinhThuc();
                int gheID = v.getGheID().getId();
                intentAddTicket.putExtra("user_name",user_name);
                intentAddTicket.putExtra("user_id",user_id);
                intentAddTicket.putExtra("ve_id",id);
                intentAddTicket.putExtra("ve_cp",cpID);
                intentAddTicket.putExtra("ve_ngaydat",thoiGianDat);
                Log.d("ngày đặt: ", String.valueOf(gheID)+ " "+ String.valueOf(suatID) + " "+String.valueOf(id));
                intentAddTicket.putExtra("ve_suat",suatID);
                intentAddTicket.putExtra("ve_user",userID);
                intentAddTicket.putExtra("ve_gia",giatien);
                intentAddTicket.putExtra("ve_hinhthuc",hinhthuc);
                intentAddTicket.putExtra("ve_hoten",hoten);
                intentAddTicket.putExtra("ve_ghe",gheID);
                startActivity(intentAddTicket);


            }
        });

    }
}
