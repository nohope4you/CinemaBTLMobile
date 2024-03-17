package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.oucinema.model.Ghe;

public class ManageAddSeat extends AppCompatActivity {
    DBHelper dbHelper;
    RadioButton rdThuong,rdVIP;
    EditText etTenGhe;
    Button btnTHEMGHE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_seat);
        dbHelper = new DBHelper(ManageAddSeat.this);
        //Nơi lấy intent Putextra
        int gheId = getIntent().getIntExtra("ghe_id", -1);
        String tenGheExtra = getIntent().getStringExtra("tenGhe");


        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_managefilm);
        etTenGhe = findViewById(R.id.etGhe);
        btnTHEMGHE = findViewById(R.id.btn_themghe);
        rdThuong = findViewById(R.id.radioGheThuong);
        rdVIP = findViewById(R.id.radioButtonGheVIP);

//        if(tenGheExtra.equals("")){
//            Log.d("Ghế: ",tenGheExtra.toString());
//            etTenGhe.setText(tenGheExtra.toString());
//        }
//        else{
//            etTenGhe.setText(tenGheExtra.toString());
//        }

        // Tạo Intent
        Intent intent = new Intent(this, ManageSeat.class);

        btnTHEMGHE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ghe g = new Ghe();
                String tengheString = etTenGhe.getText().toString();
                g.setTenGhe(tengheString);
                if(rdThuong.isChecked()){
                    g.setLoaiGhe(rdThuong.getText().toString());
                }
                else{
                    g.setLoaiGhe(rdVIP.getText().toString());
                }

                boolean b = dbHelper.addSeat(g);
                if(b){
                    Toast.makeText(ManageAddSeat.this,"Thêm ghế thành công",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ManageAddSeat.this,"Thêm ghế Thất bại",Toast.LENGTH_LONG).show();
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