package com.example.nghia.threebirds;

/**
 * Created by nghia on 1/24/2018.
 */

public class SanPham {
    private int id;
    private String LoaiSanPham, NhanHieu;
    private double GiaTien;
    private int TonKho;

    public SanPham(int id, String loaiSanPham, String nhanHieu, double giaTien, int tonKho) {
        this.id = id;
        LoaiSanPham = loaiSanPham;
        NhanHieu = nhanHieu;
        GiaTien = giaTien;
        TonKho = tonKho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaiSanPham() {
        return LoaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        LoaiSanPham = loaiSanPham;
    }

    public String getNhanHieu() {
        return NhanHieu;
    }

    public void setNhanHieu(String nhanHieu) {
        NhanHieu = nhanHieu;
    }

    public double getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(double giaTien) {
        GiaTien = giaTien;
    }

    public int getTonKho() {
        return TonKho;
    }

    public void setTonKho(int tonKho) {
        TonKho = tonKho;
    }
}
