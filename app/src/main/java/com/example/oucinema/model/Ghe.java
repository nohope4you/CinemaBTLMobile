package com.example.oucinema.model;

public class Ghe {
    private int id;
    private String tenGhe;
    private Boolean isDelete;
    private int userUpdate;

    public Ghe(int id, String tenGhe, Boolean isDelete, int userUpdate) {
        this.id = id;
        this.tenGhe = tenGhe;
        this.isDelete = isDelete;
        this.userUpdate = userUpdate;
    }

    public Ghe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenGhe() {
        return tenGhe;
    }

    public void setTenGhe(String tenGhe) {
        this.tenGhe = tenGhe;
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
