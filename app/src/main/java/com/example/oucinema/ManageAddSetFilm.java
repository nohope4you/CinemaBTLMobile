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
import com.example.oucinema.model.Suat;

public class ManageAddSetFilm extends AppCompatActivity {
    DBHelper dbHelper;
    EditText AddSetFilmNC,AddSetFilmTL,AddSetFilmGMD;
    Button btnThemSetFilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_setfilm);
        dbHelper = new DBHelper(ManageAddSetFilm.this);

        // Nơi gọi biến
        AddSetFilmNC = findViewById(R.id.Thongtinsuatngaychieu);
        AddSetFilmTL=findViewById(R.id.Thongtinsuatgiochieu);
        AddSetFilmGMD=findViewById(R.id.Thongtinsuatgia);
        btnThemSetFilm=findViewById(R.id.btn_themsuat);
        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_managefilm);
        // Tạo Intent
        Intent intent = new Intent(this, ManageSetFilm.class);

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        btnThemSetFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Suat suat = new Suat();
                java.sql.Date date = java.sql.Date.valueOf(AddSetFilmNC.getText().toString());
                suat.setNgayChieu(date);
                java.sql.Time time = java.sql.Time.valueOf(AddSetFilmTL.getText().toString());
                suat.setGioChieu(time);
                suat.setGiaMacDinh(Double.parseDouble(AddSetFilmGMD.getText().toString()));
                boolean b = dbHelper.addSetFilm(suat);
                if(b){
                    Toast.makeText(ManageAddSetFilm.this,"Thêm suất phim thành công",Toast.LENGTH_LONG).show();
                    AddSetFilmNC.getText().clear();
                    AddSetFilmTL.getText().clear();
                    AddSetFilmGMD.getText().clear();
                }
                else
                {
                    Toast.makeText(ManageAddSetFilm.this,"Thêm suất phim Thất bại !!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}