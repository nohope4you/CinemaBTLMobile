package com.example.oucinema;

import android.content.Intent;
import android.os.Parcelable;
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

import com.example.oucinema.adapter.FilmAdapter;
import com.example.oucinema.adapter.SeatAdapter;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Phim;

import java.util.ArrayList;


public class ManageSeat extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvSeat;
    SearchView tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_seat);
        dbHelper = new DBHelper(ManageSeat.this);
        String user_id = getIntent().getStringExtra("user_id");
        String user_name = getIntent().getStringExtra("user_name");
        if(user_id !=null)
            Log.d("test","user id from seat "+user_id);
        else
            Log.d("test","error ");
        tk = findViewById(R.id.manage_search_seat);

        lvSeat = findViewById(R.id.listViewSeat);
        ArrayList<Ghe> listGhe = dbHelper.getGhe();

        SeatAdapter seatAdapter = new SeatAdapter(this,R.layout.list_seat,listGhe);
        lvSeat.setAdapter(seatAdapter);

        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MyActivity", "Before filtering: " + listGhe.size() + " items");
                seatAdapter.getFilter().filter(newText);
                Log.d("MyActivity", "After filtering: " + seatAdapter.getCount() + " items");
                return true;
            }
        });
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
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });
        // Mở trang thêm sửa phim
        btnAddFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("test","user id from seat "+user_id);
                intentAddSeat.putExtra("user_name",user_name);
                intentAddSeat.putExtra("user_id",user_id);
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
                intentAddSeat.putExtra("user_name",user_name);
                intentAddSeat.putExtra("user_id",user_id);
                intentAddSeat.putExtra("ghe_id",itemId);
                intentAddSeat.putExtra("user_id",user_id);
                intentAddSeat.putExtra("ten_Ghe",itemName);
                intentAddSeat.putExtra("loai_Ghe",itemLoai);
                startActivity(intentAddSeat);

            }
        });

        BottomNavigationView btnavigation = findViewById(R.id.bottomNavigationView);
        // Bottom Navigtaion View
        btnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_manager_Film:
                        Intent intent = new Intent(ManageSeat.this, ManageFilm.class);
                        intent.putExtra("user_name",user_name);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                        break;
                    case R.id.nav_manager_t:
                        Intent intent_ticket = new Intent(ManageSeat.this, ManageTicket.class);
                        intent_ticket.putExtra("user_name",user_name);
                        intent_ticket.putExtra("user_id",user_id);
                        startActivity(intent_ticket);
                        break;

                    case R.id.nav_manager_static:
                        Intent intent_setfilm = new Intent(ManageSeat.this, Statis.class);
                        intent_setfilm.putExtra("user_id",user_id);
                        intent_setfilm.putExtra("user_name",user_name);
                        startActivity(intent_setfilm);
                        break;

                    case R.id.nav_manager_user:
                        Intent intent_user = new Intent(ManageSeat.this, ManageUser.class);
                        intent_user.putExtra("user_id",user_id);
                        intent_user.putExtra("user_name",user_name);
                        startActivity(intent_user);

                }
                return false;
            }
        });

    }
}
