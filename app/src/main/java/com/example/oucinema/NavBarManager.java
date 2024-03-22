package com.example.oucinema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class NavBarManager extends AppCompatActivity {
    // Biến navigationview
    private NavigationView navigationView;
    TextView textName;
    ImageView ivStatis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_bar);
        navigationView = findViewById(R.id.navigation_view);
        ivStatis = findViewById(R.id.imageView33);

        String user_id = getIntent().getStringExtra("user_id");
        String user_name = getIntent().getStringExtra("user_name");


        textName = findViewById(R.id.navigation_manage_ten);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("user_name")) {
            String userName = bundle.getString("user_name");
            Log.d("Test", "Name: " + userName);
            Log.d("Test", "UserID: " + user_id);
            textName.setText(userName);

        } else {
            textName.setText("Errorrrrr");
        }

        ivStatis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavBarManager.this, Statis.class);
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_id",user_id);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_manager_Film:
                        Intent intent = new Intent(NavBarManager.this, ManageFilm.class);
                        intent.putExtra("user_name",user_name);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                        break;
                    case R.id.nav_manager_ticket:
                        Intent intent_ticket = new Intent(NavBarManager.this, ManageTicket.class);
                        intent_ticket.putExtra("user_name",user_name);
                        intent_ticket.putExtra("user_id",user_id);
                        startActivity(intent_ticket);
                        break;
                    case R.id.nav_manager_seat:
                        Intent intent_seat = new Intent(NavBarManager.this, ManageSeat.class);
                        intent_seat.putExtra("user_id",user_id);
                        intent_seat.putExtra("user_name",user_name);
                        startActivity(intent_seat);
                        break;
                    case R.id.nav_manager_setfilm:
                        Intent intent_setfilm = new Intent(NavBarManager.this, ManageSetFilm.class);
                        intent_setfilm.putExtra("user_id",user_id);
                        intent_setfilm.putExtra("user_name",user_name);
                        startActivity(intent_setfilm);
                        break;
                    case R.id.nav_manager_theater:
                        Intent intent_theater = new Intent(NavBarManager.this, ManageTheater.class);
                        intent_theater.putExtra("user_id",user_id);
                        intent_theater.putExtra("user_name",user_name);
                        startActivity(intent_theater);
                        break;
                    case R.id.nav_manager_user:
                        Intent intent_user = new Intent(NavBarManager.this, ManageUser.class);
                        intent_user.putExtra("user_id",user_id);
                        intent_user.putExtra("user_name",user_name);
                        startActivity(intent_user);
                        break;
                    case R.id.nav_manager_room:
                        Intent intent_room = new Intent(NavBarManager.this, ManageRoom.class);
                        intent_room.putExtra("user_id",user_id);
                        intent_room.putExtra("user_name",user_name);
                        startActivity(intent_room);
                        break;
                    case R.id.nav_manager_discount:
                        Intent intent_coupon = new Intent(NavBarManager.this, ManageCoupon.class);
                        intent_coupon.putExtra("user_id",user_id);
                        intent_coupon.putExtra("user_name",user_name);
                        startActivity(intent_coupon);
                        break;
                    case R.id.nav_manager_logout:
                        Intent intent_main = new Intent(NavBarManager.this, MainActivity.class);
                        intent_main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent_main);
                        finish();
                        break;
                }
                return false;
            }

        });
    }

}