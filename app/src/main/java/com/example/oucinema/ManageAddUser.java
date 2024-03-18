package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oucinema.adpterSpinner.SpinnerRoleAdapter;
import com.example.oucinema.adpterSpinner.SpinnerTheaterAdapter;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Role;
import com.example.oucinema.model.User;

import java.util.ArrayList;

public class ManageAddUser extends AppCompatActivity {
    DBHelper dbHelper;
    Button btnSuaUser;
    EditText hoten,sdt,etemail;
    Spinner spinRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_user);
        dbHelper = new DBHelper(ManageAddUser.this);

        hoten=findViewById(R.id.thongtinuserhoten);
        sdt = findViewById(R.id.thongtinusersdt);
        etemail=findViewById(R.id.thongtinuseremail);
        btnSuaUser=findViewById(R.id.btn_suauserql);
        spinRole = findViewById(R.id.thongtinuservaitro);

        ArrayList<Role> listRole = dbHelper.getRole();
        SpinnerRoleAdapter spinnerRoleAdapter = new SpinnerRoleAdapter(this,R.layout.item_selected_role,listRole);
        spinRole.setAdapter(spinnerRoleAdapter);

        //lấy intent
        int id = getIntent().getIntExtra("user_id",-1);
        int roleid = getIntent().getIntExtra("user_role",-1);
        String name = getIntent().getStringExtra("user_name");
        String phone = getIntent().getStringExtra("user_phone");
        String email = getIntent().getStringExtra("user_email");
        if(id!=-1 && roleid!=-1){
            hoten.setText(name);
            sdt.setText(phone);
            etemail.setText(email);
            int[] arrayIdRap = new int[listRole.size()];
            for (int i = 0; i < listRole.size(); i++) {
                arrayIdRap[i] = listRole.get(i).getId();
            }

            int position = -1;
            for (int i = 0; i < listRole.size(); i++) {
                if (listRole.get(i).getId() == roleid) {
                    position = i;
                    break;
                }
            }
            if (position != -1) {
                spinRole.setSelection(position);
            }
        }else{
            hoten.getText().clear();
            sdt.getText().clear();
            etemail.getText().clear();
        }
        //Sửa
        btnSuaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = new User();
                String idd = String.valueOf(id);
                String nameuser = hoten.getText().toString();
                String emailuser = etemail.getText().toString();
                String phoneuser = sdt.getText().toString();
                if(nameuser.equals("")|| emailuser.equals("")|| phoneuser.equals("")){
                    Toast.makeText(ManageAddUser.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    u.setHoTen(nameuser);
                    u.setUsername(getIntent().getStringExtra("user_username"));
                    u.setPassword(getIntent().getStringExtra("user_pwd"));
                    u.setEmail(emailuser);
                    u.setPhoneNumber(phoneuser);
                    Role r = (Role) spinRole.getSelectedItem();
                    u.setRoleID(r);
                    boolean b = dbHelper.updateUser(u,idd);
                    if(b){
                        Toast.makeText(ManageAddUser.this,"Sửa user thành công",Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Toast.makeText(ManageAddUser.this,"Sửa user Thất bại",Toast.LENGTH_LONG).show();
                    }
                }
                Role selectedROLE = (Role) spinRole.getSelectedItem();


            }
        });


        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_managefilm);
        // Tạo Intent
        Intent intent = new Intent(this, ManageUser.class);

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}