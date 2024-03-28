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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oucinema.adapter.FilmAdapter;
import com.example.oucinema.adapter.UserHistory_Adapter;
import com.example.oucinema.adpterSpinner.SpinnerTheaterAdapter;
import com.example.oucinema.adpterSpinner.SpinnerTicketAdapter;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Ve;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class UserHistory extends AppCompatActivity {
    DBHelper dbHelper;
    private BottomNavigationView bottomNavigationView;
    SearchView tk;
    ListView listViewDatVe;
    String userID,user_name;
    Spinner chooseVe;
    Button Huy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_history);

        dbHelper = new DBHelper(UserHistory.this);

        tk = findViewById(R.id.manage_search_film_history);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        chooseVe = findViewById(R.id.spinnerHuyVe);
        Huy = findViewById(R.id.btHuy);
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

        // Khai báo array list
        ArrayList<Ve> listvespinner = dbHelper.getTicketForUser(String.valueOf(userID));
         SpinnerTicketAdapter SpinnerTicketAdapters= new SpinnerTicketAdapter(this, R.layout.item_selected_ve, listvespinner);
        chooseVe.setAdapter(SpinnerTicketAdapters);

        listViewDatVe = findViewById(R.id.listview_user_history);
        ArrayList<Ve> listve = dbHelper.getTicketHistoryUser(String.valueOf(userID));

        UserHistory_Adapter filmAdapter = new UserHistory_Adapter(this,R.layout.list_historyuser,listve);
        listViewDatVe.setAdapter(filmAdapter);


        // Lấy dữ liệu chuyển trang
        listViewDatVe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ve item = listve.get(position);

                // Lấy dữ liệu item
                int itemId = item.getId();
                String itemHinhAnh = item.getSuatID().getPhimID().getHinhAnh();
                String itemGhe = item.getGheID().getTenGhe();
                String itemTenPhim = item.getSuatID().getPhimID().getTenPhim();
                String itemRap = item.getSuatID().getPhongID().getRapPhimID().getTenRap();
                String itemPhong = item.getSuatID().getPhongID().getTenPhong();
                Double itemGia = item.getGiaTien();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String itemNgayChieu = sdf.format(item.getSuatID().getNgayChieu());
                String itemGioChieu = String.valueOf(item.getSuatID().getGioChieu());


                // Khởi động trang khác với dữ liệu
                Intent intent = new Intent(UserHistory.this, UserHistoryDetail.class);
                intent.putExtra("item_hinhAnh",itemHinhAnh);
                intent.putExtra("user_id", userID);
                intent.putExtra("user_name", user_name);
                intent.putExtra("user_tenphim",itemTenPhim);
                intent.putExtra("item_id", itemId);
                intent.putExtra("item_tenghe", itemGhe);
                intent.putExtra("item_tenrap", itemRap);
                intent.putExtra("item_tenphong", itemPhong);
                intent.putExtra("item_giatien", itemGia);
                intent.putExtra("item_ngaychieu", itemNgayChieu);
                intent.putExtra("item_giochieu", itemGioChieu);

                startActivity(intent);
            }
        });

        //hàm search
        tk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filmAdapter.getFilter().filter(s);
                return false;
            }
        });

        // bottomnavigationview chuyển trang
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_user_home:
                        Intent intent = new Intent(UserHistory.this, UserHome.class);
                        intent.putExtra("user_id",userID);
                        startActivity(intent);
                        break;
                    case R.id.nav_user_memories:
                        Intent intent_ticket = new Intent(UserHistory.this, UserHistory.class);
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
                        Intent intent_setfilm = new Intent(UserHistory.this, UserInfo.class);
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