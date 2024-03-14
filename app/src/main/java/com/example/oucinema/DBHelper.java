package com.example.oucinema;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.oucinema.model.Role;
import com.example.oucinema.model.User;

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

        //Bảng Rạp Phim
        sqLiteDatabase.execSQL("create table RapPhim(" +
                "id INTEGER primary key autoincrement," +
                "tenRap TEXT NOT NULL, " +
                "diaChi TEXT NOT NULL," +
                "soDienThoaiLienHeTEXT NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER" +
                ");");

        //Bảng Ghế
        sqLiteDatabase.execSQL("create table Ghe(" +
                "id INTEGER  primary key autoincrement," +
                "tenGhe TEXT NOT NULL," +
                "loaiGhe TEXT NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER" +
                ");");

        //Bảng Phòng
        sqLiteDatabase.execSQL("create table Phong (" +
                "id INTEGER  primary key autoincrement," +
                "tenPhong TEXT NOT NULL," +
                "rapPhimID INTEGER NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER," +
                "foreign key (rapPhimID) references RapPhim(id)" +
                ");");

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

    public boolean addUser2(String hoTen,String phoneNumber,String email,
                           String gioiTinh, String username, String password,
                           Role roleID){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("hoTen",hoTen);
        cv.put("phoneNumber",phoneNumber);
        cv.put("email",email);
        cv.put("gioiTinh",gioiTinh);
        cv.put("username",username);
        cv.put("password",password);
        cv.put("roleID",roleID.getId());
        long user1 = db.insert("user", null, cv);
        if(user1 ==-1){
            return false;
        }
        else{
            return  true;
        }
    }
}
