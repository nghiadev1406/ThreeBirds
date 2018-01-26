package com.example.nghia.threebirds;

/**
 * Created by nghia on 1/24/2018.
 */

public class SanPham {
    private String LoaiSanPham, NhanHieu;
    private Ngay NgayTao;

    public SanPham() {
        LoaiSanPham = "";
        NhanHieu = "";
        NgayTao = new Ngay();
    }

    public SanPham(String loaiSanPham, String nhanHieu, Ngay ngayTao) {
        LoaiSanPham = loaiSanPham;
        NhanHieu = nhanHieu;
        NgayTao = ngayTao;
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

    public Ngay getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Ngay ngayTao) {
        NgayTao = ngayTao;
    }
}
