package com.example.oucinema;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.oucinema.model.Phim;
import android.content.ContentResolver;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import android.content.res.Resources;
import android.content.Context;

public class ManageAddFilm extends AppCompatActivity {
    DBHelper dbHelper;
    EditText etTenPhim, etMoTa, etTheLoai, etThoiLuong, etNgayPhatHanh, etDaoDien, etLinkTrailer;
    Button btnThemFilm,btnSuaFilm,btnXoaFilm,btnHinhAnh;
    ImageView e ;
    private static String urlString;
    private static final int PICK_IMAGE_REQUEST_CODE = 1;
    private static final int REQUEST_CODE_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_film);
        dbHelper = new DBHelper(ManageAddFilm.this);
        String user_name = getIntent().getStringExtra("user_name");
        String user_id = getIntent().getStringExtra("user_id");
        if(user_id !=null)
            Log.d("test","user id from addfilm "+user_id);
        else
            Log.d("test","error ");

        // Lấy dữ liệu từ Intent
        int itemId = getIntent().getIntExtra("item_id", -1);
        String itemName = getIntent().getStringExtra("item_name");
        String itemMoTa = getIntent().getStringExtra("item_moTa");
        String itemTheLoai = getIntent().getStringExtra("item_theLoai");
        int itemThoiLuong = getIntent().getIntExtra("item_thoiLuong", -1);
        String itemNgayPhatHanh = getIntent().getStringExtra("item_ngayPhatHanh");
        String itemDaoDien = getIntent().getStringExtra("item_daoDien");
        String itemHinhAnh = getIntent().getStringExtra("item_hinhAnh");


        // Nơi gọi biến
        e = findViewById(R.id.imageView13);
        ImageView btnReturn = findViewById(R.id.turn_back_managefilm);
        etTenPhim = findViewById(R.id.Thongtinphimtenphim);
        etMoTa = findViewById(R.id.Thongtinphimmota);
        etTheLoai = findViewById(R.id.Thongtinphimtheloai);
        etThoiLuong = findViewById(R.id.Thongtinphimthoiluong);
        etNgayPhatHanh = findViewById(R.id.Thongtinphimngayphathanh);
        etDaoDien = findViewById(R.id.Thongtinphimdaodien);
        etLinkTrailer = findViewById(R.id.Thongtinphimlinktrailer);
        btnThemFilm = findViewById(R.id.btn_themphim);
        btnSuaFilm=findViewById(R.id.btn_suaphim);
        btnXoaFilm = findViewById(R.id.btn_xoaphim);
        btnHinhAnh = findViewById(R.id.Thongtinphimnuthinhanh);



        // Update dữ liệu
//        EditText textViewName = findViewById(R.id.Thongtinphimtenphim);
        Log.d("ID phim: ", String.valueOf(itemId));
        if (itemId == -1) {
            etTenPhim.getText().clear();
            etMoTa.getText().clear();
            etTheLoai.getText().clear();
            etThoiLuong.getText().clear();
            etNgayPhatHanh.getText().clear();
            etDaoDien.getText().clear();
            etLinkTrailer.getText().clear();
            btnXoaFilm.setEnabled(false);
            btnXoaFilm.setTextColor(Color.parseColor("#C7C8CC"));

        } else {
            String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSIONS);

            etTenPhim.setText(itemName);
            etMoTa.setText(itemMoTa);
            etTheLoai.setText(itemTheLoai);
            etThoiLuong.setText(String.valueOf(itemThoiLuong));
            etNgayPhatHanh.setText(itemNgayPhatHanh);
            etDaoDien.setText(itemDaoDien);

            File file = new File(getFilesDir(), itemHinhAnh);
            Drawable drawable = Drawable.createFromPath(file.getAbsolutePath());
            ImageView imageView = findViewById(R.id.imageView13);
            imageView.setImageDrawable(drawable);

            Log.d("test url","test "+itemHinhAnh);


        }


        // Tạo Intent
        Intent intent = new Intent(this, ManageFilm.class);

        btnThemFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    e = findViewById(R.id.imageView13);
                    Phim phim = new Phim();
                    phim.setTenPhim(etTenPhim.getText().toString());
                    phim.setMoTa(etMoTa.getText().toString());
                    phim.setTheLoai(etTheLoai.getText().toString());
                    phim.setThoiLuong(Integer.parseInt(etThoiLuong.getText().toString()));
                    java.sql.Date date = java.sql.Date.valueOf(etNgayPhatHanh.getText().toString());
                    phim.setNgayPhatHanh(date);
                    phim.setDaoDien(etDaoDien.getText().toString());
                    phim.setLinkTrailer(etLinkTrailer.getText().toString());
                    phim.setUserUpdate(Integer.parseInt(user_id));
                    phim.setHinhAnh(urlString);
                boolean b = dbHelper.addFilm(phim);
                if (b) {
                    Toast.makeText(ManageAddFilm.this, "Thêm phim thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ManageAddFilm.this, "Thêm phim Thất bại", Toast.LENGTH_LONG).show();
                }
                }catch (Exception e){
                    Log.d("log exception" , "log :" +e);
                    Toast.makeText(ManageAddFilm.this, "Chưa chọn hình ảnh", Toast.LENGTH_LONG).show();
                }

            }
        });
        //Xoá
        btnXoaFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phim phim = new Phim();
                phim.setId(itemId);
                String idd= String.valueOf(itemId);
                phim.setUserUpdate(Integer.parseInt(user_id));
                boolean b = dbHelper.deleteFilm(phim,idd);
                if (b) {
                    Toast.makeText(ManageAddFilm.this, "Xoá phim thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ManageAddFilm.this, "Xoá phim Thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Sửa thông tin phim
        btnSuaFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemId == -1) {
                    Toast.makeText(ManageAddFilm.this, "Vui lòng quay lại chọn phim cần sửa", Toast.LENGTH_LONG).show();
                }else{
                    Phim phim = new Phim();
                    phim.setTenPhim(etTenPhim.getText().toString());
                    phim.setMoTa(etMoTa.getText().toString());
                    phim.setTheLoai(etTheLoai.getText().toString());
                    phim.setThoiLuong(Integer.parseInt(etThoiLuong.getText().toString()));
                    java.sql.Date date = java.sql.Date.valueOf(etNgayPhatHanh.getText().toString());
                    phim.setNgayPhatHanh(date);
                    phim.setDaoDien(etDaoDien.getText().toString());
                    phim.setHinhAnh(urlString);
                    phim.setLinkTrailer(etLinkTrailer.getText().toString());
                    String idphim = String.valueOf(itemId);
                    phim.setUserUpdate(Integer.parseInt(user_id));

                    boolean b = dbHelper.updateFilm(phim,idphim);
                    if (b) {
                        Toast.makeText(ManageAddFilm.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ManageAddFilm.this, "Cập nhật phim Thất bại", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });

        // nút thêm hình ảnh
        btnHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

    }
    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            if (imageUri != null) {
                try {
                    // Lấy bitmap từ URI ảnh
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                    // Tạo tên file PNG
                    String fileName = "image_" + etTenPhim.getText().toString() + ".png";

                    // Lưu bitmap vào file PNG
                    File file = new File(getFilesDir(), fileName);
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.close();

                    // Thêm file PNG vào drawable
                    int drawableName = getResources().getIdentifier(fileName, "drawable", getPackageName());
                    Drawable drawable = Drawable.createFromPath(file.getAbsolutePath());
                    ImageView imageView = findViewById(R.id.imageView13);
                    imageView.setImageDrawable(drawable);

                    urlString = fileName;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}