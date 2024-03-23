package com.example.oucinema;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.FilmAdapter;
import com.example.oucinema.adapter.Film_UserHome_Adapter;
import com.example.oucinema.model.Phim;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class UserHome extends AppCompatActivity {

    // Đặt biến cho user home
    DBHelper dbHelper;
    ListView listviewFilm;
    SearchView tk;
    String userID;
    ImageView[] img = new ImageView[4];;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);
        dbHelper = new DBHelper(UserHome.this);

        // nơi gọi id từ các layout
        tk = findViewById(R.id.user_search_film);
        listviewFilm = findViewById(R.id.listview_user_home);
        img[0] = findViewById(R.id.image_film_new_1);
        img[1] = findViewById(R.id.image_film_new_2);
        img[2] = findViewById(R.id.image_film_new_3);
        img[3] = findViewById(R.id.image_film_new_4);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        // nơi gọi arraylist và adapter
        ArrayList<Phim> listPhim = dbHelper.getPhim();
        ArrayList<Phim> listimagePhim = dbHelper.getPhimfornewfilmUser();
        Film_UserHome_Adapter filmAdapter = new Film_UserHome_Adapter(this,R.layout.list_film_user,listPhim);
        listviewFilm.setAdapter(filmAdapter);


        // nhận intent từ các trang khác
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("user_id")) {
            String userId = bundle.getString("user_id");
            Log.d("Test", "UserID: " + userId);
            userID = userId;
        } else {
            userID = "0";
        }
        String user_name = dbHelper.getUserNAMELogin(userID);


        // Tự động đổ dữ liệu vào imageView
        for(int i=0;i < 4;i++)
        {
            Phim item = listimagePhim.get(i);
            File file = new File(getFilesDir(), item.getHinhAnh());
            Drawable drawable = Drawable.createFromPath(file.getAbsolutePath());
            img[i].setImageDrawable(drawable);
        }

        // bottomnavigationview chuyển trang
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_user_home:
                        Intent intent = new Intent(UserHome.this, UserHome.class);
                        intent.putExtra("user_id",userID);
                        startActivity(intent);
                        break;
                    case R.id.nav_user_memories:
                        Intent intent_ticket = new Intent(UserHome.this, UserHistory.class);
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
                        Intent intent_setfilm = new Intent(UserHome.this, UserInfo.class);
                        intent_setfilm.putExtra("user_id",userID);
                        intent_setfilm.putExtra("user_name",user_name);
                        startActivity(intent_setfilm);
                        break;
                }
                return false;
            }

        });

        //hàm search
        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filmAdapter.getFilter().filter(newText);
                return true;
            }
        });

        // Lấy dữ liệu chuyển trang
        listviewFilm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phim item = listPhim.get(position);

                // Lấy dữ liệu item
                int itemId = item.getId();
                String itemName = item.getTenPhim();
                String itemMoTa = item.getMoTa();
                Log.d("mô tả: ",item.getMoTa().toString());
                String itemTheLoai = item.getTheLoai();
                int itemThoiLuong = item.getThoiLuong();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String itemNgayPhatHanh = sdf.format(item.getNgayPhatHanh());
                String itemDaoDien = item.getDaoDien();
                String itemHinhAnh = item.getHinhAnh();


                // Khởi động trang khác với dữ liệu
                Intent intent = new Intent(UserHome.this, UserFilmDetail.class);
                intent.putExtra("item_hinhAnh",itemHinhAnh);
                intent.putExtra("user_id",userID);
                intent.putExtra("user_name",user_name);
                intent.putExtra("item_id", itemId);
                intent.putExtra("item_name", itemName);
                intent.putExtra("item_moTa", itemMoTa);
                intent.putExtra("item_theLoai", itemTheLoai);
                intent.putExtra("item_thoiLuong", itemThoiLuong);
                intent.putExtra("item_ngayPhatHanh", itemNgayPhatHanh);
                intent.putExtra("item_daoDien", itemDaoDien);
//                intent.putExtra("item_hinhAnh", itemName);

//                intent.putExtra("item_isDelete", itemName);
//                intent.putExtra("item_userUpdate", itemName);
                startActivity(intent);
            }
        });

    }
}
