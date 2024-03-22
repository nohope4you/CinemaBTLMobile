package com.example.oucinema.model;

public class TempModel {
    private String nameMovie;
    private String number;

    public TempModel(String nameMovie, String number) {
        this.nameMovie = nameMovie;
        this.number = number;
    }

    public TempModel() {
    }

    @Override
    public String toString() {
        return "TempModel{" +
                "nameMovie='" + nameMovie + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}