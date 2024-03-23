package com.example.oucinema;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.oucinema.model.DanhGia;
import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Phong;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Role;
import com.example.oucinema.model.Suat;
import com.example.oucinema.model.TempModel;
import com.example.oucinema.model.User;
import com.example.oucinema.model.Ve;

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
                "gioiTinh TEXT," +
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
        sqLiteDatabase.execSQL("insert into Phim(tenPhim,moTa,theLoai,thoiLuong,ngayPhatHanh,daoDien,hinhAnh,linkTrailer,isDelete,userUpdate) " +
                "                         values('Harry Potter','Phim về Harry','Siêu nhiên',120,'2024-3-13','Trấn Thành','hinhanh','linktrailer',false,1) ");
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
                "tenSuat TEXT NOT NULL," +
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
        sqLiteDatabase.execSQL("insert into Suat(tenSuat,ngayChieu,gioChieu,giaMacDinh,phimID,phongID,isDelete,userUpdate) values('Suất 1','2024-3-14','14:30:00',45000,1,1,false,1) ");
        sqLiteDatabase.execSQL("insert into Suat(tenSuat,ngayChieu,gioChieu,giaMacDinh,phimID,phongID,isDelete,userUpdate) values('Suất 2','2024-3-14','15:30:00',45000,1,2,false,1) ");
        sqLiteDatabase.execSQL("insert into Suat(tenSuat,ngayChieu,gioChieu,giaMacDinh,phimID,phongID,isDelete,userUpdate) values('Suất 3','2024-3-14','15:30:00',45000,2,2,false,1) ");
        //Bảng Mã giảm giá
        sqLiteDatabase.execSQL("create table MaGiamGia (" +
                "id INTEGER primary key autoincrement," +
                "tenMaGiam TEXT NOT NULL," +
                "phanTramGiam INTEGER NOT NULL," +
                "thoiGianHieuLuc DATE NOT NULL," +
                "isDelete BOOLEAN," +
                "userUpdate INTEGER" +
                ");");
        sqLiteDatabase.execSQL("insert into MaGiamGia(tenMaGiam,phanTramGiam,thoiGianHieuLuc,isDelete,userUpdate)values ('Admin',30,'2024-12-24',false,1)");
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

        sqLiteDatabase.execSQL("insert into Ve(suatID,gheID,giamGiaID,userID,giaTien,thoiGianDat,hinhThuc,isDelete,userUpdate)" +
                "values (2,4,1,2,45000,'2024-3-15','Chuyển khoản',false,2)");

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
        sqLiteDatabase.execSQL("insert into User(hoTen,phoneNumber,email,gioiTinh,username,password,roleID)values ('test1','123456789','Admin@gmail.com','Nam','user','123',1)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("hoTen", user.getHoTen());
        cv.put("phoneNumber", user.getPhoneNumber());
        cv.put("email", user.getEmail());
        cv.put("gioiTinh", user.getGioiTinh());
        cv.put("username", user.getUsername());
        cv.put("password", user.getPassword());
        cv.put("roleID", user.getRoleID().getId());
        long user1 = db.insert("user", null, cv);
        if (user1 == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean updateUser(User user, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("hoTen", user.getHoTen());
        cv.put("phoneNumber", user.getPhoneNumber());
        cv.put("email", user.getEmail());
        cv.put("gioiTinh", "Nam");
        cv.put("username", user.getUsername());
        cv.put("password", user.getPassword());
        cv.put("roleID", user.getRoleID().getId());

        long user1 = db.update("User", cv, "id= " + id, null);
        if (user1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUserExist(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where username = ? ", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // Hàm login
    public boolean userLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where username =? and password = ? ", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkRoleUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where username =? and password =? and roleID ==2 ", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
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
        } else {
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
    public ArrayList<RapPhim> getRapPhim() {
        ArrayList<RapPhim> danhSachRapPhim = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM RapPhim WHERE isDelete=false", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenRap = cursor.getString(1);
            String diaChi = cursor.getString(2);
            String sdt = cursor.getString(3);

            RapPhim rapPhim = new RapPhim(id, tenRap, diaChi, sdt, false, 1);

            danhSachRapPhim.add(rapPhim);
        }
        cursor.close();
        database.close();
        return danhSachRapPhim;
    }

    // Thêm rạp
    public boolean addTheater(RapPhim theater) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tenRap", theater.getTenRap());
        cv.put("diaChi", theater.getDiaChi());
        cv.put("soDienThoaiLienHe", theater.getSoDienThoaiLienHe());
        cv.put("isDelete", false);
        cv.put("userUpdate", theater.getUserUpdate());

        long theater1 = db.insert("RapPhim", null, cv);
        if (theater1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateRap(RapPhim theater, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tenRap", theater.getTenRap());
        cv.put("diaChi", theater.getDiaChi());
        cv.put("soDienThoaiLienHe", theater.getSoDienThoaiLienHe());
        cv.put("isDelete", false);
        cv.put("userUpdate", theater.getUserUpdate());


        long theater1 = db.update("RapPhim", cv, "id= " + id, null);
        if (theater1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteRap(RapPhim theater, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("isDelete", true);
        cv.put("userUpdate", theater.getUserUpdate());
        long theater1 = db.update("RapPhim", cv, "id= " + id, null);
        if (theater1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Hàm cho Phim
    public ArrayList<Phim> getPhim() {
        ArrayList<Phim> listFilm = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Phim WHERE isDelete=false", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenPhim = cursor.getString(1);
            String moTa = cursor.getString(2);
            String theLoai = cursor.getString(3);
            String daoDien = cursor.getString(6);
            String hinhAnh = cursor.getString(7);
            int thoiLuong = cursor.getInt(4);
            String thoiGianPhatHanh = cursor.getString(5);
            java.sql.Date date = java.sql.Date.valueOf(thoiGianPhatHanh.toString());

            Phim phim = new Phim();
            phim.setId(id);
            phim.setTenPhim(tenPhim);
            phim.setMoTa(moTa);
            phim.setDaoDien(daoDien);
            phim.setThoiLuong(thoiLuong);
            phim.setHinhAnh(hinhAnh);
            phim.setTheLoai(theLoai);
            phim.setNgayPhatHanh(date);

            listFilm.add(phim);
        }
        cursor.close();
        database.close();
        return listFilm;
    }

    // Hàm cho user lấy 4 phim mới nhất
    public ArrayList<Phim> getPhimfornewfilmUser() {
        ArrayList<Phim> listFilm = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Phim WHERE isDelete=false ORDER BY ngayPhatHanh DESC LIMIT 4", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenPhim = cursor.getString(1);
            String moTa = cursor.getString(2);
            String theLoai = cursor.getString(3);
            String daoDien = cursor.getString(6);
            String hinhAnh = cursor.getString(7);
            int thoiLuong = cursor.getInt(4);
            String thoiGianPhatHanh = cursor.getString(5);
            java.sql.Date date = java.sql.Date.valueOf(thoiGianPhatHanh.toString());

            Phim phim = new Phim();
            phim.setId(id);
            phim.setTenPhim(tenPhim);
            phim.setMoTa(moTa);
            phim.setDaoDien(daoDien);
            phim.setThoiLuong(thoiLuong);
            phim.setHinhAnh(hinhAnh);
            phim.setTheLoai(theLoai);
            phim.setNgayPhatHanh(date);

            listFilm.add(phim);
        }
        cursor.close();
        database.close();
        return listFilm;
    }


    public boolean addFilm(Phim phim) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayPhatHanhString = sdf.format(phim.getNgayPhatHanh());

        cv.put("tenPhim", phim.getTenPhim());
        cv.put("moTa", phim.getMoTa());
        cv.put("theLoai", phim.getTheLoai());
        cv.put("thoiLuong", phim.getThoiLuong());
        cv.put("ngayPhatHanh", ngayPhatHanhString);
        cv.put("daoDien", phim.getDaoDien());
        cv.put("hinhAnh", phim.getHinhAnh());
        cv.put("linkTrailer", phim.getLinkTrailer());
        cv.put("isDelete", false);
        cv.put("userUpdate", phim.getUserUpdate());

        long phim1 = db.insert("Phim", null, cv);
        if (phim1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateFilm(Phim phim, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayPhatHanhString = sdf.format(phim.getNgayPhatHanh());

        cv.put("tenPhim", phim.getTenPhim());
        cv.put("moTa", phim.getMoTa());
        cv.put("theLoai", phim.getTheLoai());
        cv.put("thoiLuong", phim.getThoiLuong());
        cv.put("ngayPhatHanh", ngayPhatHanhString);
        cv.put("daoDien", phim.getDaoDien());
        cv.put("hinhAnh", phim.getHinhAnh());
        cv.put("linkTrailer", phim.getLinkTrailer());
        cv.put("isDelete", false);
        cv.put("userUpdate", phim.getUserUpdate());
        long phim1 = db.update("Phim", cv, "id= " + id, null);
        if (phim1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteFilm(Phim phim, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("isDelete", true);
        cv.put("userUpdate", phim.getUserUpdate());
        long phim1 = db.update("Phim", cv, "id= " + id, null);
        if (phim1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Hàm cho Ghế
    public ArrayList<Ghe> getGhe() {
        ArrayList<Ghe> listSeat = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Ghe WHERE isDelete=0", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenGhe = cursor.getString(1);
            String loaiGhe = cursor.getString(2);
            Ghe ghe = new Ghe();
            ghe.setId(id);
            ghe.setTenGhe(tenGhe);
            ghe.setLoaiGhe(loaiGhe);

            listSeat.add(ghe);
        }
        cursor.close();
        database.close();
        return listSeat;
    }

    public boolean addSeat(Ghe ghe) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tenGhe", ghe.getTenGhe());
        cv.put("loaiGhe", ghe.getLoaiGhe());
        cv.put("isDelete", false);
        cv.put("userUpdate", ghe.getUserUpdate());

        long ghe1 = db.insert("Ghe", null, cv);
        if (ghe1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateGhe(Ghe ghe, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tenGhe", ghe.getTenGhe());
        cv.put("loaiGhe", ghe.getLoaiGhe());
        cv.put("isDelete", false);
        cv.put("userUpdate", ghe.getUserUpdate());


        long phim1 = db.update("Ghe", cv, "id= " + id, null);
        if (phim1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteGhe(Ghe ghe, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("isDelete", true);
        cv.put("userUpdate", ghe.getUserUpdate());


        long phim1 = db.update("Ghe", cv, "id= " + id, null);
        if (phim1 == -1) {
            return false;
        } else {
            return true;
        }
    }


    //Hàm Role
    public ArrayList<Role> getRole() {
        ArrayList<Role> listRole = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Role", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String rolename = cursor.getString(1);
            Role r = new Role();

            r.setId(id);
            r.setNameRole(rolename);
            listRole.add(r);
        }
        cursor.close();
        database.close();
        return listRole;
    }
    // Hàm cho User
    public ArrayList<User> getAllUser() {
        ArrayList<User> listUser = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM User", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String hotenUser = cursor.getString(1);
            String username = cursor.getString(6);
            String pass = cursor.getString(7);
            String email = cursor.getString(3);
            String sdt = cursor.getString(2);
            int roleID = cursor.getInt(7);
            Role r = new Role();
            r.setId(roleID);
            User u = new User();
            u.setId(id);
            u.setHoTen(hotenUser);
            u.setUsername(username);
            u.setPassword(pass);
            u.setEmail(email);
            u.setPhoneNumber(sdt);
            u.setRoleID(r);
            listUser.add(u);
        }
        cursor.close();
        database.close();
        return listUser;
    }

    // Hàm cho Suất
    public ArrayList<Suat> getSetFilm() {
        ArrayList<Suat> listSuat = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT Suat.*, Phim.tenPhim, Phong.tenPhong " +
                "FROM Suat INNER JOIN Phim ON Suat.phimID = Phim.id INNER JOIN Phong ON Suat.phongID = Phong.id WHERE Suat.isDelete=false", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            int phimID = cursor.getInt(5);
            int phongID = cursor.getInt(6);
            double gia = cursor.getDouble(4);
            String nameSuat = cursor.getString(1);
            String ngaychieu= cursor.getString(2);
            String giochieu =cursor.getString(3);

            java.sql.Date ngayChieu = java.sql.Date.valueOf(ngaychieu.toString());
            java.sql.Time gioChieu = java.sql.Time.valueOf(giochieu.toString());
            SimpleDateFormat sdfh = new SimpleDateFormat("hh:mm:ss");
            String gioChieuString = sdfh.format(gioChieu);
            Suat s = new Suat();
            Phim p = new Phim();

            Phong phong = new Phong();
            String namePhim = cursor.getString(9);
            String namePhong = cursor.getString(10);
//            Log.d("Test", "UserID: " + namePhong);
            p.setId(phimID);
            p.setTenPhim(namePhim);

            phong.setId(phongID);
            phong.setTenPhong(namePhong);
            s.setId(id);
            s.setTenSuat(nameSuat);
            s.setPhimID(p);
            s.setPhongID(phong);
            s.setNgayChieu(ngayChieu);
            s.setGioChieu(gioChieu);
            s.setGiaMacDinh(gia);

            listSuat.add(s);

        }
        cursor.close();
        database.close();
        return listSuat;
    }

    // Hàm cho Suất theo phim
    public ArrayList<Suat> getSetFilmUser(String filmid , String rapid) {
        ArrayList<Suat> listSuat = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT Suat.* FROM Suat ,RapPhim, Phong WHERE Suat.isDelete=false AND Suat.phimID = ? AND RapPhim.id = ? AND Phong.rapPhimID = RapPhim.id AND Phong.id = Suat.phongID;", new String[]{filmid,rapid});

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            int phimID = cursor.getInt(5);
            int phongID = cursor.getInt(6);
            double gia = cursor.getDouble(4);
            String nameSuat = cursor.getString(1);
            String ngaychieu= cursor.getString(2);
            String giochieu =cursor.getString(3);

            java.sql.Date ngayChieu = java.sql.Date.valueOf(ngaychieu.toString());
            java.sql.Time gioChieu = java.sql.Time.valueOf(giochieu.toString());
            SimpleDateFormat sdfh = new SimpleDateFormat("hh:mm:ss");
            String gioChieuString = sdfh.format(gioChieu);
            Suat s = new Suat();
            Phim p = new Phim();

            Phong phong = new Phong();
//            Log.d("Test", "UserID: " + namePhong);
            p.setId(phimID);
            phong.setId(phongID);
            s.setId(id);
            s.setTenSuat(nameSuat);
            s.setPhimID(p);
            s.setPhongID(phong);
            s.setNgayChieu(ngayChieu);
            s.setGioChieu(gioChieu);
            s.setGiaMacDinh(gia);

            listSuat.add(s);

        }
        cursor.close();
        database.close();
        return listSuat;
    }

    // Thêm suất phim
    public boolean addSetFilm(Suat suat) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayChieuString = sdf.format(suat.getNgayChieu());
        SimpleDateFormat sdfh = new SimpleDateFormat("hh:mm:ss");
        String gioChieuString = sdfh.format(suat.getGioChieu());

        cv.put("tenSuat","Testt");
        cv.put("ngayChieu", ngayChieuString);
        cv.put("gioChieu", gioChieuString);
        cv.put("giaMacDinh", suat.getGiaMacDinh());
        int idPhim = suat.getPhimID().getId();
        int idPhong = suat.getPhongID().getId();
        cv.put("phimID", idPhim);
        cv.put("phongID", idPhong);
        cv.put("isDelete", false);
        cv.put("userUpdate", suat.getUserUpdate());

        long suat1 = db.insert("Suat", null, cv);
        if (suat1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateSuat(Suat suat, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayChieuString = sdf.format(suat.getNgayChieu());
        SimpleDateFormat sdfh = new SimpleDateFormat("hh:mm:ss");
        String gioChieuString = sdfh.format(suat.getGioChieu());

        cv.put("ngayChieu", ngayChieuString);
        cv.put("gioChieu", gioChieuString);
        cv.put("giaMacDinh", suat.getGiaMacDinh());
        int idPhim = suat.getPhimID().getId();
        int idPhong = suat.getPhongID().getId();
        cv.put("phimID", idPhim);
        cv.put("phongID", idPhong);
        cv.put("isDelete", false);
        cv.put("userUpdate", suat.getUserUpdate());

        long suat1 = db.update("Suat", cv, "id= " + id, null);
        if (suat1 == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean deleteSuat(Suat suat, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("isDelete", true);
        cv.put("userUpdate", suat.getUserUpdate());
        long suat1 = db.update("Suat", cv, "id= " + id, null);
        if (suat1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Hàm cho Phòng
    public ArrayList<Phong> getPhong() {
        ArrayList<Phong> listPhong = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT Phong.*,RapPhim.tenRap,RapPhim.id FROM Phong,RapPhim" +
                " WHERE Phong.rapPhimID = RapPhim.id AND Phong.isDelete =false", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenPhong = cursor.getString(1);
            String tenRap = cursor.getString(5);
            int idRap = cursor.getInt(6);

            RapPhim r = new RapPhim();
            r.setId(idRap);
            r.setTenRap(tenRap);
            Phong p = new Phong();
            p.setTenPhong(tenPhong);
            p.setId(id);
            p.setRapPhimID(r);
            listPhong.add(p);

            Log.d("Test", "UserID: " + r.getTenRap());


        }
        cursor.close();
        database.close();
        return listPhong;
    }

    // Thêm phòng
    public boolean addRoom(Phong room) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int idRap = room.getRapPhimID().getId();
        cv.put("tenPhong", room.getTenPhong());
        cv.put("rapPhimID", idRap);
        cv.put("isDelete", false);
        cv.put("userUpdate", room.getUserUpdate());

        long room1 = db.insert("Phong", null, cv);
        if (room1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updatePhong(Phong phong, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int idRap = phong.getRapPhimID().getId();
        cv.put("tenPhong", phong.getTenPhong());
        cv.put("rapPhimID", idRap);
        cv.put("isDelete", false);
        cv.put("userUpdate", phong.getUserUpdate());

        long room1 = db.update("Phong", cv, "id= " + id, null);
        if (room1 == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean deletePhong(Phong phong, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("isDelete", true);
        cv.put("userUpdate", phong.getUserUpdate());

        long room1 = db.update("Phong", cv, "id= " + id, null);
        if (room1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Hàm cho Mã giảm giá
    public ArrayList<MaGiamGia> getGoupon() {
        ArrayList<MaGiamGia> listCoupon = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM MaGiamGia WHERE isDelete=false", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenMG = cursor.getString(1);
            String phantramMG = cursor.getString(2);
            String thoigianMG = cursor.getString(3);
            MaGiamGia mgg = new MaGiamGia();
            java.sql.Date date = java.sql.Date.valueOf(thoigianMG.toString());
            Log.d("MyActivity", "MaGiamGia: " + tenMG + phantramMG + date);
            mgg.setId(id);
            mgg.setTenMaGiam(tenMG);
            mgg.setPhanTramGiam(Integer.parseInt(phantramMG));
            mgg.setThoiGianHieuLuc(date);

            listCoupon.add(mgg);
        }
        cursor.close();
        database.close();
        return listCoupon;
    }
    public boolean addCoupon(MaGiamGia mgg) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayPhatHanhString = sdf.format(mgg.getThoiGianHieuLuc());
        cv.put("tenMaGiam", mgg.getTenMaGiam());
        cv.put("phanTramGiam", mgg.getPhanTramGiam());
        cv.put("thoiGianHieuLuc", ngayPhatHanhString);
        cv.put("isDelete", false);
        cv.put("userUpdate", mgg.getUserUpdate());

        long mgg1 = db.insert("MaGiamGia", null, cv);
        if (mgg1 == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean updateMgg(MaGiamGia mgg, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayPhatHanhString = sdf.format(mgg.getThoiGianHieuLuc());
        cv.put("tenMaGiam", mgg.getTenMaGiam());
        cv.put("phanTramGiam", mgg.getPhanTramGiam());
        cv.put("thoiGianHieuLuc", ngayPhatHanhString);
        cv.put("isDelete", false);
        cv.put("userUpdate", 1);


        long mgg1 = db.update("MaGiamGia", cv, "id= " + id, null);
        if (mgg1 == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean deleteMgg(MaGiamGia mgg, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String ngayPhatHanhString = sdf.format(mgg.getThoiGianHieuLuc());
//        cv.put("tenMaGiam", mgg.getTenMaGiam());
//        cv.put("phanTramGiam", mgg.getPhanTramGiam());
//        cv.put("thoiGianHieuLuc", ngayPhatHanhString);
        cv.put("isDelete", true);
        cv.put("userUpdate", 1);
        long mgg1 = db.update("MaGiamGia", cv, "id= " + id, null);
        if (mgg1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Hàm cho Vé
    public ArrayList<Ve> getTicket() {
        ArrayList<Ve> listTicket = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT Ve.*,Suat.ngayChieu,Ghe.tenGhe,User.hoTen,MaGiamGia.tenMaGiam FROM Ve,Suat,Ghe,User,MaGiamGia " +
                "WHERE Ve.suatID = Suat.id AND Ve.gheID=Ghe.id AND Ve.userID = User.id AND Ve.giamGiaID=MaGiamGia.id AND Ve.isDelete=false", null);

        while (cursor.moveToNext()) {
            User u = new User();
            Ghe ghe = new Ghe();
            Suat suat = new Suat();
            MaGiamGia m = new MaGiamGia();
            Ve ve = new Ve();

            int id = cursor.getInt(0);
            String hoten = cursor.getString(12);
            String tenGhe = cursor.getString(11);
            Double giatien = cursor.getDouble(5);
            String ngaychieu = cursor.getString(10);

            java.sql.Date ngayChieu = java.sql.Date.valueOf(ngaychieu.toString());
            String ngaydat = cursor.getString(6);
            java.sql.Date ngayDat = java.sql.Date.valueOf(ngaydat.toString());
            String hinhThuc = cursor.getString(7);
            int idMa = cursor.getInt(3);
            int gheID = cursor.getInt(2);
            int suatID = cursor.getInt(1);
            int userID = cursor.getInt(4);
            u.setHoTen(hoten);
            u.setId(userID);

            ghe.setTenGhe(tenGhe);
            ghe.setId(gheID);

            suat.setNgayChieu(ngayChieu);
            suat.setId(suatID);

            m.setId(idMa);

            ve.setId(id);
            ve.setGheID(ghe);
            ve.setUserID(u);
            ve.setSuatID(suat);
            ve.setMaID(m);

            ve.setGiaTien(giatien);
            ve.setHinhThuc(hinhThuc);
            ve.setThoiGianDat(ngayDat);
            listTicket.add(ve);
        }
        cursor.close();
        database.close();
        return listTicket;
    }

    public boolean updateVe(Ve ve, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
       cv.put("suatID",ve.getSuatID().getId());
       cv.put("gheID",ve.getGheID().getId());
       cv.put("giamGiaID",ve.getMaID().getId());
       cv.put("userID",ve.getUserID().getId());
       cv.put("giaTien",ve.getGiaTien());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String thoiGianDat = sdf.format(ve.getThoiGianDat());
        cv.put("thoiGianDat",thoiGianDat);
        cv.put("hinhThuc",ve.getHinhThuc());
        cv.put("isDelete", true);
        cv.put("userUpdate", ve.getUserUpdate());


        long ve1 = db.update("Ve", cv, "id= " + id, null);
        if (ve1 == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean deleteVe(Ve ve, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("isDelete", true);
        cv.put("userUpdate", ve.getUserUpdate());


        long ve1 = db.update("Ve", cv, "id= " + id, null);
        if (ve1 == -1) {
            return false;
        } else {
            return true;
        }
    }


    // Hàm cho thống kê
    public ArrayList<TempModel> getStatisMovie() {
        ArrayList<TempModel> listTemp = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT Phim.tenPhim as 'tenPhim', count(ve.id) as 'Number' FROM Phim LEFT JOIN Suat ON Phim.id = Suat.phimID LEFT JOIN Ve ON  Suat.id = Ve.suatID GROUP BY Phim.id, Phim.tenPhim",null);

        while (cursor.moveToNext()) {

            TempModel temp = new TempModel();
            temp.setNameMovie(cursor.getString(0));
            temp.setNumber(cursor.getString(1));



            listTemp.add(temp);
        }
        cursor.close();
        database.close();
        return listTemp;
    }
    public ArrayList<TempModel> getStatisTheater() {
        ArrayList<TempModel> listTemp = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT RapPhim.tenRap as 'tenRap', count(Ve.id) as 'Number' " +
                "FROM RapPhim " +
                "LEFT JOIN Phong ON Phong.rapPhimID = RapPhim.id " +
                "LEFT JOIN Suat ON Suat.phongID = Phong.id " +
                "LEFT JOIN Ve ON Ve.suatID = Suat.id " +
                "GROUP BY RapPhim.id,RapPhim.tenRap",null);

        while (cursor.moveToNext()) {

            TempModel temp = new TempModel();
            temp.setNameMovie(cursor.getString(0));
            temp.setNumber(cursor.getString(1));
            listTemp.add(temp);
        }
        cursor.close();
        database.close();
        return listTemp;
    }

    // bình luận đánh giá
    public boolean addCommentandRate(DanhGia dg) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("danhGia", dg.getDanhGia());
        cv.put("nguoiDanhGia", dg.getNguoiDanhGia());
        cv.put("rating", dg.getRating());
        cv.put("phimID", dg.getPhimID().getId());

        long rate1 = db.insert("DanhGia", null, cv);
        if (rate1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Hàm cho bình luận đánh giá
    public ArrayList<DanhGia> getDanhGia(String idPhim) {
        ArrayList<DanhGia> listDanhGia = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM DanhGia WHERE DanhGia.phimID=?", new String[]{idPhim});

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String danhGia = cursor.getString(1);
            String nguoiDanhGia = cursor.getString(2);
            Double rating = cursor.getDouble(3);

            DanhGia dg = new DanhGia();
            dg.setId(id);
            dg.setDanhGia(danhGia);
            dg.setNguoiDanhGia(nguoiDanhGia);
            dg.setRating(rating);

            listDanhGia.add(dg);
        }
        cursor.close();
        database.close();
        return listDanhGia;
    }




}
