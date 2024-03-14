package com.example.oucinema;

import android.app.role.RoleManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oucinema.model.Role;
import com.example.oucinema.model.User;

public class RegisterCustomer extends AppCompatActivity {

    Button btnRegist;
    EditText hoTenKH,sdtKH,emailKH,username,password;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_customer);
        dbHelper = new DBHelper(RegisterCustomer.this);

        // Nơi gọi biến
        ImageView btnReturnDangNhap= findViewById(R.id.btnquaylaidangnhap);
        btnRegist = (Button) findViewById(R.id.btnXacNhanDangKy);
        hoTenKH = (EditText) findViewById(R.id.textHoTenKhachHangDangKy);
        sdtKH = (EditText) findViewById(R.id.textSDTDangKy);
        emailKH =(EditText) findViewById(R.id.textEmail);
        username = (EditText)findViewById(R.id.textTaiKhoanDangKy);
        password =(EditText)  findViewById(R.id.textMatKhauDangKy);


        // Tạo Intent
        Intent intent = new Intent(this, MainActivity.class);

        // Quay về trang chủ
        btnReturnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                User  u ;
//                Role role = new Role(1,"User");
//
//
//
//                   u = new User(-1,hoTenKH.getText().toString(),sdtKH.getText().toString(),emailKH.getText().toString(),"Nam",username.getText().toString(),password.getText().toString(),
//                           role);
//                dbHelper = new DBHelper(RegisterCustomer.this);
//                boolean b = dbHelper.addUser(hoTenKH.getText().toString(),sdtKH.getText().toString(),
//                        emailKH.getText().toString(),"Nam",username.getText().toString(),password.getText().toString(),role);
//                    Toast.makeText(RegisterCustomer.this,u.toString(),Toast.LENGTH_LONG).show();
//
//


            }
        });

    }
}