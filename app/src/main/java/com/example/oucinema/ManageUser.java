package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
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
    SearchView tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_user);
        dbHelper = new DBHelper(ManageUser.this);
        tk= findViewById(R.id.manage_search_user);
        lvUser = findViewById(R.id.listViewUser);

        ArrayList<User> listUser = dbHelper.getAllUser();

        UserAdapter userAdapter = new UserAdapter(this,R.layout.list_user,listUser);
        lvUser.setAdapter(userAdapter);

        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MyActivity", "Before filtering: " + listUser.size() + " items");
                userAdapter.getFilter().filter(newText);
                Log.d("MyActivity", "After filtering: " + userAdapter.getCount() + " items");
                return true;
            }
        });
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
