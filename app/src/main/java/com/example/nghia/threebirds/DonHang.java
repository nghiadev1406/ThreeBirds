package com.example.nghia.threebirds;

/**
 * Created by nghia on 1/24/2018.
 */

public class DonHang {
    private String TenSanPham, NhanHieu, TrangThai, TenKhachHang, DiaChi, SoDienThoai;
    private int MaDonHang, SoLuong;

    public DonHang(int maDonHang, String tenSanPham, String nhanHieu, int soLuong, String trangThai, String tenKhachHang, String soDienThoai, String diaChi) {
        TenSanPham = tenSanPham;
        NhanHieu = nhanHieu;
        TrangThai = trangThai;
        TenKhachHang = tenKhachHang;
        DiaChi = diaChi;
        SoDienThoai = soDienThoai;
        MaDonHang = maDonHang;
        SoLuong = soLuong;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getNhanHieu() {
        return NhanHieu;
    }

    public void setNhanHieu(String nhanHieu) {
        NhanHieu = nhanHieu;
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

    public int getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        MaDonHang = maDonHang;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
