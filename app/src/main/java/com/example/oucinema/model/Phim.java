package com.example.oucinema.model;

import java.sql.Blob;
import java.sql.Date;

public class Phim {
    private int id;
    private String tenPhim;
    private String moTa;
    private String theLoai;
    private int thoiLuong;
    private Date ngayPhatHanh;
    private String daoDien;
    private Blob hinhAnh;
    private String linkTrailer;
    private Boolean isDelete;
    private int userUpdate;

    public Phim(int id, String tenPhim, String moTa, String theLoai, int thoiLuong, Date ngayPhatHanh, String daoDien, Blob hinhAnh, String linkTrailer, Boolean isDelete, int userUpdate) {
        this.id = id;
        this.tenPhim = tenPhim;
        this.moTa = moTa;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.ngayPhatHanh = ngayPhatHanh;
        this.daoDien = daoDien;
        this.hinhAnh = hinhAnh;
        this.linkTrailer = linkTrailer;
        this.isDelete = isDelete;
        this.userUpdate = userUpdate;
    }

    public Phim() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public Date getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(Date ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public Blob getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(Blob hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getLinkTrailer() {
        return linkTrailer;
    }

    public void setLinkTrailer(String linkTrailer) {
        this.linkTrailer = linkTrailer;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public int getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(int userUpdate) {
        this.userUpdate = userUpdate;
    }
}
