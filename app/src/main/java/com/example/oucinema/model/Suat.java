package com.example.oucinema.model;

import java.sql.Date;
import java.sql.Time;

public class Suat {
    private int id;
    private String tenSuat;
    private Date ngayChieu;
    private Time gioChieu;
    private Double giaMacDinh;
    private Phim phimID;
    private Phong phongID;
    private Boolean isDelete;
    private int userUpdate;

    public Suat(int id, String tenSuat, Date ngayChieu, Time gioChieu, Double giaMacDinh, Phim phimID, Phong phongID, Boolean isDelete, int userUpdate) {
        this.id = id;
        this.tenSuat = tenSuat;
        this.ngayChieu = ngayChieu;
        this.gioChieu = gioChieu;
        this.giaMacDinh = giaMacDinh;
        this.phimID = phimID;
        this.phongID = phongID;
        this.isDelete = isDelete;
        this.userUpdate = userUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSuat() {
        return tenSuat;
    }

    public void setTenSuat(String tenSuat) {
        this.tenSuat = tenSuat;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public Time getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(Time gioChieu) {
        this.gioChieu = gioChieu;
    }

    public Double getGiaMacDinh() {
        return giaMacDinh;
    }

    public void setGiaMacDinh(Double giaMacDinh) {
        this.giaMacDinh = giaMacDinh;
    }

    public Phim getPhimID() {
        return phimID;
    }

    public void setPhimID(Phim phimID) {
        this.phimID = phimID;
    }

    public Phong getPhongID() {
        return phongID;
    }

    public void setPhongID(Phong phongID) {
        this.phongID = phongID;
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

    public Suat() {
    }
}
