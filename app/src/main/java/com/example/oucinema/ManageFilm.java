package com.example.oucinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oucinema.model.User;


public class ManageFilm extends AppCompatActivity {

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_film);
        dbHelper = new DBHelper(ManageFilm.this);
        TextView textID;
        textID= findViewById(R.id.textView22);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("user_id")) {
            String userId = bundle.getString("user_id");
            Log.d("Test", "UserID: " + userId);
            textID.setText(String.valueOf(userId));
        } else {
            textID.setText("Errorrrrr");
        }


        // Nơi gọi biến
        ImageView btnMenuList= findViewById(R.id.menu_list);
        ImageView btnAddFilm= findViewById(R.id.manage_add_film);
        // Tạo Intent
        Intent intent = new Intent(this, NavBarManager.class);
        Intent intentAddFilm = new Intent(this, ManageAddFilm.class);

        // Quay về trang navbar
        btnMenuList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = dbHelper.getUserNAMELogin(textID.getText().toString());
                Intent intent = new Intent(ManageFilm.this,NavBarManager.class);
                intent.putExtra("user_name", user_name);
                startActivity(intent);
            }
        });
        // Mở trang thêm sửa phim
        btnAddFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentAddFilm);
            }
        });
//        Intent intents = getIntent();
//        User user = intents.getParcelableExtra("user");
//        int userID = user.getId();
//        TextView testID;
//        testID= findViewById(R.id.textView22);
//        testID.setText(userID);


    }
}
