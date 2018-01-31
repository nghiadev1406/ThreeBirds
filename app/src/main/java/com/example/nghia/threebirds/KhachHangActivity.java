package com.example.nghia.threebirds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class KhachHangActivity extends AppCompatActivity {
    ListView lv;
    ImageButton btn_Them, btn_DonHang, btn_KhachHang, btn_SanPham;
    int REQUEST_CODE = 123;
    Database db;
    ArrayList<String> array_KH;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        Mapping();

        db = new Database(this, "quan_ly_ban_hang.sqlite", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS KhachHang(tenKH NTEXT, loaiKH NVARCHAR(30),sdt VARCHAR(11),email TEXT,diachi NTEXT);");

        array_KH = new ArrayList<>();
        adapter = new ArrayAdapter(KhachHangActivity.this, android.R.layout.simple_list_item_1, array_KH);

        btn_DonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhachHangActivity.this, DonHangActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_SanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhachHangActivity.this, SanPhamActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KhachHangActivity.this, ThemKhachHangActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    public void Mapping() {
        lv = (ListView) findViewById(R.id.lv_khachhang);
        btn_Them = (ImageButton) findViewById(R.id.btn_them);
        btn_DonHang = (ImageButton) findViewById(R.id.btn_donhang);
        btn_KhachHang = (ImageButton) findViewById(R.id.btn_khachhang);
        btn_SanPham = (ImageButton) findViewById(R.id.btn_sanpham);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getBundleExtra("BUNDLE");
            KhachHang kh_received = (KhachHang) bundle.getSerializable("KH");
            if (bundle != null)
                array_KH.add(kh_received.getTenKhachHang());
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
        }
    }
}
