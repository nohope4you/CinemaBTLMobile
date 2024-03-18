package com.example.oucinema;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.oucinema.model.Ghe;
import com.example.oucinema.model.MaGiamGia;
import com.example.oucinema.model.Phim;
import com.example.oucinema.model.Phong;
import com.example.oucinema.model.RapPhim;
import com.example.oucinema.model.Suat;
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
        sqLiteDatabase.execSQL("insert into Suat(ngayChieu,gioChieu,giaMacDinh,phimID,phongID,isDelete,userUpdate) values('2024-3-14','14:30:00',45000,1,1,false,1) ");
        sqLiteDatabase.execSQL("insert into Suat(ngayChieu,gioChieu,giaMacDinh,phimID,phongID,isDelete,userUpdate) values('2024-3-14','15:30:00',45000,1,2,false,1) ");
        sqLiteDatabase.execSQL("insert into Suat(ngayChieu,gioChieu,giaMacDinh,phimID,phongID,isDelete,userUpdate) values('2024-3-14','15:30:00',45000,2,2,false,1) ");
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
// Thêm rạp
    public boolean addTheater(RapPhim theater){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tenRap",theater.getTenRap());
        cv.put("diaChi",theater.getDiaChi());
        cv.put("soDienThoaiLienHe",theater.getSoDienThoaiLienHe());
        cv.put("isDelete",false);
        cv.put("userUpdate",1);

        long theater1 = db.insert("RapPhim", null, cv);
        if(theater1 ==-1){
            return false;
        }
        else{
            return  true;
        }
    }


    // Hàm cho Phim
    public ArrayList<Phim> getPhim (){
        ArrayList<Phim> listFilm = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Phim", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenPhim = cursor.getString(1);
            String moTa = cursor.getString(2);
            String theLoai = cursor.getString(3);
            String daoDien = cursor.getString(6);
            int thoiLuong = cursor.getInt(4);
            String thoiGianPhatHanh = cursor.getString(5);
            java.sql.Date date = java.sql.Date.valueOf(thoiGianPhatHanh.toString());

           Phim phim = new Phim();
           phim.setId(id);
           phim.setTenPhim(tenPhim);
           phim.setMoTa(moTa);
            phim.setDaoDien(daoDien);
            phim.setThoiLuong(thoiLuong);

           phim.setTheLoai(theLoai);
           phim.setNgayPhatHanh(date);

            listFilm.add(phim);
        }
        cursor.close();
        database.close();
        return listFilm;
    }

    public boolean addFilm(Phim phim){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayPhatHanhString = sdf.format(phim.getNgayPhatHanh());

        cv.put("tenPhim",phim.getTenPhim());
        cv.put("moTa",phim.getMoTa());
        cv.put("theLoai",phim.getTheLoai());
        cv.put("thoiLuong",phim.getThoiLuong());
        cv.put("ngayPhatHanh",ngayPhatHanhString);
        cv.put("daoDien",phim.getDaoDien());
        cv.put("hinhAnh","test");
        cv.put("linkTrailer",phim.getLinkTrailer());
        cv.put("isDelete",false);
        cv.put("userUpdate",1);

        long phim1 = db.insert("Phim", null, cv);
        if(phim1 ==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    public boolean updateFilm (Phim phim, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayPhatHanhString = sdf.format(phim.getNgayPhatHanh());

        cv.put("tenPhim",phim.getTenPhim());
        cv.put("moTa",phim.getMoTa());
        cv.put("theLoai",phim.getTheLoai());
        cv.put("thoiLuong",phim.getThoiLuong());
        cv.put("ngayPhatHanh",ngayPhatHanhString);
        cv.put("daoDien",phim.getDaoDien());
        cv.put("hinhAnh","test");
        cv.put("linkTrailer",phim.getLinkTrailer());
        cv.put("isDelete",false);
        cv.put("userUpdate",1);
        long phim1 = db.update("Phim",cv,"id= "+id,null);
        if(phim1 ==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    // Hàm cho Ghế
    public ArrayList<Ghe> getGhe (){
        ArrayList<Ghe> listSeat = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Ghe WHERE isDelete=0", null);

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
    public boolean addSeat(Ghe ghe){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tenGhe",ghe.getTenGhe());
        cv.put("loaiGhe",ghe.getLoaiGhe());
        cv.put("isDelete",false);
        cv.put("userUpdate",1);

        long ghe1 = db.insert("Ghe", null, cv);
        if(ghe1 ==-1){
            return false;
        }
        else{
            return  true;
        }
    }

//    public boolean deleteGhe(Ghe ghe){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("isDelete",ghe.getDelete());
//        long ghe1 = db.update("Ghe",cv,"id"+"="+ghe.getId(),null);
//        if(ghe1 ==-1){
//            return false;
//        }
//        else{
//            return  true;
//        }
//    }


    // Hàm cho User
    public ArrayList<User> getAllUser (){
        ArrayList<User> listUser = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM User", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String hotenUser = cursor.getString(1);
            String username = cursor.getString(6);
            User u = new User();
            u.setId(id);
            u.setHoTen(hotenUser);
            u.setUsername(username);

            listUser.add(u);
        }
        cursor.close();
        database.close();
        return listUser;
    }

    // Hàm cho SetFilm
    public ArrayList<Suat> getSetFilm (){
        ArrayList<Suat> listSuat = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT Suat.*, Phim.tenPhim, Phong.tenPhong " +
                "FROM Suat INNER JOIN Phim ON Suat.phimID = Phim.id INNER JOIN Phong ON Suat.phongID = Phong.id", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
           int phimID =cursor.getInt(4);
            int phongID = cursor.getInt(5);
            double gia = cursor.getDouble(3);


            Suat s = new Suat();
            Phim p = new Phim();

            Phong phong = new Phong();
            String namePhim = cursor.getString(8);
            String namePhong = cursor.getString(9);
//            Log.d("Test", "UserID: " + namePhong);
            p.setId(phimID);
            p.setTenPhim(namePhim);

            phong.setId(phongID);
            phong.setTenPhong(namePhong);

            s.setPhimID(p);
            s.setPhongID(phong);
            s.setGiaMacDinh(gia);
            listSuat.add(s);

        }
        cursor.close();
        database.close();
        return listSuat;
    }

    // Thêm suất phim
    public boolean addSetFilm(Suat suat){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayChieuString = sdf.format(suat.getNgayChieu());
        SimpleDateFormat sdfh = new SimpleDateFormat("hh:mm:ss");
        String gioChieuString = sdfh.format(suat.getGioChieu());

        cv.put("ngayChieu", ngayChieuString);
        cv.put("gioChieu",gioChieuString);
        cv.put("giaMacDinh",suat.getGiaMacDinh());
        int idPhim= suat.getPhimID().getId();
        int idPhong = suat.getPhongID().getId();
        cv.put("phimID",idPhim);
        cv.put("phongID",idPhong);
        cv.put("isDelete",false);
        cv.put("userUpdate",1);

        long suat1 = db.insert("Suat", null, cv);
        if(suat1 ==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    // Hàm cho Phòng
    public ArrayList<Phong> getPhong (){
        ArrayList<Phong> listPhong = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT Phong.*,RapPhim.tenRap,RapPhim.id FROM Phong,RapPhim" +
                " WHERE Phong.rapPhimID = RapPhim.id", null);

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
           listPhong.add(p);

            Log.d("Test", "UserID: " + r.getTenRap());


        }
        cursor.close();
        database.close();
        return listPhong;
    }
    // Thêm phòng
    public boolean addRoom(Phong room){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int idRap = room.getRapPhimID().getId();
        cv.put("tenPhong",room.getTenPhong());
        cv.put("rapPhimID",idRap);
        cv.put("isDelete",false);
        cv.put("userUpdate",1);

        long room1 = db.insert("Phong", null, cv);
        if(room1 ==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    // Hàm cho Mã giảm giá
    public ArrayList<MaGiamGia> getGoupon (){
        ArrayList<MaGiamGia> listCoupon = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM MaGiamGia", null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String tenMG = cursor.getString(1);
            String phantramMG = cursor.getString(2);
            String thoigianMG = cursor.getString(3);
            MaGiamGia mgg = new MaGiamGia();
            java.sql.Date date = java.sql.Date.valueOf(thoigianMG.toString());
            Log.d("MyActivity", "MaGiamGia: " + tenMG + phantramMG + date);
            mgg.setTenMaGiam(tenMG);
            mgg.setPhanTramGiam(Integer.parseInt(phantramMG));
            mgg.setThoiGianHieuLuc(date);

            listCoupon.add(mgg);
        }
        cursor.close();
        database.close();
        return listCoupon;
    }

    public boolean addCoupon(MaGiamGia mgg){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayPhatHanhString = sdf.format(mgg.getThoiGianHieuLuc());
        cv.put("tenMaGiam",mgg.getTenMaGiam());
        cv.put("phanTramGiam",mgg.getPhanTramGiam());
        cv.put("thoiGianHieuLuc",ngayPhatHanhString);
        cv.put("isDelete",false);
        cv.put("userUpdate",1);

        long mgg1 = db.insert("MaGiamGia", null, cv);
        if(mgg1 ==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    // Hàm cho Vé
    public ArrayList<Ve> getTicket (){
        ArrayList<Ve> listTicket = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT Ve.*,Suat.ngayChieu,Ghe.tenGhe,User.hoTen FROM Ve,Suat,Ghe,User " +
                "WHERE Ve.suatID = Suat.id AND Ve.gheID=Ghe.id AND Ve.userID = User.id", null);

        while (cursor.moveToNext()) {
            User u = new User();
            Ghe ghe = new Ghe();
            Suat suat = new Suat();
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

            u.setHoTen(hoten);
            ghe.setTenGhe(tenGhe);
            suat.setNgayChieu(ngayChieu);

            ve.setId(id);
            ve.setGheID(ghe);
            ve.setUserID(u);
            ve.setSuatID(suat);
            ve.setGiaTien(giatien);
            ve.setHinhThuc(hinhThuc);
            ve.setThoiGianDat(ngayDat);
            listTicket.add(ve);
        }
        cursor.close();
        database.close();
        return listTicket;
    }
}
