package com.example.oucinema;

import android.content.Intent;
import android.graphics.Color;
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
    Button btnThemSetFilm,btnUpdateSet,btnDeleteSet;
    Spinner spinFilm,spinPhong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_setfilm);
        dbHelper = new DBHelper(ManageAddSetFilm.this);
        String user_id = getIntent().getStringExtra("user_id");
        String user_name = getIntent().getStringExtra("user_name");
        if(user_id !=null)
            Log.d("test","user id from addsetfilm "+user_id);
        else
            Log.d("test","error ");
        // Nơi gọi biến
        AddSetFilmNC = findViewById(R.id.Thongtinsuatngaychieu);
        AddSetFilmTL=findViewById(R.id.Thongtinsuatgiochieu);
        AddSetFilmGMD=findViewById(R.id.Thongtinsuatgia);
        btnThemSetFilm=findViewById(R.id.btn_themsuat);
        spinFilm=findViewById(R.id.Thongtinsuattenphim);
        spinPhong=findViewById(R.id.thongtinsuattenphong);
        btnUpdateSet=findViewById(R.id.btn_suasuat);
        btnDeleteSet = findViewById(R.id.btn_xoasuat);
        // lấy intent
        int id = getIntent().getIntExtra("set_id",-1);
        String ngayChieu = getIntent().getStringExtra("set_ngay");
        String gioChieu = getIntent().getStringExtra("set_gio");
        Double gia = getIntent().getDoubleExtra("set_gia",-1);
        int phimID =getIntent().getIntExtra("set_phim",-1);
        int phongID = getIntent().getIntExtra("set_phong",-1);

        ArrayList<Phim> listPhim = dbHelper.getPhim();
        SpinnerFilmAdapter spinnerFilmAdapter = new SpinnerFilmAdapter(this,R.layout.item_selected_film,listPhim);
        spinFilm.setAdapter(spinnerFilmAdapter);

        ArrayList<Phong> listPhong = dbHelper.getPhong();
        SpinnerRoomAdapter spinnerRoomAdapter = new SpinnerRoomAdapter(this,R.layout.item_selected_room,listPhong);
        spinPhong.setAdapter(spinnerRoomAdapter);

        if(id!=-1 && phimID!=-1 && phongID!=-1){
            AddSetFilmNC.setText(ngayChieu);
            AddSetFilmTL.setText(gioChieu);
            AddSetFilmGMD.setText(String.valueOf(gia));

            int[] arrayIdRap = new int[listPhim.size()];
            for (int i = 0; i < listPhim.size(); i++) {
                arrayIdRap[i] = listPhim.get(i).getId();
            }

            int position = -1;
            for (int i = 0; i < listPhim.size(); i++) {
                if (listPhim.get(i).getId() == phimID) {
                    position = i;
                    break;
                }
            }
            if (position != -1) {
                spinFilm.setSelection(position);
            }
//            -----------------------------
            int[] arrayIdPhong = new int[listPhong.size()];
            for (int i = 0; i < listPhong.size(); i++) {
                arrayIdPhong[i] = listPhong.get(i).getId();
            }
            int position2 = -1;
            for (int i = 0; i < listPhong.size(); i++) {
                if (listPhong.get(i).getId() == phongID) {
                    position2 = i;
                    break;
                }
            }
            if (position2 != -1) {
                spinPhong.setSelection(position2);
            }
        }else{
            AddSetFilmGMD.getText().clear();
            AddSetFilmTL.getText().clear();
            AddSetFilmNC.getText().clear();
            btnDeleteSet.setEnabled(false);
            btnDeleteSet.setTextColor(Color.parseColor("#C7C8CC"));
        }



        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_managefilm);
        // Tạo Intent
        Intent intent = new Intent(this, ManageSetFilm.class);

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });
        //sửa
        btnUpdateSet.setOnClickListener(new View.OnClickListener() {
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
                    suat.setUserUpdate(Integer.parseInt(user_id));

                    Phim phimSelected = (Phim) spinFilm.getSelectedItem();
                    Log.d("test ",String.valueOf( phimSelected.getTheLoai()) );
                    Log.d("test ",String.valueOf( phimSelected.getId()) );
                    Log.d("test ","-------------------------" );
                    Phong phongSelected = (Phong) spinPhong.getSelectedItem();
                    int idPhong = phongSelected.getId();
                    Phong tempPhong = new Phong();
                    tempPhong.setId(idPhong);

//                    Log.d("test ",String.valueOf(idPhong) );

                    suat.setPhimID(phimSelected);
//                    Log.d("test ",String.valueOf(suat.getPhimID().getId()));
                    suat.setPhongID(tempPhong);
                    String idd=String.valueOf(id);


                    boolean b = dbHelper.updateSuat(suat,idd);
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
            }
        });
        btnDeleteSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Suat suat = new Suat();
                suat.setId(id);
                String idd = String.valueOf(id);
                suat.setUserUpdate(Integer.parseInt(user_id));
                boolean b = dbHelper.deleteSuat(suat,idd);
                if(b){
                    Toast.makeText(ManageAddSetFilm.this,"Xoá suất phim thành công",Toast.LENGTH_LONG).show();
                    AddSetFilmNC.getText().clear();
                    AddSetFilmTL.getText().clear();
                    AddSetFilmGMD.getText().clear();
                }
                else
                {
                    Toast.makeText(ManageAddSetFilm.this,"Xoá suất phim Thất bại !!!",Toast.LENGTH_LONG).show();
                }
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

//                    Log.d("test ",String.valueOf(idPhong) );

                    suat.setPhimID(phimSelected);
//                    Log.d("test ",String.valueOf(suat.getPhimID().getId()));
                    suat.setPhongID(tempPhong);
                    suat.setUserUpdate(Integer.parseInt(user_id));

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
                }

        });
    }
}