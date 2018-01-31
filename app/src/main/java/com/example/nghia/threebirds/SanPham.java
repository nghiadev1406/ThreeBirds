package com.example.nghia.threebirds;

/**
 * Created by nghia on 1/24/2018.
 */

public class SanPham {
    private String TenHangHoa, LoaiSanPham, NhanHieu;
    private double GiaTien;

    public SanPham(String tenHangHoa, String loaiSanPham, String nhanHieu, double giaTien) {
        TenHangHoa = tenHangHoa;
        LoaiSanPham = loaiSanPham;
        NhanHieu = nhanHieu;
        GiaTien = giaTien;
    }

    public String getTenHangHoa() {
        return TenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        TenHangHoa = tenHangHoa;
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
}
