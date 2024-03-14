package com.example.oucinema.model;

public class DanhGia {
    private int id;
    private String danhGia;
    private String nguoiDanhGia;
    private Double rating;
    private Phim phimID;

    public DanhGia(int id, String danhGia, String nguoiDanhGia, Double rating, Phim phimID) {
        this.id = id;
        this.danhGia = danhGia;
        this.nguoiDanhGia = nguoiDanhGia;
        this.rating = rating;
        this.phimID = phimID;
    }

    public DanhGia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public String getNguoiDanhGia() {
        return nguoiDanhGia;
    }

    public void setNguoiDanhGia(String nguoiDanhGia) {
        this.nguoiDanhGia = nguoiDanhGia;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Phim getPhimID() {
        return phimID;
    }

    public void setPhimID(Phim phimID) {
        this.phimID = phimID;
    }
}
