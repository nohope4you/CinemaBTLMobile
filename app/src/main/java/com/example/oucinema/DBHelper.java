package com.example.oucinema;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.User;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "cinema.db";


    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Bảng Role
        sqLiteDatabase.execSQL("create table Role (" +
                "id INTEGER  primary key autoincrement," +
                "nameRole TEXT NOT NULL);");

        //Bảng User
        sqLiteDatabase.execSQL("create table User(" +
                "id INTEGER  primary key autoincrement," +
                "hoTen TEXT NOT NULL," +
                "phoneNumber TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "gioiTinh TEXT,"+
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "roleID INTEGER NOT NULL," +
                "Foreign key (roleID) references Role(id));");



        //Bảng Phim
        sqLiteDatabase.execSQL("create table Phim(" +
                "id INTEGER  primary key autoincrement," +
                "tenPhim TEXT NOT NULL," +
                "moTa TEXT NOT NULL," +
                "theLoai TEXT NOT NULL," +
                "thoiLuong int NOT NULL," +
                "ngayPhatHanh DATE NOT NULL," +
                "daoDien TEXT NOT NULL," +
                "hinhAnh TEXT NOT NULL," +
                "linkTrailer TEXT NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER);");
        sqLiteDatabase.execSQL("insert into Phim(tenPhim,moTa,theLoai,thoiLuong,ngayPhatHanh,daoDien,hinhAnh,linkTrailer,isDelete,userUpdate) " +
                "                         values('Mai','Phim về Mai và Sâu','Tình cảm',120,'2024-3-13','Trấn Thành','hinhanh','linktrailer',false,1) ");
        //Bảng Rạp Phim
        sqLiteDatabase.execSQL("create table RapPhim(" +
                "id INTEGER primary key autoincrement," +
                "tenRap TEXT NOT NULL, " +
                "diaChi TEXT NOT NULL," +
                "soDienThoaiLienHe TEXT NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER" +
                ");");
        sqLiteDatabase.execSQL("insert into RapPhim(tenRap,diaChi,soDienThoaiLienHe,isDelete,userUpdate) values('Rạp 1','Tân Bình','09876226262',false,1) ");
        sqLiteDatabase.execSQL("insert into RapPhim(tenRap,diaChi,soDienThoaiLienHe,isDelete,userUpdate) values('Rạp 2','Tân Bình','09876226262',false,1) ");
        //Bảng Ghế
        sqLiteDatabase.execSQL("create table Ghe(" +
                "id INTEGER  primary key autoincrement," +
                "tenGhe TEXT NOT NULL," +
                "loaiGhe TEXT NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER" +
                ");");
        sqLiteDatabase.execSQL("insert into Ghe(tenGhe,loaiGhe,isDelete,userUpdate) values('A1','Thường',false,1) ");
        sqLiteDatabase.execSQL("insert into Ghe(tenGhe,loaiGhe,isDelete,userUpdate) values('B1','Thường',false,1) ");
        sqLiteDatabase.execSQL("insert into Ghe(tenGhe,loaiGhe,isDelete,userUpdate) values('C1','Thường',false,1) ");
        sqLiteDatabase.execSQL("insert into Ghe(tenGhe,loaiGhe,isDelete,userUpdate) values('D1','Thường',false,1) ");
        //Bảng Phòng
        sqLiteDatabase.execSQL("create table Phong (" +
                "id INTEGER  primary key autoincrement," +
                "tenPhong TEXT NOT NULL," +
                "rapPhimID INTEGER NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER," +
                "foreign key (rapPhimID) references RapPhim(id)" +
                ");");
        sqLiteDatabase.execSQL("insert into Phong(tenPhong,rapPhimID,isDelete,userUpdate) values('Phòng 1',1,false,1) ");
        sqLiteDatabase.execSQL("insert into Phong(tenPhong,rapPhimID,isDelete,userUpdate) values('Phòng 2',2,false,1) ");

        //Bảng Suất
        sqLiteDatabase.execSQL("create table Suat (" +
                "id INTEGER primary key autoincrement," +
                "ngayChieu DATE NOT NULL," +
                "gioChieu TIME NOT NULL," +
                "giaMacDinh DOUBLE NOT NULL," +
                "phimID INTEGER NOT NULL," +
                "phongID INTEGER NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER," +
                "foreign key (phimID) references Phim(id)," +
                "foreign key (phongID) references Phong(id)" +
                ");");
        sqLiteDatabase.execSQL("insert into Suat(ngayChieu,gioChieu,giaMacDinh,phimID,phongID,isDelete,userUpdate) values('14/3/2024','14:30:00',45000,1,1,false,1) ");
        sqLiteDatabase.execSQL("insert into Suat(ngayChieu,gioChieu,giaMacDinh,phimID,phongID,isDelete,userUpdate) values('14/3/2024','15:30:00',45000,1,2,false,1) ");
        //Bảng Mã giảm giá
        sqLiteDatabase.execSQL("create table MaGiamGia (" +
                "id INTEGER primary key autoincrement," +
                "tenMaGiam TEXT NOT NULL," +
                "phanTramGiam INTEGER NOT NULL," +
                "thoiGianHieuLuc DATE NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER" +
                ");");

        //Bảng Vé
        sqLiteDatabase.execSQL("create table Ve(" +
                "id INTEGER  primary key autoincrement," +
                "suatID INTEGER NOT NULL," +
                "gheID INTEGER NOT NULL," +
                "giamGiaID INTEGER NOT NULL," +
                "userID INTEGER NOT NULL," +
                "giaTien DOUBLE NOT NULL," +
                "thoiGianDat DATE NOT NULL," +
                "hinhThuc TEXT NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER," +
                "foreign key (suatID) references Suat(id)," +
                "foreign key (gheID) references Ghe(id)," +
                "foreign key (giamGiaID) references MaGiamGia(id)," +
                "foreign key (userID) references User(id)" +
                ");");

        //Bảng đánh giá
        sqLiteDatabase.execSQL("create table DanhGia(" +
                "id INTEGER primary key autoincrement," +
                "danhGia TEXT NOT NULL," +
                "nguoiDanhGia TEXT NOT NULL," +
                "rating DOUBLE," +
                "phimID INTEGER," +
                "foreign key (phimID) references Phim(id)" +
                ")");

        sqLiteDatabase.execSQL("insert into Role(nameRole) values('User') ");
        sqLiteDatabase.execSQL("insert into Role(nameRole) values('Admin') ");



        sqLiteDatabase.execSQL("insert into User(hoTen,phoneNumber,email,gioiTinh,username,password,roleID)values ('Admin','123456789','Admin@gmail.com','Nam','admin','admin123',2)");


    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addUser(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("hoTen",user.getHoTen());
        cv.put("phoneNumber",user.getPhoneNumber());
        cv.put("email",user.getEmail());
        cv.put("gioiTinh",user.getGioiTinh());
        cv.put("username",user.getUsername());
        cv.put("password",user.getPassword());
        cv.put("roleID",user.getRoleID().getId());
        long user1 = db.insert("user", null, cv);
        if(user1 ==-1){
            return false;
        }
        else{
            return  true;
        }
    }
    public  boolean checkUserExist(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where username = ? ",new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        else
        {
            return false;
        }
    }

    // Hàm login
    public boolean userLogin (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where username =? and password = ? ",new String[]{username,password});
        if(cursor.getCount()>0){
            return  true;
        }
        else{
            return  false;
        }
    }
    public boolean checkRoleUser (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where username =? and password =? and roleID ==2 ",new String[]{username,password});
        if(cursor.getCount()>0){
            return  true;
        }
        else{
            return  false;
        }
    }

    public String getUserIDLogin(String username, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, pwd});
        if (cursor != null && cursor.getCount() > 0) {
            int columnIndex = cursor.getColumnIndex("id");
            String columnName = cursor.getColumnName(columnIndex);
            Log.d("MyActivity", "Column name: " + columnName);
            while (cursor.moveToNext()) {
                String userID = cursor.getString(columnIndex);

                Log.d("Test", "UserID: " + userID);
                return userID;
            }
            String userID = cursor.getString(columnIndex);
            return userID;
        }
        else {
            String userID = "0";
            return userID;
        }
    }

    public String getUserNAMELogin(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM User WHERE id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        if (cursor != null && cursor.getCount() > 0) {
            int columnIndex = cursor.getColumnIndex("hoTen");
            String columnName = cursor.getColumnName(columnIndex);
            Log.d("MyActivity", "Column name: " + columnName);
            while (cursor.moveToNext()) {
                String userID = cursor.getString(columnIndex);
                // Process user data here (e.g., using userID)
                Log.d("Test", "UserID: " + userID);
                return userID;
            }
            String userID = cursor.getString(columnIndex);
            return userID;
        } else {
            // Handle the case of a null or empty Cursor
            String userID = "0";
            return userID;
        }
    }

    // Hàm cho Rạp Phim
    public ArrayList<RapPhim> getRapPhim (){
        ArrayList<RapPhim> danhSachRapPhim = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM RapPhim", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenRap = cursor.getString(1);
            String diaChi = cursor.getString(2);
            String sdt = cursor.getString(3);

            RapPhim rapPhim = new RapPhim(id, tenRap, diaChi, sdt,false,1);

            danhSachRapPhim.add(rapPhim);
        }
        cursor.close();
        database.close();
        return danhSachRapPhim;
    }

    // Hàm cho Phim
    public ArrayList<Phim> getPhim (){
        ArrayList<Phim> listFilm = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Phim", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenPhim = cursor.getString(1);
            String theLoai = cursor.getString(3);
            String daoDien = cursor.getString(6);

           Phim phim = new Phim();
           phim.setTenPhim(tenPhim);
            phim.setDaoDien(daoDien);

           phim.setTheLoai(theLoai);


            listFilm.add(phim);
        }
        cursor.close();
        database.close();
        return listFilm;
    }

    // Hàm cho Ghế
    public ArrayList<Ghe> getGhe (){
        ArrayList<Ghe> listSeat = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Ghe", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenGhe = cursor.getString(1);
            String loaiGhe = cursor.getString(2);
            Ghe ghe = new Ghe();
            ghe.setTenGhe(tenGhe);
            ghe.setLoaiGhe(loaiGhe);

            listSeat.add(ghe);
        }
        cursor.close();
        database.close();
        return listSeat;
    }
}
