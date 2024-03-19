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

        lvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Phong p= listPhong.get(position);
                int itemId = p.getId();
                int itemRapID = p.getRapPhimID().getId();
                String itemName = p.getTenPhong();

                intentAddRoom.putExtra("room_id",itemId);
                intentAddRoom.putExtra("rap_id",itemRapID);
                Log.d("ID rạp ",String.valueOf(itemRapID));
                intentAddRoom.putExtra("room_name",itemName);
                startActivity(intentAddRoom);
            }
        });

    }
}
