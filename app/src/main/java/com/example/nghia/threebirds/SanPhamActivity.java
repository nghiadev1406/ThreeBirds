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

public class SanPhamActivity extends AppCompatActivity {
    ImageButton btn_Them, btn_DonHang, btn_KhachHang, btn_SanPham;
    int REQUEST_CODE = 123;
    Database db;

    ArrayList<SanPham> array_SP;
    ListView lv;
    SanPhamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        Mapping();

        db = new Database(this, "quan_ly_ban_hang.sqlite", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS SanPham(id INTEGER PRIMARY KEY AUTOINCREMENT,loaiSP NTEXT, nhanHieu NTEXT,giatien double, tonkho INTEGER);");
        array_SP = new ArrayList<>();
        adapter = new SanPhamAdapter(this, R.layout.danh_sach_san_pham, array_SP);
        getDataSanPham();
        lv.setAdapter(adapter);

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SanPhamActivity.this, ThemSanPhamActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btn_DonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SanPhamActivity.this, DonHangActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });

        btn_KhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SanPhamActivity.this, KhachHangActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    public void Mapping() {
        lv = (ListView) findViewById(R.id.lv_sanpham);
        btn_Them = (ImageButton) findViewById(R.id.btn_them);
        btn_DonHang = (ImageButton) findViewById(R.id.btn_donhang);
        btn_KhachHang = (ImageButton) findViewById(R.id.btn_khachhang);
        btn_SanPham = (ImageButton) findViewById(R.id.btn_sanpham);
    }

    public void DialogDeleteInfo(final SanPham sp) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn xóa sản phẩm ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    db.QueryData("DELETE FROM SanPham WHERE ID = '" + sp.getId() + "'");
                    Toast.makeText(SanPhamActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    getDataSanPham();
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

    public void DialogEditInfo(final SanPham sp) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update_san_pham);
        final Spinner spinLoaiSP = (Spinner) dialog.findViewById(R.id.dialog_SP_spinnerLoaiSP);
        final Spinner spinNhanHieu = (Spinner) dialog.findViewById(R.id.dialog_SP_spinnerNhanHieu);
        final EditText edtGiaTien = (EditText) dialog.findViewById(R.id.dialog_SP_edtGiaTien);
        final EditText edtTonKho = (EditText) dialog.findViewById(R.id.dialog_SP_edtTonKho);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.dialog_SP_btnOK);
        Button btnHuy = (Button) dialog.findViewById(R.id.dialog_SP_btnCancel);

        String[] array_loaiSP = new String[]{"Điện thoại", "Tablet"};
        final ArrayAdapter<String> adapter_loaiSP = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array_loaiSP);
        spinLoaiSP.setAdapter(adapter_loaiSP);
        for (int i = 0; i < array_loaiSP.length; i++) {
            if (array_loaiSP[i].equals(sp.getLoaiSanPham())) {
                spinLoaiSP.setSelection(i);
                break;
            }
        }

        String[] array_NhanHieu = new String[]{"SamSung", "Iphone", "Asus", "Xiaomi"};
        final ArrayAdapter<String> adapter_nhanHieu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array_NhanHieu);
        spinNhanHieu.setAdapter(adapter_nhanHieu);
        for (int i = 0; i < array_NhanHieu.length; i++) {
            if (array_NhanHieu[i].equals(sp.getNhanHieu())) {
                spinNhanHieu.setSelection(i);
                break;
            }
        }

        edtGiaTien.setText(String.valueOf(sp.getGiaTien()));
        edtTonKho.setText(String.valueOf(sp.getTonKho()));

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!spinLoaiSP.getSelectedItem().toString().isEmpty() || !spinNhanHieu.getSelectedItem().toString().isEmpty() || !edtGiaTien.getText().toString().isEmpty() || !edtTonKho.getText().toString().isEmpty()) {
                    int id = sp.getId();
                    String loaiSP_moi = spinLoaiSP.getSelectedItem().toString();
                    String nhanHieu_moi = spinNhanHieu.getSelectedItem().toString();
                    double giatien_moi = Double.parseDouble(edtGiaTien.getText().toString());
                    int tonkho_moi = Integer.parseInt(edtTonKho.getText().toString());
                    db.QueryData("UPDATE SanPham SET loaiSP = '" + loaiSP_moi + "',NhanHieu = '" + nhanHieu_moi + "',giatien = '" + giatien_moi + "',tonkho = '" + tonkho_moi + "' WHERE id = '" + id + "'");
                    Toast.makeText(SanPhamActivity.this, "Chỉnh sửa thành công!", Toast.LENGTH_SHORT).show();
                    getDataSanPham();
                    dialog.dismiss();
                } else {
                    Toast.makeText(SanPhamActivity.this, "Thông tin chưa được điền đầy đủ!", Toast.LENGTH_SHORT).show();
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
            if (signal.equals("SP_SUCCESS")) {
                getDataSanPham();
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

    public void getDataSanPham() {
        Cursor dataCur = db.GetData("SELECT * FROM SanPham");
        array_SP.clear();
        while (dataCur.moveToNext()) {
            int idSP = dataCur.getInt(0);
            String loaiSP = dataCur.getString(1);
            String nhanHieu = dataCur.getString(2);
            double giatien = dataCur.getDouble(3);
            int tonkho = dataCur.getInt(4);
            array_SP.add(new SanPham(idSP, loaiSP, nhanHieu, giatien, tonkho));
        }
        adapter.notifyDataSetChanged();
    }
}
