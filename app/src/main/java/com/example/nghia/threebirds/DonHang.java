package com.example.nghia.threebirds;

/**
 * Created by nghia on 1/24/2018.
 */

public class DonHang {
    private String MaHangHoa,TrangThai,SoDienThoai,DiaChi;
    private Ngay NgayTao;

    public DonHang() {
        MaHangHoa = "";
        NgayTao = new Ngay();
        TrangThai = "";
        SoDienThoai = "";
        DiaChi = "";
    }

    public DonHang(String maHangHoa, Ngay ngayTao, String trangThai, String soDienThoai, String diaChi) {
        MaHangHoa = maHangHoa;
        NgayTao = ngayTao;
        TrangThai = trangThai;
        SoDienThoai = soDienThoai;
        DiaChi = diaChi;
    }
}
