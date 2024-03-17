package com.example.oucinema;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oucinema.model.User;

public class MainActivity extends AppCompatActivity {


    DBHelper dbHelper;
    EditText etusername,etpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(MainActivity.this);

        // Nơi gọi biến
        Button btnRegister_Customer= findViewById(R.id.btndangky);
        Button btnLogin = findViewById(R.id.btndangnhap);
        etusername= findViewById(R.id.textusername);
        etpwd= findViewById(R.id.textpassword);
//        btnTest = findViewById(R.id.btnTest);

        // Hàm chuyển trang đăng ký
        btnRegister_Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterCustomer.class);
                startActivity(intent);
            }
        });
        // Hàm chuyển trang đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username,pwd;
                username = etusername.getText().toString();
                pwd = etpwd.getText().toString();
                if(username.equals("")||pwd.equals("")){
                    Toast.makeText(MainActivity.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    if(dbHelper.userLogin(username,pwd)){
                        if(dbHelper.checkRoleUser(username,pwd)){
                            String userid = dbHelper.getUserIDLogin(username,pwd);
                            Intent intent = new Intent(MainActivity.this,ManageFilm.class);
                            intent.putExtra("user_id", userid);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(MainActivity.this,UserHome.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

    }


}