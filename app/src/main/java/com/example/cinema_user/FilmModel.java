package com.example.cinema_user;

public class FilmModel {

    private String aciklama, filmismi, oyuncular, resim, tur, yil, yonetmen;

    public FilmModel()
    {

    }

    public FilmModel(String aciklama, String filmismi, String oyuncular, String resim, String tur, String yil, String yonetmen) {
        this.aciklama = aciklama;
        this.filmismi = filmismi;
        this.oyuncular = oyuncular;
        this.resim = resim;
        this.tur = tur;
        this.yil = yil;
        this.yonetmen = yonetmen;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getFilmismi() {
        return filmismi;
    }

    public void setFilmismi(String filmismi) {
        this.filmismi = filmismi;
    }

    public String getOyuncular() {
        return oyuncular;
    }

    public void setOyuncular(String oyuncular) {
        this.oyuncular = oyuncular;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getYil() {
        return yil;
    }

    public void setYil(String yil) {
        this.yil = yil;
    }

    public String getYonetmen() {
        return yonetmen;
    }

    public void setYonetmen(String yonetmen) {
        this.yonetmen = yonetmen;
    }

    @Override
    public String toString() {
        return "FilmModel{" +
                "aciklama='" + aciklama + '\'' +
                ", filmismi='" + filmismi + '\'' +
                ", oyuncular='" + oyuncular + '\'' +
                ", resim='" + resim + '\'' +
                ", tur='" + tur + '\'' +
                ", yil='" + yil + '\'' +
                ", yonetmen='" + yonetmen + '\'' +
                '}';
    }
}
