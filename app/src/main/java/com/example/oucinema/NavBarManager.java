package com.example.oucinema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class NavBarManager extends AppCompatActivity {
    // Biến navigationview
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_bar);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_manager_Film:
                        Intent intent = new Intent(NavBarManager.this, ManageFilm.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_manager_ticket:
                        Intent intent_ticket = new Intent(NavBarManager.this, ManageTicket.class);
                        startActivity(intent_ticket);
                        break;
                    case R.id.nav_manager_seat:
                        Intent intent_seat = new Intent(NavBarManager.this, ManageSeat.class);
                        startActivity(intent_seat);
                        break;
                    case R.id.nav_manager_setfilm:
                        Intent intent_setfilm = new Intent(NavBarManager.this, ManageSetFilm.class);
                        startActivity(intent_setfilm);
                        break;
                    case R.id.nav_manager_theater:
                        Intent intent_theater = new Intent(NavBarManager.this, ManageTheater.class);
                        startActivity(intent_theater);
                        break;
                    case R.id.nav_manager_user:
                        Intent intent_user = new Intent(NavBarManager.this, ManageUser.class);
                        startActivity(intent_user);
                        break;
                    case R.id.nav_manager_room:
                        Intent intent_room = new Intent(NavBarManager.this, ManageRoom.class);
                        startActivity(intent_room);
                        break;
                    case R.id.nav_manager_discount:
                        Intent intent_coupon = new Intent(NavBarManager.this, ManageCoupon.class);
                        startActivity(intent_coupon);
                        break;
                }
                return false;
            }

        });
    }

}