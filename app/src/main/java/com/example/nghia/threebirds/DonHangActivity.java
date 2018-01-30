package com.example.nghia.threebirds;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DonHangActivity extends AppCompatActivity {
    ListView lv;
    ImageButton btn_Them, btn_DonHang, btn_KhachHang, btn_SanPham;
    int REQUEST_CODE = 123;
    SQLiteDatabase db;


    ArrayList<DonHang> dsDonHang;
    DonHangAdapter svAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        Mapping();

        //Create Database + Tables
        db = openOrCreateDatabase("QuanLyBanHang", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS DonHang(MaHangHoa VARCHAR(10) PRIMARY KEY,TrangThai VARCHAR(20),SoDienThoai VARCHAR(11), DiaChi NTEXT, TenKhachHang NTEXT, Ngay TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS SanPham(LoaiSanPham NVARCHAR(30),NhanHieu NVARCHAR(10),NgayTao TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS KhachHang(TenKhachHang NTEXT, LoaiKhachHang VARCHAR(10), SoDienThoai VARCHAR(11), Email TEXT, DiaChi NTEXT);");
        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonHangActivity.this, ThemDonHangActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    public void Mapping() {
        lv = (ListView) findViewById(R.id.lv_donhang);
        btn_Them = (ImageButton) findViewById(R.id.btn_them);
        btn_DonHang = (ImageButton) findViewById(R.id.btn_donhang);
        btn_KhachHang = (ImageButton) findViewById(R.id.btn_khachhang);
        btn_SanPham = (ImageButton) findViewById(R.id.btn_sanpham);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Toast.makeText(DonHangActivity.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
        }
    }

}
