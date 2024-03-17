package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.RapPhim;

import java.util.ArrayList;

public class ManageAddTheater extends AppCompatActivity {
    ListView listViewRapPhim;
    DBHelper dbHelper;
    EditText AddTenRap, AddDiaChiRap, AddSDTRap;
    Button btnThemTheater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_theater);
        dbHelper = new DBHelper(ManageAddTheater.this);

        // Nơi gọi biến
        AddTenRap = findViewById(R.id.Thongtinraptenrap);
        AddDiaChiRap=findViewById(R.id.thongtinrapdiachi);
        AddSDTRap=findViewById(R.id.thongtinrapsdt);
        btnThemTheater=findViewById(R.id.btn_themrap);


        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_managefilm);
        // Tạo Intent
        Intent intent = new Intent(this, ManageTheater.class);

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        // Thêm mã rạp
        btnThemTheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(AddTenRap.getText().toString().equals("")||AddDiaChiRap.getText().toString().equals("")||
                            AddSDTRap.getText().toString().equals("")){
                        Toast.makeText(ManageAddTheater.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_LONG).show();
                    }
                    else{
                        RapPhim theater = new RapPhim();
                        theater.setTenRap(AddTenRap.getText().toString());
                        theater.setDiaChi(AddDiaChiRap.getText().toString());
                        theater.setSoDienThoaiLienHe(AddSDTRap.getText().toString());
                        boolean b = dbHelper.addTheater(theater);
                        if(b){
                            Toast.makeText(ManageAddTheater.this,"Thêm rạp phim thành công",Toast.LENGTH_LONG).show();
                            AddTenRap.getText().clear();
                            AddDiaChiRap.getText().clear();
                            AddSDTRap.getText().clear();
                        }
                        else
                        {
                            Toast.makeText(ManageAddTheater.this,"Thêm rạp phim Thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                catch (Exception e){
                    Toast.makeText(ManageAddTheater.this,"Có lỗi xảy ra",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}