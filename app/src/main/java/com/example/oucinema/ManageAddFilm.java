package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oucinema.model.Phim;

public class ManageAddFilm extends AppCompatActivity {
    DBHelper dbHelper;
    EditText etTenPhim, etMoTa, etTheLoai, etThoiLuong, etNgayPhatHanh, etDaoDien, etLinkTrailer;
    Button btnThemFilm,btnSuaFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_film);
        dbHelper = new DBHelper(ManageAddFilm.this);


        // Lấy dữ liệu từ Intent
        int itemId = getIntent().getIntExtra("item_id", -1);
        String itemName = getIntent().getStringExtra("item_name");
        String itemMoTa = getIntent().getStringExtra("item_moTa");
        String itemTheLoai = getIntent().getStringExtra("item_theLoai");
        int itemThoiLuong = getIntent().getIntExtra("item_thoiLuong", -1);
        String itemNgayPhatHanh = getIntent().getStringExtra("item_ngayPhatHanh");
        String itemDaoDien = getIntent().getStringExtra("item_daoDien");


        // Nơi gọi biến
        ImageView btnReturn = findViewById(R.id.turn_back_managefilm);
        etTenPhim = findViewById(R.id.Thongtinphimtenphim);
        etMoTa = findViewById(R.id.Thongtinphimmota);
        etTheLoai = findViewById(R.id.Thongtinphimtheloai);
        etThoiLuong = findViewById(R.id.Thongtinphimthoiluong);
        etNgayPhatHanh = findViewById(R.id.Thongtinphimngayphathanh);
        etDaoDien = findViewById(R.id.Thongtinphimdaodien);
        etLinkTrailer = findViewById(R.id.Thongtinphimlinktrailer);
        btnThemFilm = findViewById(R.id.btn_themphim);
        btnSuaFilm=findViewById(R.id.btn_suaphim);


        // Update dữ liệu
//        EditText textViewName = findViewById(R.id.Thongtinphimtenphim);
        Log.d("ID phim: ", String.valueOf(itemId));
        if (itemId == -1) {
            etTenPhim.getText().clear();
            etMoTa.getText().clear();
            etTheLoai.getText().clear();
            etThoiLuong.getText().clear();
            etNgayPhatHanh.getText().clear();
            etDaoDien.getText().clear();
            etLinkTrailer.getText().clear();
        } else {
            etTenPhim.setText(itemName);
            etMoTa.setText(itemMoTa);
            etTheLoai.setText(itemTheLoai);
            etThoiLuong.setText(String.valueOf(itemThoiLuong));
            etNgayPhatHanh.setText(itemNgayPhatHanh);
            etDaoDien.setText(itemDaoDien);
        }


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
                if (b) {
                    Toast.makeText(ManageAddFilm.this, "Thêm phim thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ManageAddFilm.this, "Thêm phim Thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Sửa thông tin phim
        btnSuaFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemId == -1) {
                    Toast.makeText(ManageAddFilm.this, "Vui lòng quay lại chọn phim cần sửa", Toast.LENGTH_LONG).show();
                }else{
                    Phim phim = new Phim();
                    phim.setTenPhim(etTenPhim.getText().toString());
                    phim.setMoTa(etMoTa.getText().toString());
                    phim.setTheLoai(etTheLoai.getText().toString());
                    phim.setThoiLuong(Integer.parseInt(etThoiLuong.getText().toString()));
                    java.sql.Date date = java.sql.Date.valueOf(etNgayPhatHanh.getText().toString());
                    phim.setNgayPhatHanh(date);
                    phim.setDaoDien(etDaoDien.getText().toString());
                    phim.setLinkTrailer(etLinkTrailer.getText().toString());
                    String idphim = String.valueOf(itemId);
                    boolean b = dbHelper.updateFilm(phim,idphim);
                    if (b) {
                        Toast.makeText(ManageAddFilm.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ManageAddFilm.this, "Cập nhật phim Thất bại", Toast.LENGTH_LONG).show();
                    }
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