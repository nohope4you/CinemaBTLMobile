package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.CouponAdapter;
import com.example.oucinema.model.MaGiamGia;

import java.util.ArrayList;


public class ManageCoupon extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvCoupon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_coupon);
        dbHelper = new DBHelper(ManageCoupon.this);


        lvCoupon = findViewById(R.id.listViewCoupon);
        ArrayList<MaGiamGia> listCoupon = dbHelper.getGoupon();

        CouponAdapter coupnAdapter = new CouponAdapter(this,R.layout.list_coupon,listCoupon);
        lvCoupon.setAdapter(coupnAdapter);

        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddFilm= findViewById(R.id.manage_add_coupon);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddCoupon = new Intent(this, ManageAddCoupon.class);

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
                startActivity(intentAddCoupon);
            }
        });

    }
}
