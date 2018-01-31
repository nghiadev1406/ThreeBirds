package com.example.nghia.threebirds;

import android.content.Intent;
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
    Database db;

    ArrayList<DonHang> dsDonHang;
    DonHangAdapter svAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        Mapping();

        //Create Database + Tables
        db = new Database(this, "quan_ly_ban_hang.sqlite", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS DonHang(maDH INTEGER PRIMARY KEY AUTOINCREMENT, tenSP NVARCHAR(30), status VARCHAR(20), tenKH NTEXT, sdt VARCHAR(11), diachi NTEXT);");

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonHangActivity.this, ThemDonHangActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btn_KhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonHangActivity.this, KhachHangActivity.class);
                startActivity(intent);
                db.closeConnection();
                finish();
            }
        });

        btn_SanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonHangActivity.this, SanPhamActivity.class);
                startActivity(intent);
                finish();
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
