package com.example.oucinema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import  android.support.v7.widget.SearchView;
import com.example.oucinema.adapter.FilmAdapter;
import com.example.oucinema.adapter.TheaterAdapter;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ManageFilm extends AppCompatActivity {

    DBHelper dbHelper;
    ListView lvFilm;
    SearchView tk;
    BottomNavigationView btnavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_film);
        dbHelper = new DBHelper(ManageFilm.this);
        tk = findViewById(R.id.manage_search_film);
        btnavigation = findViewById(R.id.bottomNavigationView);



        Bundle bundle = getIntent().getExtras();
            String userId = bundle.getString("user_id");
            Log.d("Test", "UserID: " + userId);

        String user_name = dbHelper.getUserNAMELogin(userId);
        String user_id = getIntent().getStringExtra("user_id");
        lvFilm = findViewById(R.id.listViewFilm);
        ArrayList<Phim> listPhim = dbHelper.getPhim();

        FilmAdapter filmAdapter = new FilmAdapter(this,R.layout.list_film,listPhim);
        lvFilm.setAdapter(filmAdapter);

        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddFilm= findViewById(R.id.manage_add_film);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddFilm = new Intent(this, ManageAddFilm.class);

        //hàm search
        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MyActivity", "Before filtering: " + listPhim.size() + " items");
                filmAdapter.getFilter().filter(newText);
                Log.d("MyActivity", "After filtering: " + filmAdapter.getCount() + " items");
                return true;
            }
        });


        // Quay về trang navbar
        btnMenuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ManageFilm.this,NavBarManager.class);
                intent.putExtra("user_name", user_name);
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });

        // Lấy dữ liệu chuyển trang
        lvFilm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phim item = listPhim.get(position);

                // Lấy dữ liệu item
                int itemId = item.getId();
                String itemName = item.getTenPhim();
                String itemMoTa = item.getMoTa();
                Log.d("mô tả: ",item.getMoTa().toString());
                String itemTheLoai = item.getTheLoai();
                int itemThoiLuong = item.getThoiLuong();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String itemNgayPhatHanh = sdf.format(item.getNgayPhatHanh());
                String itemDaoDien = item.getDaoDien();
                String itemHinhAnh = item.getHinhAnh();


                // Khởi động trang khác với dữ liệu
                Intent intent = new Intent(ManageFilm.this, ManageAddFilm.class);
                intent.putExtra("item_hinhAnh",itemHinhAnh);
                intent.putExtra("user_name", user_name);
                intent.putExtra("user_id",user_id);
                intent.putExtra("item_id", itemId);
                intent.putExtra("item_name", itemName);
                intent.putExtra("item_moTa", itemMoTa);
                intent.putExtra("item_theLoai", itemTheLoai);
                intent.putExtra("item_thoiLuong", itemThoiLuong);
                intent.putExtra("item_ngayPhatHanh", itemNgayPhatHanh);
                intent.putExtra("item_daoDien", itemDaoDien);
//                intent.putExtra("item_hinhAnh", itemName);

//                intent.putExtra("item_isDelete", itemName);
//                intent.putExtra("item_userUpdate", itemName);
                startActivity(intent);
            }
        });



        // Mở trang thêm sửa phim
        btnAddFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAddFilm.putExtra("user_name", user_name);
                intentAddFilm.putExtra("user_id", user_id);
                startActivity(intentAddFilm);
            }
        });

        // Bottom Navigtaion View
        btnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_manager_Film:
                        Intent intent = new Intent(ManageFilm.this, ManageFilm.class);
                        intent.putExtra("user_name",user_name);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                        break;
                    case R.id.nav_manager_t:
                        Intent intent_ticket = new Intent(ManageFilm.this, ManageTicket.class);
                        intent_ticket.putExtra("user_name",user_name);
                        intent_ticket.putExtra("user_id",user_id);
                        startActivity(intent_ticket);
                        break;

                    case R.id.nav_manager_static:
                        Intent intent_setfilm = new Intent(ManageFilm.this, Statis.class);
                        intent_setfilm.putExtra("user_id",user_id);
                        intent_setfilm.putExtra("user_name",user_name);
                        startActivity(intent_setfilm);
                        break;

                    case R.id.nav_manager_user:
                        Intent intent_user = new Intent(ManageFilm.this, ManageUser.class);
                        intent_user.putExtra("user_id",user_id);
                        intent_user.putExtra("user_name",user_name);
                        startActivity(intent_user);

                }
                return false;
            }
        });
    }
}
