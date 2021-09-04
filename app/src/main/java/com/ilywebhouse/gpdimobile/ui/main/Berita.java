package com.ilywebhouse.gpdimobile.ui.main;

import java.io.Serializable;

public class Berita implements Serializable {
    private String judulBerita;
    private String tanggal;
    private String kontenBerita;

    public Berita(String judulberita, String tanggal, String kontenBerita) {
        this.judulBerita = judulberita;
        this.tanggal = tanggal;
        this.kontenBerita = kontenBerita;
    }

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulberita) {
        this.judulBerita = judulberita;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKontenBerita() {
        return kontenBerita;
    }

    public void setKontenBerita(String kontenBerita) {
        this.kontenBerita = kontenBerita;
    }
}
