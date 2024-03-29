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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.TheaterAdapter;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.RapPhim;

import java.util.ArrayList;
import java.util.List;


public class ManageTheater extends AppCompatActivity {

    DBHelper dbHelper;
    ListView lvRap;
    SearchView tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_theater);
        dbHelper = new DBHelper(ManageTheater.this);
        tk= findViewById(R.id.manage_search_theater);
        lvRap = findViewById(R.id.listViewRapPhim);
        String user_id = getIntent().getStringExtra("user_id");
        String user_name = getIntent().getStringExtra("user_name");
        if(user_id !=null)
            Log.d("test","user id from theater "+user_id);
        else
            Log.d("test","error ");
        ArrayList<RapPhim> listRap = dbHelper.getRapPhim();

        TheaterAdapter theaterAdapter = new TheaterAdapter(this,R.layout.list_theater,listRap);
        lvRap.setAdapter(theaterAdapter);
        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MyActivity", "Before filtering: " + listRap.size() + " items");
                theaterAdapter.getFilter().filter(newText);
                Log.d("MyActivity", "After filtering: " + theaterAdapter.getCount() + " items");
                return true;
            }
        });

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
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        // Mở trang thêm sửa phim
        btnAddFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAddTheater.putExtra("user_name",user_name);
                intentAddTheater.putExtra("user_id",user_id);
                startActivity(intentAddTheater);
            }
        });
        lvRap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                RapPhim rp= listRap.get(position);
                int idr = rp.getId();
                String name = rp.getTenRap();
                String diachi = rp.getDiaChi();
                String sdt = rp.getSoDienThoaiLienHe();
                intentAddTheater.putExtra("user_name",user_name);
                intentAddTheater.putExtra("user_id",user_id);
                intentAddTheater.putExtra("theater_id",idr);
                intentAddTheater.putExtra("theater_name",name);
                intentAddTheater.putExtra("theater_diachi",diachi);
                intentAddTheater.putExtra("theater_sdt",sdt);
                startActivity(intentAddTheater);
            }
        });

        BottomNavigationView btnavigation = findViewById(R.id.bottomNavigationView);
        // Bottom Navigtaion View
        btnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_manager_Film:
                        Intent intent = new Intent(ManageTheater.this, ManageFilm.class);
                        intent.putExtra("user_name",user_name);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                        break;
                    case R.id.nav_manager_t:
                        Intent intent_ticket = new Intent(ManageTheater.this, ManageTicket.class);
                        intent_ticket.putExtra("user_name",user_name);
                        intent_ticket.putExtra("user_id",user_id);
                        startActivity(intent_ticket);
                        break;

                    case R.id.nav_manager_static:
                        Intent intent_setfilm = new Intent(ManageTheater.this, Statis.class);
                        intent_setfilm.putExtra("user_id",user_id);
                        intent_setfilm.putExtra("user_name",user_name);
                        startActivity(intent_setfilm);
                        break;

                    case R.id.nav_manager_user:
                        Intent intent_user = new Intent(ManageTheater.this, ManageUser.class);
                        intent_user.putExtra("user_id",user_id);
                        intent_user.putExtra("user_name",user_name);
                        startActivity(intent_user);

                }
                return false;
            }
        });

    }
}
