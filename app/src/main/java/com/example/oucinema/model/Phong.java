package com.example.oucinema.model;

public class Phong {
    private int id;
    private String tenPhong;
    private RapPhim rapPhimID;
    private Boolean isDelete;
    private int userUpdate;

    public Phong(int id, String tenPhong, RapPhim rapPhimID, Boolean isDelete, int userUpdate) {
        this.id = id;
        this.tenPhong = tenPhong;
        this.rapPhimID = rapPhimID;
        this.isDelete = isDelete;
        this.userUpdate = userUpdate;
    }

    public Phong() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public RapPhim getRapPhimID() {
        return rapPhimID;
    }

    public void setRapPhimID(RapPhim rapPhimID) {
        this.rapPhimID = rapPhimID;
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
