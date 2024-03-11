package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);


//        // Nơi gọi biến
//        Button btnRegister_Customer= findViewById(R.id.btndangky);
//        Button btnLogin = findViewById(R.id.btndangnhap);
//
//        // Hàm chuyển trang đăng ký
//        btnRegister_Customer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,RegisterCustomer.class);
//                startActivity(intent);
//            }
//        });
//        // Hàm chuyển trang đăng nhập
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,ManageFilm.class);
//                startActivity(intent);
//            }
//        });
    }


}