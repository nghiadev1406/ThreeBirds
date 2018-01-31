package com.example.nghia.threebirds;

import java.io.Serializable;

/**
 * Created by nghia on 1/24/2018.
 */

public class KhachHang implements Serializable {
    private int id;
    private String TenKhachHang, LoaiKhachHang, SoDienThoai, Email, DiaChi;

    public KhachHang(int id, String tenKhachHang, String loaiKhachHang, String soDienThoai, String email, String diaChi) {
        this.id = id;
        TenKhachHang = tenKhachHang;
        LoaiKhachHang = loaiKhachHang;
        SoDienThoai = soDienThoai;
        Email = email;
        DiaChi = diaChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
