package com.example.oucinema.model;

public class RapPhim {
    private int id;
    private String tenRap;
    private String diaChi;
    private String soDienThoaiLienHe;
    private Boolean isDelete;
    private int userUpdate;

    public RapPhim(int id, String tenRap, String diaChi, String soDienThoaiLienHe, Boolean isDelete, int userUpdate) {
        this.id = id;
        this.tenRap = tenRap;
        this.diaChi = diaChi;
        this.soDienThoaiLienHe = soDienThoaiLienHe;
        this.isDelete = isDelete;
        this.userUpdate = userUpdate;
    }

    public RapPhim() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoaiLienHe() {
        return soDienThoaiLienHe;
    }

    public void setSoDienThoaiLienHe(String soDienThoaiLienHe) {
        this.soDienThoaiLienHe = soDienThoaiLienHe;
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
