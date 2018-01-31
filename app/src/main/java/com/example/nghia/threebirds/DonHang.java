package com.example.nghia.threebirds;

/**
 * Created by nghia on 1/24/2018.
 */

public class DonHang {
    private String MaDonHang, TenSanPham, TrangThai, TenKhachHang, DiaChi, SoDienThoai;

    public DonHang(String maDonHang, String tenSanPham, String trangThai, String tenKhachHang, String diaChi, String soDienThoai) {
        MaDonHang = maDonHang;
        TenSanPham = tenSanPham;
        TrangThai = trangThai;
        TenKhachHang = tenKhachHang;
        DiaChi = diaChi;
        SoDienThoai = soDienThoai;
    }

    public String getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        MaDonHang = maDonHang;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }
}
