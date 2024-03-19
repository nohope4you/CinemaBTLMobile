package com.example.oucinema;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phim;

public class ManageAddCoupon extends AppCompatActivity {
    DBHelper dbHelper;
    EditText AddTenMG, AddPTMG, AddTGHL;
    Button btnThemCoupon,btnUpdateCoupon,btnDeleteCP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_coupon);
        dbHelper = new DBHelper(ManageAddCoupon.this);

        // Nơi gọi biến
        AddTenMG = findViewById(R.id.Thongtinmagiamten);
        AddPTMG=findViewById(R.id.Thongtinmagiamphantram);
        AddTGHL=findViewById(R.id.thongtinmagiamhieuluc);
        btnThemCoupon=findViewById(R.id.btnthongtinmagiamthem);
        btnUpdateCoupon=findViewById(R.id.btnthongtinmagiamsua);
        btnDeleteCP = findViewById(R.id.btnthongtinmagiamxoa);

        // lấy intent
        int id = getIntent().getIntExtra("coupon_id",-1);
        String name = getIntent().getStringExtra("coupon_name");
        int phantram = getIntent().getIntExtra("coupon_phantram",-1);
        String hieuluc = getIntent().getStringExtra("coupon_hieuluc");

        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_managecoupon);
        // Tạo Intent
        Intent intent = new Intent(this, ManageCoupon.class);


        //update dữ liệu intent
        if(id!=-1){
            AddTenMG.setText(name);
            AddPTMG.setText(String.valueOf(phantram));
            AddTGHL.setText(hieuluc);
        }
        else{
            AddTGHL.getText().clear();
            AddTenMG.getText().clear();
            AddPTMG.getText().clear();
            btnDeleteCP.setEnabled(false);
            btnDeleteCP.setTextColor(Color.parseColor("#C7C8CC"));
        }

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        //sửa mgg
        btnUpdateCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(AddTenMG.getText().toString().equals("")||AddPTMG.getText().toString().equals("")||AddTGHL.getText().toString().equals("")){
                        Toast.makeText(ManageAddCoupon.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_LONG).show();
                    }
                    else{
                        MaGiamGia mgg = new MaGiamGia();
                        mgg.setTenMaGiam(AddTenMG.getText().toString());
                        mgg.setPhanTramGiam(Integer.parseInt(AddPTMG.getText().toString()));
                        java.sql.Date date = java.sql.Date.valueOf(AddTGHL.getText().toString());
                        mgg.setThoiGianHieuLuc(date);
                        String idd = String.valueOf(id);
                        boolean b = dbHelper.updateMgg(mgg,idd);
                        if(b){
                            Toast.makeText(ManageAddCoupon.this,"Sửa mã giảm giá thành công",Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(ManageAddCoupon.this,"Sửa mã giảm giá Thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(ManageAddCoupon.this,"Lỗi định dạng yyyy-MM-dd",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDeleteCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    MaGiamGia mgg = new MaGiamGia();
                    mgg.setId(id);
                    String idd=String.valueOf(id);
                    boolean b = dbHelper.deleteMgg(mgg,idd);
                    if(b){
                        Toast.makeText(ManageAddCoupon.this,"Xoá mã giảm giá thành công",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(ManageAddCoupon.this,"Xoá mã giảm giá thất bại",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ManageAddCoupon.this,"Chưa chọn mã giảm giá để xoá",Toast.LENGTH_LONG).show();
                }

            }
        });

        // Thêm mã giảm giá
        btnThemCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try{
                if(AddTenMG.getText().toString().equals("")||AddPTMG.getText().toString().equals("")||AddTGHL.getText().toString().equals("")){
                    Toast.makeText(ManageAddCoupon.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }
                else{
                    MaGiamGia mgg = new MaGiamGia();
                    mgg.setTenMaGiam(AddTenMG.getText().toString());
                    mgg.setPhanTramGiam(Integer.parseInt(AddPTMG.getText().toString()));
                    java.sql.Date date = java.sql.Date.valueOf(AddTGHL.getText().toString());
                    mgg.setThoiGianHieuLuc(date);
                    boolean b = dbHelper.addCoupon(mgg);
                    if(b){
                        Toast.makeText(ManageAddCoupon.this,"Thêm mã giảm giá thành công",Toast.LENGTH_LONG).show();
                        AddTenMG.getText().clear();
                        AddPTMG.getText().clear();
                        AddTGHL.getText().clear();
                    }
                    else
                    {
                        Toast.makeText(ManageAddCoupon.this,"Thêm mã giảm giá Thất bại",Toast.LENGTH_LONG).show();
                    }
                }
            }catch (Exception e){
                Toast.makeText(ManageAddCoupon.this,"Lỗi định dạng yyyy-MM-dd",Toast.LENGTH_LONG).show();
            }


            }
        });

    }
}