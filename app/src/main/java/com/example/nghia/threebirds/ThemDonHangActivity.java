package com.example.nghia.threebirds;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ThemDonHangActivity extends AppCompatActivity {
    Spinner spin_TrangThai;
    EditText edt_SanPham, edt_NhanHieu, edt_soluong, edt_TenKH, edt_SDT, edt_DiaChi;
    ImageButton btn_Finish, btn_Back, btn_ChonKH;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_don_hang);
        Mapping();

        db = new Database(this, "quan_ly_ban_hang.sqlite", null, 1);

        String[] array_status = new String[]{"Hoàn thành", "Hủy", "Đang giao"};
        final ArrayAdapter<String> adapter_trangthai = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array_status);
        spin_TrangThai.setAdapter(adapter_trangthai);

        btn_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_SanPham.getText().toString().isEmpty() || edt_soluong.getText().toString().isEmpty() || spin_TrangThai.getSelectedItem().toString().isEmpty() || edt_TenKH.getText().toString().isEmpty() || edt_SDT.getText().toString().isEmpty() || edt_DiaChi.getText().toString().isEmpty()) {
                    Toast.makeText(ThemDonHangActivity.this, "Xin hãy nhập đầy đủ tất cả các trường!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        db.QueryData("INSERT INTO DonHang VALUES(NULL,'" + edt_SanPham.getText().toString() + "','" + edt_NhanHieu.getText().toString() + "','" + edt_soluong.getText().toString() + "','" + spin_TrangThai.getSelectedItem().toString() + "','" + edt_TenKH.getText().toString() + "','" + edt_SDT.getText().toString() + "', '" + edt_DiaChi.getText().toString() + "')");
                        Intent intent = new Intent();
                        intent.putExtra("SIGNAL", "DH_SUCCESS");
                        setResult(RESULT_OK, intent);
                        showMessage("Thành công", "Thêm đơn hàng thành công!");
                        db.close();
                    } catch (SQLException e) {
                        showMessage("Thất bại", "Không thể thêm đơn hàng!");
                    }
                }
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Mapping() {
        edt_SanPham = (EditText) findViewById(R.id.edtSanPham);
        edt_NhanHieu = (EditText) findViewById(R.id.edtNhanHieu);
        edt_soluong = (EditText) findViewById(R.id.edtSoLuong);
        spin_TrangThai = (Spinner) findViewById(R.id.spinnerTrangThai);
        edt_TenKH = (EditText) findViewById(R.id.edtTenKH);
        edt_SDT = (EditText) findViewById(R.id.edtSDT);
        edt_DiaChi = (EditText) findViewById(R.id.edtDiaChi);
        btn_Finish = (ImageButton) findViewById(R.id.btn_finish);
        btn_Back = (ImageButton) findViewById(R.id.btn_back);
        btn_ChonKH = (ImageButton) findViewById(R.id.btnChonKH);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }
}
