package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.oucinema.adpterSpinner.SpinnerCouponAdapter;
import com.example.oucinema.adpterSpinner.SpinnerFilmAdapter;
import com.example.oucinema.adpterSpinner.SpinnerSetAdapter;
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Suat;
import com.example.oucinema.model.Ve;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ManageAddTicket extends AppCompatActivity {
    DBHelper dbHelper;
    Spinner spinSet, spinCP;
    EditText etTime,etHoTen,etGhe,etHinhThuc,etTien;
    Button btnSuaVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_ticket);
        dbHelper = new DBHelper(ManageAddTicket.this);
        etTime=findViewById(R.id.Thongtinvegiatien);
        etHoTen=findViewById(R.id.Thongtinvekhachang);
        etGhe=findViewById(R.id.Thongtinveghe);
        etHinhThuc=findViewById(R.id.Thongtinvehinhthuc);
        etTien=findViewById(R.id.Thongtinvegiatien);
        spinSet=findViewById(R.id.Thongtinvesuatchieu);
        spinCP =findViewById(R.id.Thongtinvemagiamgia);
        btnSuaVe=findViewById(R.id.btn_suave);
        ArrayList<Suat> listSet = dbHelper.getSetFilm();
        SpinnerSetAdapter spinnerSetAdapter = new SpinnerSetAdapter(this,R.layout.item_selected_set,listSet);
        spinSet.setAdapter(spinnerSetAdapter);

        ArrayList<MaGiamGia> listCp = dbHelper.getGoupon();
        SpinnerCouponAdapter spinnerCouponAdapter = new SpinnerCouponAdapter(this,R.layout.item_selected_coupon,listCp);
        spinCP.setAdapter(spinnerCouponAdapter);
        //lấy intent
        int id = getIntent().getIntExtra("ve_id",-1);
        int cpid = getIntent().getIntExtra("ve_cp",-1);
        String gheName = getIntent().getStringExtra("ve_ghe");
        int suat = getIntent().getIntExtra("ve_suat",-1);
        String time = getIntent().getStringExtra("ve_ngaydat");
        String hinhthuc = getIntent().getStringExtra("ve_hinhthuc");
        String hoten = getIntent().getStringExtra("ve_hoten");
        Double gia = getIntent().getDoubleExtra("ve_gia",-1);
        if(id!=-1 && suat!=-1){
            etTime.setText(time);
            etHoTen.setText(hoten);
            etHinhThuc.setText(hinhthuc);
            etGhe.setText(gheName);
            etTien.setText(String.valueOf(gia));

            int[] arrayIdRap = new int[listSet.size()];
            for (int i = 0; i < listSet.size(); i++) {
                arrayIdRap[i] = listSet.get(i).getId();
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
                if (listCp.get(i).getId() == suat) {
                    position2 = i;
                    break;
                }
            }
            if (position2 != -1) {
                spinCP.setSelection(position2);
            }

        }else{
            etTime.getText().clear();
            etHoTen.getText().clear();
            etHinhThuc.getText().clear();
            etGhe.getText().clear();
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
                startActivity(intent);
            }
        });

//        btnSuaVe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Ve ve = new Ve();
//                String idd = String.valueOf(id);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////                String ngayDat = sdf.format(time);
//
//            }
//        });
    }
}