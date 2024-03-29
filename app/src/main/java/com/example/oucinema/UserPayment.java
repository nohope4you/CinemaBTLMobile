package com.example.oucinema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Suat;
import com.example.oucinema.model.User;
import com.example.oucinema.model.Ve;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;


public class UserPayment extends AppCompatActivity {

    DBHelper dbHelper;
    TextView tongtien;
    Button kiemtramgg, thanhtoan;
    Spinner phuongthucthanhtoan;
    EditText etMGG ;
    String userID,user_name;
    Integer phanTram,idMGG;
    Double TienGiam, KqSauKhiGiam;
    Date TGHL;
    ImageView btnReturn;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_payment);
        dbHelper = new DBHelper(UserPayment.this);

        // Khai báo biến
        etMGG= findViewById(R.id.txtmagiamgia);
        tongtien = findViewById(R.id.tongtien);
        kiemtramgg = findViewById(R.id.btnkiemtramagiamgia);
        btnReturn = findViewById(R.id.btnturnbackprocessorder);
        thanhtoan = findViewById(R.id.btnthanhtoan);
        phuongthucthanhtoan = findViewById(R.id.phuongthucthanhtoan);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Lấy biến Intent
        String soGhe = getIntent().getStringExtra("so_ghe");
        String suatid = getIntent().getStringExtra("suat_id");
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
        String totalMoney= getIntent().getStringExtra("tongtien");
        tongtien.setText(totalMoney);
        String A1 = getIntent().getStringExtra("A1");
        String A2 = getIntent().getStringExtra("A2");
        String A3 = getIntent().getStringExtra("A3");
        String A4 = getIntent().getStringExtra("A4");
        String B1 = getIntent().getStringExtra("B1");
        String B2 = getIntent().getStringExtra("B2");
        String B3 = getIntent().getStringExtra("B3");
        String B4 = getIntent().getStringExtra("B4");
        String C1 = getIntent().getStringExtra("C1");
        String C2 = getIntent().getStringExtra("C2");
        String C3 = getIntent().getStringExtra("C3");
        String C4 = getIntent().getStringExtra("C4");
        String D1 = getIntent().getStringExtra("D1");
        String D2 = getIntent().getStringExtra("D2");
        String D3 = getIntent().getStringExtra("D3");
        String D4 = getIntent().getStringExtra("D4");
        String E1 = getIntent().getStringExtra("E1");
        String E2 = getIntent().getStringExtra("E2");
        String E3 = getIntent().getStringExtra("E3");
        String E4 = getIntent().getStringExtra("E4");
        String F1 = getIntent().getStringExtra("F1");
        String F2 = getIntent().getStringExtra("F2");
        String F3 = getIntent().getStringExtra("F3");
        String F4 = getIntent().getStringExtra("F4");
        String G1 = getIntent().getStringExtra("G1");
        String G2 = getIntent().getStringExtra("G2");
        String G3 = getIntent().getStringExtra("G3");
        String G4 = getIntent().getStringExtra("G4");
        String H1 = getIntent().getStringExtra("H1");
        String H2 = getIntent().getStringExtra("H2");
        String H3 = getIntent().getStringExtra("H3");
        String H4 = getIntent().getStringExtra("H4");
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng trong Java bắt đầu từ 0
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String formattedDate = String.format("%d-%d-%d", year, month, day);
        Date formatdateDate= Date.valueOf(formattedDate);

        Log.d("tgggggggggggggggggggg" , formatdateDate.toString());

        // Spinner
        String[] numbers = {"Chuyển Khoản"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phuongthucthanhtoan.setAdapter(adapter);

        // nút kiểm tra
        kiemtramgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                MaGiamGia mggs = new MaGiamGia();
                String codept= etMGG.getText().toString();
                mggs = dbHelper.getPhanTramCuaMGG(codept);

                idMGG = mggs.getId();
                phanTram = mggs.getPhanTramGiam();
                TGHL = mggs.getThoiGianHieuLuc();

                if (idMGG != null || idMGG !=0)
                {
                    if (formatdateDate.before(TGHL))
                    {
                        TienGiam = Double.valueOf(tongtien.getText().toString()) * phanTram /100;
                        KqSauKhiGiam = Double.valueOf(tongtien.getText().toString()) - TienGiam;
                        tongtien.setText(String.valueOf(KqSauKhiGiam));
                        kiemtramgg.setEnabled(false);
                        Toast.makeText(UserPayment.this,"Áp dụng mã giảm thành công !!!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(UserPayment.this,"Mã giảm giá đã hết hiệu lực !!!",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(UserPayment.this,"Mã giảm giá không hợp lệ !!!",Toast.LENGTH_LONG).show();
                }
            }catch (Exception exception){
                    Toast.makeText(UserPayment.this,"Mã không tồn tại !!!",Toast.LENGTH_LONG).show();
                }
            }
        });

        // nút xác nhận
        thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                        if(A1 != null)
                        {
                            dat(A1);
                        }
                        if(A2 != null)
                        {
                            dat(A2);
                        }
                        if(A3 != null)
                        {
                         dat(A3);
                        }
                        if(A4 != null) {
                            dat(A4);
                        }
                        if(B1 != null)
                        {
                            dat(B1);
                            }
                        if(B2 != null)
                        {
                            dat(B2);
                        }
                        if(B3 != null)
                        {
                            dat(B3);
                        }
                        if(B4 != null)
                        {
                            dat(B4);
                        }
                        if(C1 != null)
                        {
                            dat(C1);
                        }
                        if(C2 != null)
                        {
                            dat(C2);
                        }
                        if(C3 != null)
                        {
                            dat(C3);
                        }
                        if(C4 != null)
                        {
                            dat(C4);
                        }
                        if(D1 != null)
                        {
                            dat(D1);
                        }
                        if(D2 != null)
                        {
                            dat(D2);
                        }
                        if(D3 != null)
                        {
                            dat(D3);
                        }
                        if(D4 != null)
                        {
                            dat(D4);
                        }
                        if(E1 != null)
                        {
                            dat(E1);
                        }
                        if(E2 != null) {
                            dat(E2);
                        }
                        if(E3 != null)
                        {
                            dat(E3);
                        }
                        if(E4 != null)
                        {
                            dat(E4);
                        }
                        if(F1 != null)
                        {
                            dat(F1);
                        }
                        if(F2!= null)
                        {
                            dat(F2);
                        }
                        if(F3!= null)
                        {
                            dat(F3);
                        }
                        if(F4!= null)
                        {
                            dat(F4);
                        }
                        if(G1!= null)
                        {
                            dat(G1);
                        }
                        if(G2!= null)
                        {
                            dat(G2);
                        }
                        if(G3!= null)
                        {
                            dat(G3);
                        }
                        if(G4!= null)
                        {
                            dat(G4);
                        }
                        if(H1!= null)
                        {
                            dat(H1);
                        }
                        if(H2!= null)
                        {
                            dat(H2);
                        }
                        if(H3!= null)
                        {
                            dat(H3);
                        }
                        if(H4!= null)
                        {
                            dat(H4);
                        }

                }
                catch (Exception ex){
                    Toast.makeText(UserPayment.this,ex.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        // quay lại trang home
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPayment.this, UserHome.class);
                intent.putExtra("user_id", userID);

                startActivity(intent);
            }
        });

        // bottomnavigationview chuyển trang
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_user_home:
                        Intent intent = new Intent(UserPayment.this, UserHome.class);
                        intent.putExtra("user_id", userID);
                        startActivity(intent);

                        break;
                    case R.id.nav_user_memories:
                        Intent intent_ticket = new Intent(UserPayment.this, UserHistory.class);
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
                        Intent intent_setfilm = new Intent(UserPayment.this, UserInfo.class);
                        intent_setfilm.putExtra("user_id", userID);
                        intent_setfilm.putExtra("user_name", user_name);
                        startActivity(intent_setfilm);
                        break;
                }
                return false;
            }

        });


    }
    public void dat(String s){
        String soGhe = getIntent().getStringExtra("so_ghe");
        int suatid = getIntent().getIntExtra("suat_id",-1);
       Double tien1ghe = Double.parseDouble(tongtien.getText().toString()) / Double.parseDouble(soGhe);
        Integer idghe = dbHelper.getIDGhe(s);
        if (idMGG == null || idMGG == 0){
            idMGG = 1;
        }
        Suat suat = new Suat();
        suat.setId(suatid);
        Ghe ghe = new Ghe();
        ghe.setId(idghe);
        MaGiamGia mgg = new MaGiamGia();
        mgg.setId(idMGG);
        User user = new User();
        user.setId(Integer.parseInt(userID));
        Ve ve = new Ve();
        ve.setSuatID(suat);
        ve.setGheID(ghe);
        ve.setMaID(mgg);
        ve.setUserID(user);
        ve.setGiaTien(tien1ghe);
        ve.setHinhThuc(String.valueOf(phuongthucthanhtoan.getSelectedItem()));
        ve.setUserUpdate(Integer.parseInt(userID));

        boolean b = dbHelper.addVe(ve);
        if(b){
            Toast.makeText(UserPayment.this,"Mua vé thành công",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(UserPayment.this, UserHome.class);
            intent.putExtra("user_id",userID);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(UserPayment.this,"Mua vé thất bại",Toast.LENGTH_LONG).show();
        }
    }

}