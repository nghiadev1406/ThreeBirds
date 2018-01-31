package com.example.nghia.threebirds;

import java.io.Serializable;

/**
 * Created by nghia on 1/24/2018.
 */

public class KhachHang implements Serializable {
    private String TenKhachHang, LoaiKhachHang, SoDienThoai, Email, DiaChi;

    public KhachHang(String tenKhachHang, String loaiKhachHang, String soDienThoai, String email, String diaChi) {
        TenKhachHang = tenKhachHang;
        LoaiKhachHang = loaiKhachHang;
        SoDienThoai = soDienThoai;
        Email = email;
        DiaChi = diaChi;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getLoaiKhachHang() {
        return LoaiKhachHang;
    }

    public void setLoaiKhachHang(String loaiKhachHang) {
        LoaiKhachHang = loaiKhachHang;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
}
