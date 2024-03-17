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

import com.example.oucinema.adapter.TheaterAdapter;
import com.example.oucinema.adpterSpinner.SpinnerTheaterAdapter;
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phong;
import com.example.oucinema.model.RapPhim;

import java.util.ArrayList;

public class ManageAddRoom extends AppCompatActivity {
    DBHelper dbHelper;
    EditText AddTenPhong;
    Button btnThemRoom;
    Spinner spinTheater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_room);
        dbHelper = new DBHelper(ManageAddRoom.this);

        // Nơi gọi biến
        AddTenPhong = findViewById(R.id.Thongtinphongtenphong);
        btnThemRoom=findViewById(R.id.btnthongtinphongthem);
        spinTheater=findViewById(R.id.Thongtinphongmarap);
        ArrayList<RapPhim> listRap = dbHelper.getRapPhim();
        SpinnerTheaterAdapter spinnerTheaterAdapter = new SpinnerTheaterAdapter(this,R.layout.item_selected_theater,listRap);
        spinTheater.setAdapter(spinnerTheaterAdapter);


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

        // Thêm mã giảm giá
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