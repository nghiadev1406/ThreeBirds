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

        //this.deleteDatabase("quan_ly_ban_hang.sqlite");
        db = new Database(this, "quan_ly_ban_hang.sqlite", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS KhachHang(id INTEGER PRIMARY KEY AUTOINCREMENT,tenKH NTEXT, loaiKH NVARCHAR(30),sdt VARCHAR(11),email TEXT,diachi NTEXT);");
        array_KH = new ArrayList<>();
        adapter = new KhachHangAdapter(this, R.layout.danh_sach_khach_hang, array_KH);
        getDataKhachHang();
        lv.setAdapter(adapter);

        btn_DonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.close();
                Intent intent = new Intent(KhachHangActivity.this, DonHangActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });

        btn_SanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.close();
                Intent intent = new Intent(KhachHangActivity.this, SanPhamActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.close();
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

    public void DialogDeleteInfo(final KhachHang kh) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Xác nhận");
        builder.setMessage("Xóa khách hàng " + kh.getTenKhachHang() + " ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    db.QueryData("DELETE FROM KhachHang WHERE ID = '" + kh.getId() + "'");
                    Toast.makeText(KhachHangActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    getDataKhachHang();
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

    public void DialogEditInfo(final KhachHang kh) {
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

        edtTenKH.setText(String.valueOf(kh.getTenKhachHang()));

        String[] array_loaiKH = new String[]{"Mới", "Member", "VIP"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array_loaiKH);
        spinnerLoaiKH.setAdapter(adapter);
        for (int i = 0; i < array_loaiKH.length; i++) {
            if (array_loaiKH[i].equals(kh.getLoaiKhachHang())) {
                spinnerLoaiKH.setSelection(i);
                break;
            }
        }

        edtSDT.setText(String.valueOf(kh.getSoDienThoai()));
        edtEmail.setText(String.valueOf(kh.getEmail()));
        edtDiaChi.setText(String.valueOf(kh.getDiaChi()));

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtTenKH.getText().toString().isEmpty() || !spinnerLoaiKH.getSelectedItem().toString().isEmpty() || !edtSDT.getText().toString().isEmpty() || !edtEmail.getText().toString().isEmpty() || !edtDiaChi.getText().toString().isEmpty()) {
                    int id = kh.getId();
                    String tenMoi = edtTenKH.getText().toString();
                    String loaiKhMoi = spinnerLoaiKH.getSelectedItem().toString();
                    String sdtMoi = edtSDT.getText().toString();
                    String emailMoi = edtEmail.getText().toString();
                    String diachiMoi = edtDiaChi.getText().toString();
                    db.QueryData("UPDATE KhachHang SET tenKH = '" + tenMoi + "',loaiKH = '" + loaiKhMoi + "',SDT = '" + sdtMoi + "',email = '" + emailMoi + "', diachi = '" + diachiMoi + "' WHERE ID = '" + id + "'");
                    Toast.makeText(KhachHangActivity.this, "Chỉnh sửa thành công!", Toast.LENGTH_SHORT).show();
                    getDataKhachHang();
                    dialog.dismiss();
                } else {
                    Toast.makeText(KhachHangActivity.this, "Thông tin chưa được điền đầy đủ!", Toast.LENGTH_SHORT).show();
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
            if (signal.equals("KH_SUCCESS")) {
                getDataKhachHang();
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

    public void getDataKhachHang() {
        Cursor dataCur = db.GetData("SELECT * FROM KhachHang");
        array_KH.clear();
        while (dataCur.moveToNext()) {
            int idKH = dataCur.getInt(0);
            String tenKH = dataCur.getString(1);
            String loaiKH = dataCur.getString(2);
            String sdt = dataCur.getString(3);
            String email = dataCur.getString(4);
            String diachi = dataCur.getString(5);
            array_KH.add(new KhachHang(idKH, tenKH, loaiKH, sdt, email, diachi));
        }
        adapter.notifyDataSetChanged();
    }
}
