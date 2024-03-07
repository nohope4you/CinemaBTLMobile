package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RegisterCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_customer);

        // Nơi gọi biến
        ImageView btnReturnDangNhap= findViewById(R.id.btnquaylaidangnhap);
        // Tạo Intent
        Intent intent = new Intent(this, MainActivity.class);

        // Quay về trang chủ
        btnReturnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}