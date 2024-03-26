package com.example.oucinema;

import android.Manifest;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oucinema.adapter.CommentAdapter;
import com.example.oucinema.model.DanhGia;
import com.example.oucinema.model.Ghe;

import java.util.ArrayList;


public class UserProcessOder extends AppCompatActivity {

    DBHelper dbHelper;
    private Double tiensuat;
    private String tongTienn;
    String userID,user_name;
    TextView soGhe,tongTien;
    Button Xacnhan;
    CheckBox A1,B1,C1,D1,A2,B2,C2,D2,A3,B3,C3,D3,A4,B4,C4,D4,
            E1,F1,H1,G1,E2,F2,G2,H2,E3,F3,G3,H3,E4,F4,G4,H4;

    private BottomNavigationView bottomNavigationView;
    private static final int REQUEST_CODE_PERMISSIONS = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_processorder);

        dbHelper = new DBHelper(UserProcessOder.this);

//        dbHelper.getVetamthoi();
        // nơi gọi id từ các layout
        A1 = findViewById(R.id.seatA1);
        A2 = findViewById(R.id.seatA2);
        A3 = findViewById(R.id.seatA3);
        A4 = findViewById(R.id.seatA4);

        B1 = findViewById(R.id.seatB1);
        B2 = findViewById(R.id.seatB2);
        B3 = findViewById(R.id.seatB3);
        B4 = findViewById(R.id.seatB4);

        C1 = findViewById(R.id.seatC1);
        C2 = findViewById(R.id.seatC2);
        C3 = findViewById(R.id.seatC3);
        C4 = findViewById(R.id.seatC4);

        D1 = findViewById(R.id.seatD1);
        D2 = findViewById(R.id.seatD2);
        D3 = findViewById(R.id.seatD3);
        D4 = findViewById(R.id.seatD4);

        E1 = findViewById(R.id.seatE1);
        E2 = findViewById(R.id.seatE2);
        E3 = findViewById(R.id.seatE3);
        E4 = findViewById(R.id.seatE4);

        F1 = findViewById(R.id.seatF1);
        F2 = findViewById(R.id.seatF2);
        F3 = findViewById(R.id.seatF3);
        F4 = findViewById(R.id.seatF4);

        G1 = findViewById(R.id.seatG1);
        G2 = findViewById(R.id.seatG2);
        G3 = findViewById(R.id.seatG3);
        G4 = findViewById(R.id.seatG4);

        H1 = findViewById(R.id.seatH1);
        H2 = findViewById(R.id.seatH2);
        H3 = findViewById(R.id.seatH3);
        H4 = findViewById(R.id.seatH4);

        soGhe = findViewById(R.id.soluongghe);
        tongTien = findViewById(R.id.tongtien);
        Xacnhan = findViewById(R.id.btn_tienhanhdat);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        ImageView btnReturn = findViewById(R.id.btnturnbackfilmdetail);


        // nhận intent từ các trang khác và khai báo intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("user_id")) {
            String userId = bundle.getString("user_id");
            String username = bundle.getString("user_name");
            Log.d("Test", "UserID: " + userId);
            userID = userId;
            user_name = username;
        } else {
            userID = "0";
        }

        int rapid = getIntent().getIntExtra("rap_id", -1);
        int suatid = getIntent().getIntExtra("suat_id", -1);
        int itemId = getIntent().getIntExtra("item_id", -1);
        String itemName = getIntent().getStringExtra("item_name");
        String itemMoTa = getIntent().getStringExtra("item_moTa");
        int itemThoiLuong = getIntent().getIntExtra("item_thoiLuong", -1);
        String itemNgayPhatHanh = getIntent().getStringExtra("item_ngayPhatHanh");
        String itemHinhAnh = getIntent().getStringExtra("item_hinhAnh");

        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSIONS);
        Log.d("taggggggggg", String.valueOf(itemId));

        // set up dữ liệu cho trang
        ArrayList<Ghe> listDanhGia = dbHelper.getGheSuat(String.valueOf(suatid));
            for (Ghe items : listDanhGia)
        {


            if(items.getTenGhe().equals("A1"))
            {
                A1.setChecked(true);
                A1.setEnabled(false);
                A1.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("A2"))
            {
                A2.setChecked(true);
                A2.setEnabled(false);
                A2.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("A3"))
            {
                A3.setChecked(true);
                A3.setEnabled(false);
                A3.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("A4"))
            {
                A4.setChecked(true);
                A4.setEnabled(false);
                A4.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("B1"))
            {
                B1.setChecked(true);
                B1.setEnabled(false);
                B1.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("B2"))
            {
                B2.setChecked(true);
                B2.setEnabled(false);
                B2.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("B3"))
            {
                B3.setChecked(true);
                B3.setEnabled(false);
                B3.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("B4"))
            {
                B4.setChecked(true);
                B4.setEnabled(false);
                B4.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("C1"))
            {
                C1.setChecked(true);
                C1.setEnabled(false);
                C1.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("C2"))
            {
                C2.setChecked(true);
                C2.setEnabled(false);
                C2.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("C3"))
            {
                C3.setChecked(true);
                C3.setEnabled(false);
                C3.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("C4"))
            {
                C4.setChecked(true);
                C4.setEnabled(false);
                C4.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("D1"))
            {
                D1.setChecked(true);
                D1.setEnabled(false);
                D1.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("D2"))
            {
                D2.setChecked(true);
                D2.setEnabled(false);
                D2.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("D3"))
            {
                D3.setChecked(true);
                D3.setEnabled(false);
                D3.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("D4"))
            {
                D4.setChecked(true);
                D4.setEnabled(false);
                D4.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("E1"))
            {
                E1.setChecked(true);
                E1.setEnabled(false);
                E1.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("E2"))
            {
                E2.setChecked(true);
                E2.setEnabled(false);
                E2.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("E3"))
            {
                E3.setChecked(true);
                E3.setEnabled(false);
                E3.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("E4"))
            {
                E4.setChecked(true);
                E4.setEnabled(false);
                E4.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("F1"))
            {
                F1.setChecked(true);
                F1.setEnabled(false);
                F1.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("F2"))
            {
                F2.setChecked(true);
                F2.setEnabled(false);
                F2.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("F3"))
            {
                F3.setChecked(true);
                F3.setEnabled(false);
                F3.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("F4"))
            {
                F4.setChecked(true);
                F4.setEnabled(false);
                F4.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("G1"))
            {
                G1.setChecked(true);
                G1.setEnabled(false);
                G1.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("G2"))
            {
                G2.setChecked(true);
                G2.setEnabled(false);
                G2.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("G3"))
            {
                G3.setChecked(true);
                G3.setEnabled(false);
                G3.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("G4"))
            {
                G4.setChecked(true);
                G4.setEnabled(false);
                G4.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("H1"))
            {
                H1.setChecked(true);
                H1.setEnabled(false);
                H1.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("H2"))
            {
                H2.setChecked(true);
                H2.setEnabled(false);
                H2.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("H3"))
            {
                H3.setChecked(true);
                H3.setEnabled(false);
                H3.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
            if(items.getTenGhe().equals("H4"))
            {
                H4.setChecked(true);
                H4.setEnabled(false);
                H4.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#1FA17F") ));
            }
        }

        tiensuat = dbHelper.getTienCuaSuat(String.valueOf(suatid));
        // Sự kiện onclick cho các check box
        A1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });

        A2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });

        A3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });

        A4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });

        B1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });

        B2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        B3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        B4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        C1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        C2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        C3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        C4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        D1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        D2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        D3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        D4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        E1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        E2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        E3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        E4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        F1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        F2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));                }
            }
        });
        F3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        F4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        E1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        E2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        E3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        E4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        G1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        G2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        G3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        G4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        H1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        H2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        H3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });
        H4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Kiểm tra xem CheckBox có được tích hay không
                if (isChecked) {
                    // Nếu được tích, tự động tăng số trong TextView
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue++;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                } else {
                    // Nếu bỏ tích, tự động giảm số trong TextView (nếu bạn muốn)
                    int currentValue = Integer.parseInt(soGhe.getText().toString());
                    currentValue--;
                    soGhe.setText(String.valueOf(currentValue));
                    tongTien.setText(String.valueOf(tiensuat * currentValue));
                }
            }
        });

        // nút xác nhận
        Xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tongTienn=tongTien.getText().toString();
                Intent intent = new Intent(UserProcessOder.this, UserPayment.class);
                intent.putExtra("tongtien",tongTienn);
                intent.putExtra("user_id", userID);
                intent.putExtra("user_name", user_name);

                intent.putExtra("suat_id", suatid);
                if(A1.isChecked() && A1.isEnabled())
                {
                    intent.putExtra("A1", "A1");
                }
                if(A2.isChecked() && A2.isEnabled())
                {
                    intent.putExtra("A2", "A2");
                }
                if(A3.isChecked() && A3.isEnabled())
                {
                    intent.putExtra("A3", "A3");
                }
                if(A4.isChecked() && A4.isEnabled())
                {
                    intent.putExtra("A4", "A4");
                }
                if(B1.isChecked() && B1.isEnabled())
                {
                    intent.putExtra("B1", "B1");
                }
                if(B2.isChecked() && B2.isEnabled())
                {
                    intent.putExtra("B2", "B2");
                }
                if(B3.isChecked() && B3.isEnabled())
                {
                    intent.putExtra("B3", "B3");
                }
                if(B4.isChecked() && B4.isEnabled())
                {
                    intent.putExtra("B4", "B4");
                }
                if(C1.isChecked() && C1.isEnabled())
                {
                    intent.putExtra("C1", "C1");
                }
                if(C2.isChecked() && C2.isEnabled())
                {
                    intent.putExtra("C2", "C2");
                }
                if(C3.isChecked() && C3.isEnabled())
                {
                    intent.putExtra("C3", "C3");
                }
                if(C4.isChecked() && C4.isEnabled())
                {
                    intent.putExtra("C4", "C4");
                }
                if(D1.isChecked() && D1.isEnabled())
                {
                    intent.putExtra("D1", "D1");
                }
                if(D2.isChecked() && D2.isEnabled())
                {
                    intent.putExtra("D2", "D2");
                }
                if(D3.isChecked() && D3.isEnabled())
                {
                    intent.putExtra("D3", "D3");
                }
                if(D4.isChecked() && D4.isEnabled())
                {
                    intent.putExtra("D4", "D4");
                }
                if(E1.isChecked() && E1.isEnabled())
                {
                    intent.putExtra("E1", "E1");
                }
                if(E2.isChecked() && E2.isEnabled())
                {
                    intent.putExtra("E2", "E2");
                }
                if(E3.isChecked() && E3.isEnabled())
                {
                    intent.putExtra("E3", "E3");
                }
                if(E4.isChecked() && E4.isEnabled())
                {
                    intent.putExtra("E4", "E4");
                }
                if(F1.isChecked() && F1.isEnabled())
                {
                    intent.putExtra("F1", "F1");
                }
                if(F2.isChecked() && F2.isEnabled())
                {
                    intent.putExtra("F2", "F2");
                }
                if(F3.isChecked() && F3.isEnabled())
                {
                    intent.putExtra("F3", "F3");
                }
                if(F4.isChecked() && F4.isEnabled())
                {
                    intent.putExtra("F4", "F4");
                }
                if(G1.isChecked() && G1.isEnabled())
                {
                    intent.putExtra("G1", "G1");
                }
                if(G2.isChecked() && G2.isEnabled())
                {
                    intent.putExtra("G2", "G2");
                }
                if(G3.isChecked() && G3.isEnabled())
                {
                    intent.putExtra("G3", "G3");
                }
                if(G4.isChecked() && G4.isEnabled())
                {
                    intent.putExtra("G4", "G4");
                }
                if(H1.isChecked() && H1.isEnabled())
                {
                    intent.putExtra("H1", "H1");
                }
                if(H2.isChecked() && H2.isEnabled())
                {
                    intent.putExtra("H2", "H2");
                }
                if(H3.isChecked() && H3.isEnabled())
                {
                    intent.putExtra("H3", "H3");
                }
                if(H4.isChecked() && H4.isEnabled())
                {
                    intent.putExtra("H4", "H4");
                }

                startActivity(intent);
            }
        });


        // quay lại trang chi tiết
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProcessOder.this, UserFilmDetail.class);


                intent.putExtra("item_id", itemId);
                intent.putExtra("user_id", userID);
                intent.putExtra("user_name", user_name);
                intent.putExtra("item_name", itemName);
                intent.putExtra("item_moTa", itemMoTa);
                intent.putExtra("item_thoiLuong", itemThoiLuong);
                intent.putExtra("item_ngayPhatHanh", itemNgayPhatHanh);
                intent.putExtra("item_hinhAnh", itemHinhAnh);
                startActivity(intent);
            }
        });


        // bottomnavigationview chuyển trang
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_user_home:
                        Intent intent = new Intent(UserProcessOder.this, UserHome.class);
                        intent.putExtra("user_id", userID);
                        startActivity(intent);
                        break;
                    case R.id.nav_user_memories:
                        Intent intent_ticket = new Intent(UserProcessOder.this, UserHistory.class);
                        intent_ticket.putExtra("user_id", userID);
                        intent_ticket.putExtra("user_name", user_name);
                        startActivity(intent_ticket);
                        break;
//                    case R.id.nav_user_cart:
//                        Intent intent_seat = new Intent(UserHome.this, ManageSeat.class);
////                        intent_seat.putExtra("user_id",user_id);
////                        intent_seat.putExtra("user_name",user_name);
//                        startActivity(intent_seat);
//                        break;
                    case R.id.nav_user_info:
                        Intent intent_setfilm = new Intent(UserProcessOder.this, UserInfo.class);
                        intent_setfilm.putExtra("user_id", userID);
                        intent_setfilm.putExtra("user_name", user_name);
                        startActivity(intent_setfilm);
                        break;
                }
                return false;
            }

        });
    }

}