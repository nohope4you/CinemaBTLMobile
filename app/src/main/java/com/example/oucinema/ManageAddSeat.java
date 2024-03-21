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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.oucinema.model.Ghe;

public class ManageAddSeat extends AppCompatActivity {
    DBHelper dbHelper;
    RadioButton rdThuong,rdVIP;
    EditText etTenGhe;
    Button btnTHEMGHE,btnSUAGHE,btnXOAGHE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_seat);
        dbHelper = new DBHelper(ManageAddSeat.this);
        String user_id = getIntent().getStringExtra("user_id");
        String user_name = getIntent().getStringExtra("user_name");
        if(user_id !=null)
            Log.d("test","user id from addseat "+user_id);
        else
            Log.d("test","error ");


        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_managefilm);
        etTenGhe = findViewById(R.id.etGhe);
        btnTHEMGHE = findViewById(R.id.btn_themghe);
        rdThuong = findViewById(R.id.radioGheThuong);
        rdVIP = findViewById(R.id.radioButtonGheVIP);
        btnSUAGHE=findViewById(R.id.btn_suaghe);
        btnXOAGHE = findViewById(R.id.btn_xoaghe);
        //Nơi lấy intent Putextra
        int gheId = getIntent().getIntExtra("ghe_id", -1);
        String tenGhe = getIntent().getStringExtra("ten_Ghe");
        String loaiGhe = getIntent().getStringExtra("loai_Ghe");



        // Tạo Intent
        Intent intent = new Intent(this, ManageSeat.class);

        // update dữ liệu từ intent
        if(gheId!=-1){
            etTenGhe.setText(tenGhe);
            if(loaiGhe.contains("VIP"))
                rdVIP.setChecked(true);
        }else{
            rdThuong.setChecked(true);
            btnXOAGHE.setEnabled(false);
            btnXOAGHE.setTextColor(Color.parseColor("#C7C8CC"));
        }

        btnTHEMGHE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ghe g = new Ghe();
                String tengheString = etTenGhe.getText().toString();
                g.setTenGhe(tengheString);
                g.setUserUpdate(Integer.parseInt(user_id));
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
        btnXOAGHE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Ghe g = new Ghe();
                    g.setId(gheId);
                    g.setUserUpdate(Integer.parseInt(user_id));
                    String idd= String.valueOf(gheId);
                    boolean b = dbHelper.deleteGhe(g,idd);

                    if(b){
                        Toast.makeText(ManageAddSeat.this,"Xoá ghế thành công",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(ManageAddSeat.this,"Xoá ghế Thất bại",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ManageAddSeat.this,e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });

        btnSUAGHE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ghe g = new Ghe();
                String tengheString = etTenGhe.getText().toString();
                g.setTenGhe(tengheString);
                g.setUserUpdate(Integer.parseInt(user_id));
//                g.setUserUpdate(Integer.parseInt(user_id));
                if(rdThuong.isChecked()){
                    g.setLoaiGhe(rdThuong.getText().toString());
                }
                else{
                    g.setLoaiGhe(rdVIP.getText().toString());
                }
                String idseat = String.valueOf(gheId);
                Log.d("id: ",idseat);
                boolean b = dbHelper.updateGhe(g,idseat);

                if(b){
                    Toast.makeText(ManageAddSeat.this,"Sửa ghế thành công",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ManageAddSeat.this,"Sửa ghế Thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });

    }
}