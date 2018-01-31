package com.example.nghia.threebirds;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class ThemDonHangActivity extends AppCompatActivity {
    Spinner spin_TrangThai;
    EditText edt_MaSP, edt_SanPham, edt_TenKH, edt_SDT, edt_DiaChi;
    ImageButton btn_Finish, btn_Back, btn_ChonKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_don_hang);
        Mapping();
        btn_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("goihang", "nghia");
                setResult(RESULT_OK, intent);
                showMessage("Thành công", "Tạo đơn hàng thành công!");
            }
        });

    }

    public void Mapping() {
        spin_TrangThai = (Spinner) findViewById(R.id.spinnerTrangThai);
        edt_MaSP = (EditText) findViewById(R.id.edtMaDH);
        edt_SanPham = (EditText) findViewById(R.id.edtSanPham);
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
