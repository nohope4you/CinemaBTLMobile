package com.example.oucinema.model;

import java.sql.Date;

public class MaGiamGia {
    private int id;
    private String tenMaGiam;
    private int phanTramGiam;
    private Date thoiGianHieuLuc;
    private Boolean isDelete;
    private int userUpdate;

    public MaGiamGia(int id, String tenMaGiam, int phanTramGiam, Date thoiGianHieuLuc, Boolean isDelete, int userUpdate) {
        this.id = id;
        this.tenMaGiam = tenMaGiam;
        this.phanTramGiam = phanTramGiam;
        this.thoiGianHieuLuc = thoiGianHieuLuc;
        this.isDelete = isDelete;
        this.userUpdate = userUpdate;
    }

    public MaGiamGia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMaGiam() {
        return tenMaGiam;
    }

    public void setTenMaGiam(String tenMaGiam) {
        this.tenMaGiam = tenMaGiam;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Date getThoiGianHieuLuc() {
        return thoiGianHieuLuc;
    }

    public void setThoiGianHieuLuc(Date thoiGianHieuLuc) {
        this.thoiGianHieuLuc = thoiGianHieuLuc;
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
