package com.example.oucinema;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phong;

public class ManageAddRoom extends AppCompatActivity {
    DBHelper dbHelper;
    EditText AddTenPhong;
    Button btnThemRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_room);
        dbHelper = new DBHelper(ManageAddRoom.this);

        // Nơi gọi biến
        AddTenPhong = findViewById(R.id.Thongtinphongtenphong);
        btnThemRoom=findViewById(R.id.btnthongtinphongthem);

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
                boolean b = dbHelper.addRoom(room);
                if(b){
                    Toast.makeText(ManageAddRoom.this,"Thêm mã giảm giá thành công",Toast.LENGTH_LONG).show();
                    AddTenPhong.getText().clear();
                }
                else
                {
                    Toast.makeText(ManageAddRoom.this,"Thêm mã giảm giá Thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}