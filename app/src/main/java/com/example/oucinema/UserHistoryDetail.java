package com.example.oucinema;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class UserHistoryDetail extends AppCompatActivity {
    String userID,user_name;
    DBHelper dbHelper;
    private BottomNavigationView bottomNavigationView;
    TextView tenRap,tenPhong,tenGhe,giaTien,ngayChieu,gioChieu;
//    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_history_detail);
        dbHelper = new DBHelper(UserHistoryDetail.this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("user_id")) {
            String userId = bundle.getString("user_id");
            String username = bundle.getString("user_name");
            Log.d("Test", "UserID: " + userId);
            userID = userId;
            user_name = username;
        } else {
            userID = "0";
        }
        // Lấy biến intent
        String user_tenphim = getIntent().getStringExtra("user_tenphim");
        String item_hinhAnh = getIntent().getStringExtra("item_hinhAnh");
        String item_id = getIntent().getStringExtra("item_id");
        String item_tenghe = getIntent().getStringExtra("item_tenghe");
        String item_tenrap = getIntent().getStringExtra("item_tenrap");
        String item_tenphong = getIntent().getStringExtra("item_tenphong");
        Double item_giatien = getIntent().getDoubleExtra("item_giatien",-1);
        String item_ngaychieu = getIntent().getStringExtra("item_ngaychieu");
        String item_giochieu = getIntent().getStringExtra("item_giochieu");

        //Khai báo biến
        tenRap = findViewById(R.id.txthistorydetailcinemaname);
        tenPhong= findViewById(R.id.txthistorydetailroomname);
        tenGhe = findViewById(R.id.txthistorydetailseatname);
        giaTien = findViewById(R.id.txthistorydetailprice);
        ngayChieu=findViewById(R.id.txthistorydetaildate);
        gioChieu= findViewById(R.id.txthistorydetailtime);
        ImageView btnReturn = findViewById(R.id.btnturnbackhistory);
//        imgView = findViewById(R.id.imageView20);

        //Gán biến
        tenRap.setText(item_tenrap);
        tenPhong.setText(item_tenphong);
        tenGhe.setText(item_tenghe);
        giaTien.setText(String.valueOf(item_giatien));
        ngayChieu.setText(item_ngaychieu);
        gioChieu.setText(item_giochieu);
        File file = new File(getFilesDir(), item_hinhAnh);
        Drawable drawable = Drawable.createFromPath(file.getAbsolutePath());
        ImageView imageView = findViewById(R.id.imageView20);
        imageView.setImageDrawable(drawable);

        Intent intent = new Intent(this, UserHistory.class);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("user_id",userID);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });
        // bottomnavigationview chuyển trang
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_user_home:
                        Intent intent = new Intent(UserHistoryDetail.this, UserHome.class);
                        intent.putExtra("user_id",userID);
                        startActivity(intent);
                        break;
                    case R.id.nav_user_memories:
                        Intent intent_ticket = new Intent(UserHistoryDetail.this, UserHistory.class);
                        intent_ticket.putExtra("user_id",userID);
                        intent_ticket.putExtra("user_name",user_name);
                        startActivity(intent_ticket);
                        break;
//                    case R.id.nav_user_cart:
//                        Intent intent_seat = new Intent(UserHome.this, ManageSeat.class);
////                        intent_seat.putExtra("user_id",user_id);
////                        intent_seat.putExtra("user_name",user_name);
//                        startActivity(intent_seat);
//                        break;
                    case R.id.nav_user_info:
                        Intent intent_setfilm = new Intent(UserHistoryDetail.this, UserInfo.class);
                        intent_setfilm.putExtra("user_id",userID);
                        intent_setfilm.putExtra("user_name",user_name);
                        startActivity(intent_setfilm);
                        break;
                }
                return false;
            }

        });
    }

}