package com.example.oucinema;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oucinema.adapter.CommentAdapter;
import com.example.oucinema.adapter.Film_UserHome_Adapter;
import com.example.oucinema.adpterSpinner.SpinnerSetFilmAdapter;
import com.example.oucinema.adpterSpinner.SpinnerTheaterAdapter;
import com.example.oucinema.model.DanhGia;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Suat;

import java.io.File;
import java.util.ArrayList;


public class UserFilmDetail extends AppCompatActivity {

    // Đặt biến cho user film detail
    DBHelper dbHelper;
    String userID,user_name;
    TextView tenphim,mota,ratesao,thoiluong,ngayphathanh;
    Button Danhgia, datve;
    Spinner ratesosao, rap, suat;
    EditText Binhluan;
    ListView listviewrate;
    private static final int REQUEST_CODE_PERMISSIONS = 123;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_filmdetail);
        dbHelper = new DBHelper(UserFilmDetail.this);

        // nơi gọi id từ các layout
        tenphim = findViewById(R.id.txtviewnamefilm);
        mota= findViewById(R.id.txtviewdesciptionfilm);
        ratesao= findViewById(R.id.bienrate);
        thoiluong= findViewById(R.id.thoiluong);
        ngayphathanh= findViewById(R.id.ngaychieu);
        Danhgia= findViewById(R.id.btndangrating);
        datve= findViewById(R.id.btnxacnhan);
        ratesosao= findViewById(R.id.ratingstar);
        rap= findViewById(R.id.filmdetailtheater);
        suat= findViewById(R.id.filmdetailsetfilm);
        Binhluan= findViewById(R.id.rating);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        listviewrate = findViewById(R.id.listview_user_danhgia);
        ImageView btnReturn = findViewById(R.id.turn_back_home);

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

        int itemId = getIntent().getIntExtra("item_id", -1);
        String itemName = getIntent().getStringExtra("item_name");
        String itemMoTa = getIntent().getStringExtra("item_moTa");
        int itemThoiLuong = getIntent().getIntExtra("item_thoiLuong", -1);
        String itemNgayPhatHanh = getIntent().getStringExtra("item_ngayPhatHanh");
        String itemHinhAnh = getIntent().getStringExtra("item_hinhAnh");
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSIONS);
        Log.d("taggggggggg", String.valueOf(itemId));

        // Khai báo array list
        ArrayList<RapPhim> listRap = dbHelper.getRapPhim();
        SpinnerTheaterAdapter spinnerTheaterAdapter = new SpinnerTheaterAdapter(this,R.layout.item_selected_theater,listRap);
        rap.setAdapter(spinnerTheaterAdapter);
        RapPhim selectedRap = (RapPhim) rap.getSelectedItem();
        int idselectedrap = selectedRap.getId();

        ArrayList<Suat> listsetfilm = dbHelper.getSetFilmUser(String.valueOf(itemId),String.valueOf(idselectedrap));
        SpinnerSetFilmAdapter spinnerSetFilmAdapter = new SpinnerSetFilmAdapter(this,R.layout.item_selected_setfilm_user,listsetfilm);
        suat.setAdapter(spinnerSetFilmAdapter);
        int idselectedsuat = 0;
       if(listsetfilm.isEmpty()){
           suat.setVisibility(View.GONE);
           Toast.makeText(this, "Không có suất chiếu.", Toast.LENGTH_SHORT).show();
       }else{
           suat.setVisibility(View.VISIBLE);
           Suat selectedSuat = (Suat) suat.getSelectedItem();
           idselectedsuat= selectedSuat.getId();
       }
//        Suat selectedSuat = (Suat) suat.getSelectedItem();
//        int idselectedsuat = selectedSuat.getId();

        ArrayList<DanhGia> listDanhGia = dbHelper.getDanhGia(String.valueOf(itemId));
        CommentAdapter commentAdapter = new CommentAdapter(this,R.layout.list_danhgia,listDanhGia);
        listviewrate.setAdapter(commentAdapter);

        String[] numbers = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratesosao.setAdapter(adapter);


        // set dữ liệu
        tenphim.setText("Tên : "+itemName);
        mota.setText(itemMoTa);
        thoiluong.setText(String.valueOf(itemThoiLuong)+" phút");
        ngayphathanh.setText(itemNgayPhatHanh);

        File file = new File(getFilesDir(), itemHinhAnh);
        Drawable drawable = Drawable.createFromPath(file.getAbsolutePath());
        ImageView imageView = findViewById(R.id.image_filmdetail_user);
        imageView.setImageDrawable(drawable);



        // Quay về user home
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserFilmDetail.this, UserHome.class);
                intent.putExtra("user_id",userID);
                startActivity(intent);
            }
        });

        // Tiến hành đặt vé
        int finalIdselectedsuat = idselectedsuat;
        datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserFilmDetail.this,UserProcessOder.class);
                intent.putExtra("rap_id", idselectedrap);
                intent.putExtra("suat_id", finalIdselectedsuat);
                intent.putExtra("item_id",itemId);
                intent.putExtra("user_id",userID);
                intent.putExtra("user_name",user_name);

                startActivity(intent);
            }
        });

        // Tiến hành đánh giá
        Danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Phim phimSelected = new Phim();
                    phimSelected.setId(itemId);
                    String selectedRatingString = (String) ratesosao.getSelectedItem();
                    double selectedRating = Double.parseDouble(selectedRatingString);


                    DanhGia dg = new DanhGia();
                    dg.setDanhGia(Binhluan.getText().toString());
                    dg.setNguoiDanhGia(user_name);
                    dg.setRating(selectedRating);
                    dg.setPhimID(phimSelected);
                    boolean b = dbHelper.addCommentandRate(dg);
                    if (b) {
                        Toast.makeText(UserFilmDetail.this, "Thêm bình luận thành công", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(UserFilmDetail.this, "Thêm bình luận Thất bại", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Log.d("Taeeeeeeeeeeee" , "32"+String.valueOf(e));
                    Toast.makeText(UserFilmDetail.this, "Có vấn đề trong quá trình bình luận !!!", Toast.LENGTH_LONG).show();
                }

            }
        });

        // bottomnavigationview chuyển trang
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_user_home:
                        Intent intent = new Intent(UserFilmDetail.this, UserHome.class);
                        intent.putExtra("user_id",userID);
                        startActivity(intent);
                        break;
                    case R.id.nav_user_memories:
                        Intent intent_ticket = new Intent(UserFilmDetail.this, UserHistory.class);
                        intent_ticket.putExtra("user_id",userID);
                        intent_ticket.putExtra("user_name",user_name);
                        startActivity(intent_ticket);
                        break;
//                    case R.id.nav_user_cart:
//                        Intent intent_seat = new Intent(UserHome.this, ManageSeat.class);
////                        intent_seat.putExtra("user_id",user_id);
////                        intent_seat.putExtra("user_name",user_name);
//                        startActivity(intent_seat);
//                        break;
                    case R.id.nav_user_info:
                        Intent intent_setfilm = new Intent(UserFilmDetail.this, UserInfo.class);
                        intent_setfilm.putExtra("user_id",userID);
                        intent_setfilm.putExtra("user_name",user_name);
                        startActivity(intent_setfilm);
                        break;
                }
                return false;
            }

        });

    }

}