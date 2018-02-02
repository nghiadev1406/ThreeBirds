package com.example.nghia.threebirds;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DonHangActivity extends AppCompatActivity {
    ImageButton btn_Them, btn_DonHang, btn_KhachHang, btn_SanPham;
    int REQUEST_CODE = 123;
    Database db;

    ArrayList<DonHang> array_DH;
    ListView lv;
    DonHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        Mapping();


        //Create Database + Tables
        //this.deleteDatabase("quan_ly_ban_hang.sqlite");
        db = new Database(this, "quan_ly_ban_hang.sqlite", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS DonHang(maDH INTEGER PRIMARY KEY AUTOINCREMENT, tenSP NVARCHAR(30), NhanHieu NVARCHAR(30), soluong INTEGER, status VARCHAR(20), tenKH NTEXT, sdt VARCHAR(11), diachi NTEXT);");
        array_DH = new ArrayList<>();
        adapter = new DonHangAdapter(this, R.layout.danh_sach_don_hang, array_DH);
        getDataDonHang();
        lv.setAdapter(adapter);

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.close();
                Intent intent = new Intent(DonHangActivity.this, ThemDonHangActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                db.close();
            }
        });

        btn_KhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonHangActivity.this, KhachHangActivity.class);
                startActivity(intent);
                db.close();
                finish();
                overridePendingTransition(0, 0);
            }
        });

        btn_SanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonHangActivity.this, SanPhamActivity.class);
                startActivity(intent);
                db.close();
                finish();
                overridePendingTransition(0, 0);
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

    public void DialogDeleteInfo(final DonHang dh) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn xóa đơn hàng?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    db.QueryData("DELETE FROM DonHang WHERE maDH = '" + dh.getMaDonHang() + "'");
                    Toast.makeText(DonHangActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    getDataDonHang();
                } catch (SQLException e) {
                    showMessage("Lỗi", "Xóa không thành công!");
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void DialogEditInfo(final DonHang dh) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update_don_hang);
        final EditText edtSP = (EditText) dialog.findViewById(R.id.dialog_DH_edtSanPham);
        final EditText edtNhanHieu = (EditText) dialog.findViewById(R.id.dialog_DH_edtNhanHieu);
        final EditText edtSoLuong = (EditText) dialog.findViewById(R.id.dialog_DH_edtSoLuong);
        final Spinner spinStatus = (Spinner) dialog.findViewById(R.id.dialog_DH_spinnerTrangThai);
        final EditText edtKH = (EditText) dialog.findViewById(R.id.dialog_DH_edtTenKH);
        final EditText edtSDT = (EditText) dialog.findViewById(R.id.dialog_DH_edtSDT);
        final EditText edtDiaChi = (EditText) dialog.findViewById(R.id.dialog_DH_edtDiaChi);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.dialog_DH_btnOK);
        Button btnHuy = (Button) dialog.findViewById(R.id.dialog_DH_btnCancel);

        edtSP.setText(String.valueOf(dh.getTenSanPham()));
        edtNhanHieu.setText(String.valueOf(dh.getNhanHieu()));
        edtSoLuong.setText(String.valueOf(dh.getSoLuong()));

        String[] array_status = new String[]{"Hoàn thành", "Hủy", "Đang giao"};
        final ArrayAdapter<String> adapter_trangthai = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array_status);
        spinStatus.setAdapter(adapter_trangthai);
        for (int i = 0; i < array_status.length; i++) {
            if (array_status[i].equals(dh.getTrangThai())) {
                spinStatus.setSelection(i);
                break;
            }
        }
        edtKH.setText(String.valueOf(dh.getTenKhachHang()));
        edtSDT.setText(String.valueOf(dh.getSoDienThoai()));
        edtDiaChi.setText(String.valueOf(dh.getDiaChi()));

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtSP.getText().toString().isEmpty() || !edtSoLuong.getText().toString().isEmpty() || !spinStatus.getSelectedItem().toString().isEmpty() || !edtKH.getText().toString().isEmpty() || !edtSDT.getText().toString().isEmpty() || !edtDiaChi.getText().toString().isEmpty()) {
                    int id = dh.getMaDonHang();
                    String sanpham_moi = edtSP.getText().toString();
                    String nhanHieu_moi = edtNhanHieu.getText().toString();
                    int soluong_moi = Integer.parseInt(edtSoLuong.getText().toString());
                    String trangthai_moi = spinStatus.getSelectedItem().toString();
                    String khachhang_moi = edtKH.getText().toString();
                    String sdt_moi = edtSDT.getText().toString();
                    String diachi_moi = edtDiaChi.getText().toString();

                    db.QueryData("UPDATE DonHang SET tenSP = '" + sanpham_moi + "',NhanHieu = '" + nhanHieu_moi + "', soluong = '" + soluong_moi + "', status = '" + trangthai_moi + "' , tenKH = '" + khachhang_moi + "', sdt = '" + sdt_moi + "', diachi = '" + diachi_moi + "' WHERE maDH = '" + id + "'");
                    Toast.makeText(DonHangActivity.this, "Chỉnh sửa thành công!", Toast.LENGTH_SHORT).show();
                    getDataDonHang();
                    dialog.dismiss();
                } else {
                    Toast.makeText(DonHangActivity.this, "Thông tin chưa được điền đầy đủ!", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
            String signal = data.getStringExtra("SIGNAL");
            if (signal.equals("DH_SUCCESS")) {
                getDataDonHang();
            }
        }
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    public void getDataDonHang() {
        Cursor dataCur = db.GetData("SELECT * FROM DonHang");
        array_DH.clear();
        while (dataCur.moveToNext()) {
            int id = dataCur.getInt(0);
            String sanpham = dataCur.getString(1);
            String nhanHieu = dataCur.getString(2);
            int soluong = dataCur.getInt(3);
            String trangthai = dataCur.getString(4);
            String khachhang = dataCur.getString(5);
            String sdt = dataCur.getString(6);
            String diachi = dataCur.getString(7);
            array_DH.add(new DonHang(id, sanpham, nhanHieu, soluong, trangthai, khachhang, sdt, diachi));
        }
        adapter.notifyDataSetChanged();
    }
}