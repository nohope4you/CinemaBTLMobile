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
    EditText hoTenKH,sdtKH,emailKH,username,password,repassword;
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
        repassword = (EditText) findViewById(R.id.textXacNhanMatKhauDangKy);


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
                User  u ;
                Role role = new Role(1,"User");
                String hotenKH, sdt, email,userName,passWord,repwd;
                hotenKH = hoTenKH.getText().toString();
                sdt = sdtKH.getText().toString();
                email = emailKH.getText().toString();
                userName = username.getText().toString();
                passWord = password.getText().toString();
                repwd = repassword.getText().toString();
                if(hotenKH.equals("")||sdt.equals("")||email.equals("")||userName.equals("")||passWord.equals("")){
                    Toast.makeText(RegisterCustomer.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    if(passWord.equals(repwd)){
                        if(dbHelper.checkUserExist(userName)){
                            Toast.makeText(RegisterCustomer.this,"Tài khoản đã tồn tại",Toast.LENGTH_LONG).show();
                        }
                        else {
                            u = new User(-1,hotenKH,sdt,
                                    email,"Nam",userName,passWord,
                                    role);
                            dbHelper = new DBHelper(RegisterCustomer.this);
                            boolean b = dbHelper.addUser(u);
                            Toast.makeText(RegisterCustomer.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterCustomer.this,"Xác nhận mật khẩu không khớp",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}