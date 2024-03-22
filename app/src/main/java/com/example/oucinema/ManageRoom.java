package com.example.oucinema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oucinema.adapter.RoomAdapter;
import com.example.oucinema.adapter.SeatAdapter;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Phong;

import java.util.ArrayList;


public class ManageRoom extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvRoom;
    SearchView tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_room);
        dbHelper = new DBHelper(ManageRoom.this);
        tk= findViewById(R.id.manage_search_room);
        lvRoom = findViewById(R.id.listViewRoom);

        ArrayList<Phong> listPhong = dbHelper.getPhong();
        RoomAdapter roomAdapter = new RoomAdapter(this,R.layout.list_room,listPhong);
        lvRoom.setAdapter(roomAdapter);
        String user_name = getIntent().getStringExtra("user_name");
        String user_id = getIntent().getStringExtra("user_id");
        if(user_id !=null)
        Log.d("test","user id from room "+user_id);
        else
            Log.d("test","error ");

        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MyActivity", "Before filtering: " + listPhong.size() + " items");
                roomAdapter.getFilter().filter(newText);
                Log.d("MyActivity", "After filtering: " + roomAdapter.getCount() + " items");
                return true;
            }
        });
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
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        // Mở trang thêm sửa phim
        btnAddFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAddRoom.putExtra("user_name",user_name);
                intentAddRoom.putExtra("user_id",user_id);
                startActivity(intentAddRoom);
            }
        });

        lvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Phong p= listPhong.get(position);
                int itemId = p.getId();
                int itemRapID = p.getRapPhimID().getId();
                String itemName = p.getTenPhong();
                intentAddRoom.putExtra("user_name",user_name);
                intentAddRoom.putExtra("user_id",user_id);
                intentAddRoom.putExtra("room_id",itemId);
                intentAddRoom.putExtra("rap_id",itemRapID);
                Log.d("ID rạp ",String.valueOf(itemRapID));
                intentAddRoom.putExtra("room_name",itemName);
                startActivity(intentAddRoom);
            }
        });

        BottomNavigationView btnavigation = findViewById(R.id.bottomNavigationView);
        // Bottom Navigtaion View
        btnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_manager_Film:
                        Intent intent = new Intent(ManageRoom.this, ManageFilm.class);
                        intent.putExtra("user_name",user_name);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                        break;
                    case R.id.nav_manager_t:
                        Intent intent_ticket = new Intent(ManageRoom.this, ManageTicket.class);
                        intent_ticket.putExtra("user_name",user_name);
                        intent_ticket.putExtra("user_id",user_id);
                        startActivity(intent_ticket);
                        break;

                    case R.id.nav_manager_static:
                        Intent intent_setfilm = new Intent(ManageRoom.this, Statis.class);
                        intent_setfilm.putExtra("user_id",user_id);
                        intent_setfilm.putExtra("user_name",user_name);
                        startActivity(intent_setfilm);
                        break;

                    case R.id.nav_manager_user:
                        Intent intent_user = new Intent(ManageRoom.this, ManageUser.class);
                        intent_user.putExtra("user_id",user_id);
                        intent_user.putExtra("user_name",user_name);
                        startActivity(intent_user);

                }
                return false;
            }
        });

    }
}
