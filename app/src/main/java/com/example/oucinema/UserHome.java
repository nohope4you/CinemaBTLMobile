package com.example.oucinema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.oucinema.adapter.FilmAdapter;
import com.example.oucinema.adapter.Film_UserHome_Adapter;
import com.example.oucinema.model.Phim;

import java.util.ArrayList;


public class UserHome extends AppCompatActivity {

    // Đặt biến cho user home
    DBHelper dbHelper;
    ListView listviewFilm;
    SearchView tk;
    String userID;
    ImageView imgv1, imgv2, imgv3, imgv4;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);
        dbHelper = new DBHelper(UserHome.this);

        // nơi gọi id từ các layout
        tk = findViewById(R.id.user_search_film);
        listviewFilm = findViewById(R.id.listview_user_home);
        imgv1 = findViewById(R.id.image_film_new_1);
        imgv2 = findViewById(R.id.image_film_new_2);
        imgv3 = findViewById(R.id.image_film_new_3);
        imgv4 = findViewById(R.id.image_film_new_4);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // nơi gọi arraylist và adapter
        ArrayList<Phim> listPhim = dbHelper.getPhim();
        Film_UserHome_Adapter filmAdapter = new Film_UserHome_Adapter(this,R.layout.list_film_user,listPhim);
        listviewFilm.setAdapter(filmAdapter);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("user_id")) {
            String userId = bundle.getString("user_id");
            Log.d("Test", "UserID: " + userId);
            userID = userId;
        } else {
            userID = "0";
        }

        // bottomnavigationview chuyển trang


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_user_home:
                        Intent intent = new Intent(UserHome.this, UserHome.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_user_memories:
                        Intent intent_ticket = new Intent(UserHome.this, UserHistory.class);
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
                        startActivity(intent_setfilm);
                        break;
                }
                return false;
            }

        });

    }
}
