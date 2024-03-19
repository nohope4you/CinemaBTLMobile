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

import com.example.oucinema.adapter.TheaterAdapter;
import com.example.oucinema.adpterSpinner.SpinnerTheaterAdapter;
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phong;
import com.example.oucinema.model.RapPhim;

import java.util.ArrayList;

public class ManageAddRoom extends AppCompatActivity {
    DBHelper dbHelper;
    EditText AddTenPhong;
    Button btnThemRoom,btnSuaRoom,btnDeletePhong;
    Spinner spinTheater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_room);
        dbHelper = new DBHelper(ManageAddRoom.this);

        // Nơi gọi biến
        AddTenPhong = findViewById(R.id.Thongtinphongtenphong);
        btnThemRoom=findViewById(R.id.btnthongtinphongthem);
        btnSuaRoom = findViewById(R.id.btnthongtinphongsua);
        btnDeletePhong= findViewById(R.id.btnthongtinphongxoa);

        spinTheater=findViewById(R.id.Thongtinphongmarap);
        ArrayList<RapPhim> listRap = dbHelper.getRapPhim();
        SpinnerTheaterAdapter spinnerTheaterAdapter = new SpinnerTheaterAdapter(this,R.layout.item_selected_theater,listRap);
        spinTheater.setAdapter(spinnerTheaterAdapter);

        //Lấy biến intent
        int idRoom = getIntent().getIntExtra("room_id",-1);
        int idRap = getIntent().getIntExtra("rap_id",-1);
        String nameRoom = getIntent().getStringExtra("room_name");

        // update dữ liệu từ intent
        if(idRoom!=-1 && idRap!=-1){
            AddTenPhong.setText(nameRoom);


            int[] arrayIdRap = new int[listRap.size()];
            for (int i = 0; i < listRap.size(); i++) {
                arrayIdRap[i] = listRap.get(i).getId();
            }

            int position = -1;
            for (int i = 0; i < listRap.size(); i++) {
                if (listRap.get(i).getId() == idRap) {
                    position = i;
                    break;
                }
            }
            if (position != -1) {
                spinTheater.setSelection(position);
            }

        }else{
            AddTenPhong.getText().clear();
            btnDeletePhong.setEnabled(false);
            btnDeletePhong.setTextColor(Color.parseColor("#C7C8CC"));

        }


        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_manageroom);
        // Tạo Intent
        Intent intent = new Intent(this, ManageRoom.class);

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


        //Sửa phòng
        btnSuaRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phong room = new Phong();

                room.setTenPhong(AddTenPhong.getText().toString());

                RapPhim selectedRap = (RapPhim) spinTheater.getSelectedItem();
                int id = selectedRap.getId();
                RapPhim tempRap = new RapPhim();
                tempRap.setId(id);
                String idd=String.valueOf(idRoom);

//                Log.d("test: ",String.valueOf(selectedRap.getId()));
                if(AddTenPhong.getText().toString().equals("")){
                    Toast.makeText(ManageAddRoom.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    if(selectedRap!=null){
                        room.setRapPhimID(tempRap);
                        boolean b = dbHelper.updatePhong(room,idd);
                        if(b){
                            Toast.makeText(ManageAddRoom.this,"Sửa phòng thành công",Toast.LENGTH_LONG).show();
                            AddTenPhong.getText().clear();
                        }
                        else
                        {
                            Toast.makeText(ManageAddRoom.this,"Sửa phòng Thất bại",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(ManageAddRoom.this,"Vui lòng chọn rạp phim",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        //Xoá
        btnDeletePhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phong phong = new Phong();
                phong.setId(idRoom);
                String idd= String.valueOf(idRoom);
                boolean b = dbHelper.deletePhong(phong,idd);
                if(b){
                    Toast.makeText(ManageAddRoom.this,"Xoá phòng thành công",Toast.LENGTH_LONG).show();
                    AddTenPhong.getText().clear();
                }
                else
                {
                    Toast.makeText(ManageAddRoom.this,"Xoá phòng Thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });
        // Thêm phòng
        btnThemRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phong room = new Phong();

                room.setTenPhong(AddTenPhong.getText().toString());

                RapPhim selectedRap = (RapPhim) spinTheater.getSelectedItem();
                int id = selectedRap.getId();
                RapPhim tempRap = new RapPhim();
                tempRap.setId(id);

//                Log.d("test: ",String.valueOf(selectedRap.getId()));
                if(AddTenPhong.getText().toString().equals("")){
                    Toast.makeText(ManageAddRoom.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    if(selectedRap!=null){
                        room.setRapPhimID(tempRap);
                        boolean b = dbHelper.addRoom(room);
                        if(b){
                            Toast.makeText(ManageAddRoom.this,"Thêm phòng thành công",Toast.LENGTH_LONG).show();
                            AddTenPhong.getText().clear();
                        }
                        else
                        {
                            Toast.makeText(ManageAddRoom.this,"Thêm phòng Thất bại",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(ManageAddRoom.this,"Vui lòng chọn rạp phim",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

    }
}