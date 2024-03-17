package com.example.oucinema.model;

import java.sql.Date;

public class Ve {
    private int id;
    private Suat suatID;
    private Ghe gheID;
    private MaGiamGia maID;
    private User userID;
    private Double giaTien;
    private Date thoiGianDat;
    private String hinhThuc;
    private Boolean isDelete;
    private int userUpdate;

    public Ve(int id, Suat suatID, Ghe gheID, MaGiamGia maID, User userID, Double giaTien, Date thoiGianDat, String hinhThuc, Boolean isDelete, int userUpdate) {
        this.id = id;
        this.suatID = suatID;
        this.gheID = gheID;
        this.maID = maID;
        this.userID = userID;
        this.giaTien = giaTien;
        this.thoiGianDat = thoiGianDat;
        this.hinhThuc = hinhThuc;
        this.isDelete = isDelete;
        this.userUpdate = userUpdate;
    }

    public Ve() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Suat getSuatID() {
        return suatID;
    }

    public void setSuatID(Suat suatID) {
        this.suatID = suatID;
    }

    public Ghe getGheID() {
        return gheID;
    }

    public void setGheID(Ghe gheID) {
        this.gheID = gheID;
    }

    public MaGiamGia getMaID() {
        return maID;
    }

    public void setMaID(MaGiamGia maID) {
        this.maID = maID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public Date getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(Date thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
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
