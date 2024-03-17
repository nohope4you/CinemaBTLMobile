package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oucinema.adpterSpinner.SpinnerFilmAdapter;
import com.example.oucinema.adpterSpinner.SpinnerRoomAdapter;
import com.example.oucinema.adpterSpinner.SpinnerTheaterAdapter;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Phong;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Suat;

import java.util.ArrayList;

public class ManageAddSetFilm extends AppCompatActivity {
    DBHelper dbHelper;
    EditText AddSetFilmNC,AddSetFilmTL,AddSetFilmGMD;
    Button btnThemSetFilm;
    Spinner spinFilm,spinPhong;
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
        spinFilm=findViewById(R.id.Thongtinsuattenphim);
        spinPhong=findViewById(R.id.thongtinsuattenphong);

        ArrayList<Phim> listPhim = dbHelper.getPhim();
        SpinnerFilmAdapter spinnerFilmAdapter = new SpinnerFilmAdapter(this,R.layout.item_selected_film,listPhim);
        spinFilm.setAdapter(spinnerFilmAdapter);

        ArrayList<Phong> listPhong = dbHelper.getPhong();
        SpinnerRoomAdapter spinnerRoomAdapter = new SpinnerRoomAdapter(this,R.layout.item_selected_room,listPhong);
        spinPhong.setAdapter(spinnerRoomAdapter);



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
                if(AddSetFilmNC.getText().toString().equals("")||
                        AddSetFilmTL.getText().toString().equals("")||
                        AddSetFilmGMD.getText().toString().equals(""))
                {
                    Toast.makeText(ManageAddSetFilm.this,"Điền đầy đủ thông tin !!!",Toast.LENGTH_LONG).show();

                }
                else{
                    java.sql.Date date = java.sql.Date.valueOf(AddSetFilmNC.getText().toString());
                    suat.setNgayChieu(date);
                    java.sql.Time time = java.sql.Time.valueOf(AddSetFilmTL.getText().toString());
                    suat.setGioChieu(time);
                    suat.setGiaMacDinh(Double.parseDouble(AddSetFilmGMD.getText().toString()));

                    Phim phimSelected = (Phim) spinFilm.getSelectedItem();
                    Log.d("test ",String.valueOf( phimSelected.getTheLoai()) );
                    Log.d("test ",String.valueOf( phimSelected.getId()) );
                    Log.d("test ","-------------------------" );
                    Phong phongSelected = (Phong) spinPhong.getSelectedItem();
                    int idPhong = phongSelected.getId();
                    Phong tempPhong = new Phong();
                    tempPhong.setId(idPhong);

                    Log.d("test ",String.valueOf(idPhong) );

                    suat.setPhimID(phimSelected);
                    Log.d("test ",String.valueOf(suat.getPhimID().getId()));
                    suat.setPhongID(tempPhong);


//                    boolean b = dbHelper.addSetFilm(suat);
//                    if(b){
//                        Toast.makeText(ManageAddSetFilm.this,"Thêm suất phim thành công",Toast.LENGTH_LONG).show();
//                        AddSetFilmNC.getText().clear();
//                        AddSetFilmTL.getText().clear();
//                        AddSetFilmGMD.getText().clear();
//                    }
//                    else
//                    {
//                        Toast.makeText(ManageAddSetFilm.this,"Thêm suất phim Thất bại !!!",Toast.LENGTH_LONG).show();
//                    }
                }
                }

        });
    }
}