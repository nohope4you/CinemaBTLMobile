package com.example.oucinema;

import android.app.Service;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.design.widget.NavigationView;
//import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.Provider;


public class NavBarHeader extends AppCompatActivity {
    TextView textID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);
        textID = findViewById(R.id.navigation_manage_ten);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("user_name")) {
            String userId = bundle.getString("user_name");
            textID.setText(String.valueOf(userId));
            Intent intent = new Intent(NavBarHeader.this,NavBarManager.class);
            startActivity(intent);
            Log.d("Testrrrrrrrrr", "asdsdssdassdsdasd: " + userId);
        } else {
            textID.setText("Errorrrrr");
        }


    }

    public void run() {
        setContentView(R.layout.nav_header);
        textID = findViewById(R.id.navigation_manage_ten);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("user_name")) {
            String userId = bundle.getString("user_name");
            textID.setText(String.valueOf(userId));
            Intent intent = new Intent(NavBarHeader.this,NavBarManager.class);
            startActivity(intent);
            Log.d("Testrrrrrrrrr", "asdsdssdassdsdasd: " + userId);
        } else {
            textID.setText("Errorrrrr");
        }
    }
}