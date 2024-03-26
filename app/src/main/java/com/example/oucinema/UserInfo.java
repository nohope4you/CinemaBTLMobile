package com.example.oucinema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oucinema.model.User;


public class UserInfo extends AppCompatActivity {
    EditText etHoTen,etSDT,etEmail;
    RadioButton rdNam, rdNu;
    Button btnXacCapNhat, btnDangXuat;
    DBHelper dbHelper;
    String userID,user_name;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        dbHelper = new DBHelper(UserInfo.this);

        // Gán biến
        etHoTen = findViewById(R.id.txthotenuser);
        etSDT = findViewById(R.id.txtsdtuser);
        etEmail =findViewById(R.id.txtemailuser);
        rdNam = findViewById(R.id.rdbuttonnam);
        rdNu = findViewById(R.id.rdbuttonnnu);
        btnXacCapNhat = findViewById(R.id.btnupdateuser);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        ///
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


        User user = new User();
        user = dbHelper.getUserByID(userID);
        if(user.getHoTen()!=null){
            etHoTen.setText(user.getHoTen());
            etSDT.setText(user.getPhoneNumber());
            etEmail.setText(user.getEmail());
            if(user.getGioiTinh().equals("Nam")){
                rdNam.setChecked(true);
            }
            else{
                rdNu.setChecked(true);
            }
        }
        //Button Xác nhận
        btnXacCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User tempUser = new User();
                tempUser.setHoTen(etHoTen.getText().toString());
                tempUser.setPhoneNumber(etSDT.getText().toString());
                tempUser.setEmail(etEmail.getText().toString());
                if(rdNam.isChecked()){
                    tempUser.setGioiTinh(rdNam.getText().toString());
                }
                else
                {

                    tempUser.setGioiTinh(rdNu.getText().toString());
                }
                String idd = String.valueOf(userID);
                Boolean b = dbHelper.updateUser1(tempUser,idd);
                if(b){
                    Toast.makeText(UserInfo.this, "Sửa thông tin thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UserInfo.this, "Sửa thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // bottomnavigationview chuyển trang
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_user_home:
                        Intent intent = new Intent(UserInfo.this, UserHome.class);
                        intent.putExtra("user_id",userID);
                        startActivity(intent);
                        break;
                    case R.id.nav_user_memories:
                        Intent intent_ticket = new Intent(UserInfo.this, UserHistory.class);
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
                        Intent intent_setfilm = new Intent(UserInfo.this, UserInfo.class);
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