package com.ilywebhouse.gpdimobile.ui.main;

import java.io.Serializable;

public class Berita implements Serializable {
    private String judulberita;
    private String tanggal;

    public Berita(String judulberita, String tanggal) {
        this.judulberita = judulberita;
        this.tanggal = tanggal;
    }

    public String getJudulberita() {
        return judulberita;
    }

    public void setJudulberita(String judulberita) {
        this.judulberita = judulberita;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
