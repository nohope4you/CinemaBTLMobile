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

import com.example.oucinema.adapter.CouponAdapter;
import com.example.oucinema.model.MaGiamGia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ManageCoupon extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvCoupon;
    SearchView tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_coupon);
        dbHelper = new DBHelper(ManageCoupon.this);
        tk = findViewById(R.id.manage_search_coupon);

        String user_id = getIntent().getStringExtra("user_id");
        String user_name = getIntent().getStringExtra("user_name");
        if(user_id !=null)
            Log.d("test","user id from coupon "+user_id);
        else
            Log.d("test","error ");

        lvCoupon = findViewById(R.id.listViewCoupon);
        ArrayList<MaGiamGia> listCoupon = dbHelper.getGoupon();

        CouponAdapter coupnAdapter = new CouponAdapter(this,R.layout.list_coupon,listCoupon);
        lvCoupon.setAdapter(coupnAdapter);

        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MyActivity", "Before filtering: " + listCoupon.size() + " items");
                coupnAdapter.getFilter().filter(newText);
                Log.d("MyActivity", "After filtering: " + coupnAdapter.getCount() + " items");
                return true;
            }
        });

        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddCP= findViewById(R.id.manage_add_coupon);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddCoupon = new Intent(this, ManageAddCoupon.class);

        // Quay về trang navbar
        btnMenuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("test","user id from coupon "+user_id);
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });
        // Mở trang thêm sửa phim
        btnAddCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAddCoupon.putExtra("user_name",user_name);
                intentAddCoupon.putExtra("user_id",user_id);
                startActivity(intentAddCoupon);
            }
        });

        lvCoupon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MaGiamGia item =listCoupon.get(i);
                int id = item.getId();
                String name = item.getTenMaGiam();
                int phantram = item.getPhanTramGiam();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String itemHieuLuc = sdf.format(item.getThoiGianHieuLuc());
                Log.d("ngày: ",itemHieuLuc);
                intentAddCoupon.putExtra("user_name",user_name);
                intentAddCoupon.putExtra("user_id",user_id);
                intentAddCoupon.putExtra("coupon_id",id);
                intentAddCoupon.putExtra("coupon_name",name);
                intentAddCoupon.putExtra("coupon_phantram",phantram);
                intentAddCoupon.putExtra("coupon_hieuluc",itemHieuLuc);
                startActivity(intentAddCoupon);
            }
        });

        BottomNavigationView btnavigation = findViewById(R.id.bottomNavigationView);
        // Bottom Navigtaion View
        btnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_manager_Film:
                        Intent intent = new Intent(ManageCoupon.this, ManageFilm.class);
                        intent.putExtra("user_name",user_name);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                        break;
                    case R.id.nav_manager_t:
                        Intent intent_ticket = new Intent(ManageCoupon.this, ManageTicket.class);
                        intent_ticket.putExtra("user_name",user_name);
                        intent_ticket.putExtra("user_id",user_id);
                        startActivity(intent_ticket);
                        break;

                    case R.id.nav_manager_static:
                        Intent intent_setfilm = new Intent(ManageCoupon.this, Statis.class);
                        intent_setfilm.putExtra("user_id",user_id);
                        intent_setfilm.putExtra("user_name",user_name);
                        startActivity(intent_setfilm);
                        break;

                    case R.id.nav_manager_user:
                        Intent intent_user = new Intent(ManageCoupon.this, ManageUser.class);
                        intent_user.putExtra("user_id",user_id);
                        intent_user.putExtra("user_name",user_name);
                        startActivity(intent_user);

                }
                return false;
            }
        });

    }
}
