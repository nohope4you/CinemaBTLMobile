package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.SeatAdapter;
import com.example.oucinema.adapter.UserAdapter;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.User;

import java.util.ArrayList;


public class ManageUser extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_user);
        dbHelper = new DBHelper(ManageUser.this);

        lvUser = findViewById(R.id.listViewUser);
        ArrayList<User> listUser = dbHelper.getAllUser();

        UserAdapter userAdapter = new UserAdapter(this,R.layout.list_user,listUser);
        lvUser.setAdapter(userAdapter);
        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddUser= findViewById(R.id.manage_add_user);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddUser = new Intent(this, ManageAddUser.class);

        // Quay về trang navbar
        btnMenuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        // Mở trang thêm sửa phim
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentAddUser);
            }
        });

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User u = listUser.get(i);
                int id = u.getId();
                String name = u.getHoTen();
                String phone = u.getPhoneNumber();
                String email = u.getEmail();
                int role = u.getRoleID().getId();
                intentAddUser.putExtra("user_id",id);
                intentAddUser.putExtra("user_name",name);
                intentAddUser.putExtra("user_phone",phone);
                intentAddUser.putExtra("user_email",email);
                intentAddUser.putExtra("user_role",role);
                intentAddUser.putExtra("user_username",u.getUsername());
                intentAddUser.putExtra("user_pwd",u.getPassword());
                startActivity(intentAddUser);


            }
        });

    }
}
