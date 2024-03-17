package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oucinema.model.Phim;

public class ManageAddFilm extends AppCompatActivity {
    DBHelper dbHelper;
    EditText etTenPhim,etMoTa,etTheLoai,etThoiLuong,etNgayPhatHanh,etDaoDien,etLinkTrailer;
    Button btnThemFilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_film);
        dbHelper = new DBHelper(ManageAddFilm.this);

        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_managefilm);
        etTenPhim = findViewById(R.id.Thongtinphimtenphim);
        etMoTa=findViewById(R.id.Thongtinphimmota);
        etTheLoai=findViewById(R.id.Thongtinphimtheloai);
        etThoiLuong=findViewById(R.id.Thongtinphimthoiluong);
        etNgayPhatHanh = findViewById(R.id.Thongtinphimngayphathanh);
        etDaoDien = findViewById(R.id.Thongtinphimdaodien);
        etLinkTrailer = findViewById(R.id.Thongtinphimlinktrailer);
        btnThemFilm=findViewById(R.id.btn_themphim);

        // Tạo Intent
        Intent intent = new Intent(this, ManageFilm.class);

        btnThemFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phim phim = new Phim();
                phim.setTenPhim(etTenPhim.getText().toString());
                phim.setMoTa(etMoTa.getText().toString());
                phim.setTheLoai(etTheLoai.getText().toString());
                phim.setThoiLuong(Integer.parseInt(etThoiLuong.getText().toString()));
                java.sql.Date date = java.sql.Date.valueOf(etNgayPhatHanh.getText().toString());
                phim.setNgayPhatHanh(date);
                phim.setDaoDien(etDaoDien.getText().toString());
                phim.setLinkTrailer(etLinkTrailer.getText().toString());
                boolean b = dbHelper.addFilm(phim);
                if(b){
                    Toast.makeText(ManageAddFilm.this,"Thêm phim thành công",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ManageAddFilm.this,"Thêm phim Thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });
        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}