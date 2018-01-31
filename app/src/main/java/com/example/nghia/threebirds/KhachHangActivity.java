package com.example.nghia.threebirds;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class KhachHangActivity extends AppCompatActivity {
    ImageButton btn_Them, btn_DonHang, btn_KhachHang, btn_SanPham;
    int REQUEST_CODE = 123;
    Database db;

    ArrayList<KhachHang> array_KH;
    ListView lv;
    KhachHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        Mapping();

        array_KH = new ArrayList<>();
        adapter = new KhachHangAdapter(this, R.layout.danh_sach_khach_hang, array_KH);
        lv.setAdapter(adapter);

        //this.deleteDatabase("quan_ly_ban_hang.sqlite");
        db = new Database(this, "quan_ly_ban_hang.sqlite", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS KhachHang(tenKH NTEXT, loaiKH NVARCHAR(30),sdt VARCHAR(11),email TEXT,diachi NTEXT);");
        Cursor dataCur = db.GetData("SELECT * FROM KhachHang");
        while (dataCur.moveToNext()) {
            String tenKH = dataCur.getString(0);
            String loaiKH = dataCur.getString(1);
            String sdt = dataCur.getString(2);
            String email = dataCur.getString(3);
            String diachi = dataCur.getString(4);
            array_KH.add(new KhachHang(tenKH, loaiKH, sdt, email, diachi));
        }
        adapter.notifyDataSetChanged();

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

    public void DialogEditInfo(KhachHang kh) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update_khach_hang);
        final EditText edtTenKH = (EditText) dialog.findViewById(R.id.dialog_KH_edtTenKH);
        final Spinner spinnerLoaiKH = (Spinner) dialog.findViewById(R.id.dialog_KH_spinnerLoaiKH);
        final EditText edtSDT = (EditText) dialog.findViewById(R.id.dialog_KH_edtSDT);
        final EditText edtEmail = (EditText) dialog.findViewById(R.id.dialog_KH_edtEmail);
        final EditText edtDiaChi = (EditText) dialog.findViewById(R.id.dialog_KH_edtDiaChi);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.dialog_KH_btnOK);
        Button btnHuy = (Button) dialog.findViewById(R.id.dialog_KH_btnCancel);

        edtTenKH.setText(String.valueOf(kh.getTen()));
        //spinnerLoaiKH.set
        edtSDT.setText(String.valueOf(kh.getSoDienThoai()));
        edtEmail.setText(String.valueOf(kh.getEmail()));
        edtDiaChi.setText(String.valueOf(kh.getDiaChi()));

   /*     btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtTenKH.getText().toString().isEmpty() || !spinnerLoaiKH.getSelectedItem().toString().isEmpty() || !edtSDT.getText().toString().isEmpty() || !edtEmail.getText().toString().isEmpty() || !edtDiaChi.getText().toString().isEmpty()) {
                    String tenMoi = edtTenKH.getText().toString();
                    String loaiKhMoi = spinnerLoaiKH.getSelectedItem().toString();
                    String sdtMoi = edtSDT.getText().toString();
                    String emailMoi = edtEmail.getText().toString();
                    String diachiMoi = edtDiaChi.getText().toString();
                    for (KhachHang sv : DSSV) {
                        if (sv.getMssv() == mssvMoi) {
                            sv.setTen(tenMoi);
                            sv.setDiem(diemMoi);
                        }
                        dialog.dismiss();
                        svAdapter.notifyDataSetChanged();
                        Toast.makeText(DanhSachSinhVien.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DanhSachSinhVien.this, "Thông tin chưa được điền đầy đủ!", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getBundleExtra("BUNDLE");
            KhachHang kh_received = (KhachHang) bundle.getSerializable("KH");
            if (bundle != null) {
                array_KH.add(kh_received);
                db.QueryData("INSERT INTO KhachHang VALUES('" + kh_received.getTen() + "','" + kh_received.getLoaiKhachHang() + "','" + kh_received.getSoDienThoai() + "','" + kh_received.getEmail() + "','" + kh_received.getDiaChi() + "')");
            }
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
        }
    }
}
