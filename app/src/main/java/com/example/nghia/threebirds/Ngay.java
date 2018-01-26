package com.example.nghia.threebirds;

/**
 * Created by nghia on 1/27/2018.
 */

public class Ngay {
    private int ngay, thang, nam;

    public Ngay() {
        this.ngay = 1;
        this.thang = 1;
        this.nam = 2011;
    }

    public Ngay(int ngay, int thang, int nam) {
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }
}
