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

import com.example.oucinema.adpterSpinner.SpinnerCouponAdapter;
import com.example.oucinema.adpterSpinner.SpinnerFilmAdapter;
import com.example.oucinema.adpterSpinner.SpinnerSeatAdapter;
import com.example.oucinema.adpterSpinner.SpinnerSetAdapter;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Suat;
import com.example.oucinema.model.User;
import com.example.oucinema.model.Ve;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ManageAddTicket extends AppCompatActivity {
    DBHelper dbHelper;
    Spinner spinSet, spinCP,spinSeat;
    EditText etTime,etHoTen,etGhe,etHinhThuc,etTien;
    Button btnSuaVe,btnXoaVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_ticket);
        dbHelper = new DBHelper(ManageAddTicket.this);
        String user_name = getIntent().getStringExtra("user_name");
        String user_id = getIntent().getStringExtra("user_id");
        if(user_id !=null)
            Log.d("test","user id from addticket "+user_id);
        else
            Log.d("test","error ");

        etTime=findViewById(R.id.thongtinvethoigiandat);
        etHoTen=findViewById(R.id.Thongtinvekhachang);
        spinSeat=findViewById(R.id.Thongtinveghe);
        etHinhThuc=findViewById(R.id.Thongtinvehinhthuc);
        etTien=findViewById(R.id.Thongtinvegiatien);
        spinSet=findViewById(R.id.Thongtinvesuatchieu);
        spinCP =findViewById(R.id.Thongtinvemagiamgia);
        btnSuaVe=findViewById(R.id.btn_suave);
        btnXoaVe = findViewById(R.id.btn_xoave);

        ArrayList<Suat> listSet = dbHelper.getSetFilm();
        SpinnerSetAdapter spinnerSetAdapter = new SpinnerSetAdapter(this,R.layout.item_selected_set,listSet);
        spinSet.setAdapter(spinnerSetAdapter);

        ArrayList<MaGiamGia> listCp = dbHelper.getGoupon();
        SpinnerCouponAdapter spinnerCouponAdapter = new SpinnerCouponAdapter(this,R.layout.item_selected_coupon,listCp);
        spinCP.setAdapter(spinnerCouponAdapter);

        ArrayList<Ghe> listGhe = dbHelper.getGhe();
        SpinnerSeatAdapter spinnerSeatAdapter = new SpinnerSeatAdapter(this,R.layout.item_selected_seat,listGhe);
        spinSeat.setAdapter(spinnerSeatAdapter);
        //lấy intent
        int id = getIntent().getIntExtra("ve_id",-1);
        int cpid = getIntent().getIntExtra("ve_cp",-1);
        int ghe = getIntent().getIntExtra("ve_ghe",-1);
        int suat = getIntent().getIntExtra("ve_suat",-1);
        int user = getIntent().getIntExtra("ve_user",-1);
        String time = getIntent().getStringExtra("ve_ngaydat");
//        Log.d("ngày đặt",time);
        String hinhthuc = getIntent().getStringExtra("ve_hinhthuc");
        String hoten = getIntent().getStringExtra("ve_hoten");
        Double gia = getIntent().getDoubleExtra("ve_gia",-1);
        if(id!=-1 && suat!=-1){
            etTime.setText(time);
            etHoTen.setText(hoten);
            etHinhThuc.setText(hinhthuc);

            Log.d("ghế id ",String.valueOf(ghe));
            etTien.setText(String.valueOf(gia));


            int[] arrayIdSuat = new int[listSet.size()];
            for (int i = 0; i < listSet.size(); i++) {
                arrayIdSuat[i] = listSet.get(i).getId();
            }

            int position = -1;
            for (int i = 0; i < listSet.size(); i++) {
                if (listSet.get(i).getId() == suat) {
                    position = i;
                    break;
                }
            }
            if (position != -1) {
                spinSet.setSelection(position);
            }

            int[] arrayIdCP = new int[listCp.size()];
            for (int i = 0; i < listCp.size(); i++) {
                arrayIdCP[i] = listCp.get(i).getId();
            }

            int position2 = -1;
            for (int i = 0; i < listCp.size(); i++) {
                if (listCp.get(i).getId() == cpid) {
                    position2 = i;
                    break;
                }
            }
            if (position2 != -1) {
                spinCP.setSelection(position2);
            }

            int[] arrayIdGhe = new int[listGhe.size()];
            for (int i = 0; i < listGhe.size(); i++) {
                arrayIdGhe[i] = listGhe.get(i).getId();
            }

            int position3 = -1;
            for (int i = 0; i < listGhe.size(); i++) {
                if (listGhe.get(i).getId() == ghe) {
                    position3 = i;
                    break;
                }
            }
            if (position3 != -1) {
                spinSeat.setSelection(position3);
            }

        }else{
            etTime.getText().clear();
            etHoTen.getText().clear();
            etHinhThuc.getText().clear();
            etTien.getText().clear();
        }



        // Nơi gọi biến
        ImageView btnReturn= findViewById(R.id.turn_back_managefilm);
        // Tạo Intent
        Intent intent = new Intent(this, ManageTicket.class);

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });
        btnXoaVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ve ve = new Ve();
                String idd = String.valueOf(id);
                ve.setId(id);
                ve.setUserUpdate(Integer.parseInt(user_id));
                boolean b = dbHelper.deleteVe(ve,idd);
                if(b){
                    Toast.makeText(ManageAddTicket.this,"Xoá vé thành công",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ManageAddTicket.this,"Xoá vé Thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });


        btnSuaVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ve ve = new Ve();
                String idd = String.valueOf(id);
                java.sql.Date date = java.sql.Date.valueOf(etTime.getText().toString());
                Ghe selectedGhe = (Ghe) spinSeat.getSelectedItem();
                Suat selectedSuat = (Suat) spinSet.getSelectedItem();
                MaGiamGia selectedMGG = (MaGiamGia) spinCP.getSelectedItem();
                User u = new User();
                u.setId(user);
                Double gia = Double.parseDouble(etTien.getText().toString());
                String hinhthuc = etHinhThuc.getText().toString();
                ve.setId(id);
                ve.setThoiGianDat(date);
                ve.setGheID(selectedGhe);
                ve.setSuatID(selectedSuat);
                ve.setMaID(selectedMGG);
                ve.setGiaTien(gia);
                ve.setHinhThuc(hinhthuc);
                ve.setUserID(u);
                boolean b = dbHelper.updateVe(ve,idd);
                if(b){
                    Toast.makeText(ManageAddTicket.this,"Sửa vé thành công",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ManageAddTicket.this,"Sửa vé Thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}