package com.example.nghia.threebirds;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ThemSanPhamActivity extends AppCompatActivity {

    Spinner spinnerLoaiSP, spinnerNhanHieu;
    EditText edtGiaTien, edtTonKho;
    ImageButton btn_Finish, btn_Back;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        Mapping();

        db = new Database(this, "quan_ly_ban_hang.sqlite", null, 1);

        String[] array_loaiSP = new String[]{"Điện thoại", "Tablet"};
        final ArrayAdapter<String> adapter_loaiSP = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array_loaiSP);
        spinnerLoaiSP.setAdapter(adapter_loaiSP);

        String[] array_NhanHieu = new String[]{"SamSung", "Iphone","Asus","Xiaomi"};
        final ArrayAdapter<String> adapter_nhanHieu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array_NhanHieu);
        spinnerNhanHieu.setAdapter(adapter_nhanHieu);

        btn_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerLoaiSP.getSelectedItem().toString().length() == 0 || spinnerNhanHieu.getSelectedItem().toString().length() == 0 || edtGiaTien.getText().toString().length() == 0 || edtTonKho.getText().toString().length() == 0) {
                    Toast.makeText(ThemSanPhamActivity.this, "Xin hãy nhập đầy đủ tất cả các trường!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int tonkho = Integer.parseInt(edtTonKho.getText().toString());
                        double giatien = Double.parseDouble(edtGiaTien.getText().toString());
                        db.QueryData("INSERT INTO SanPham VALUES(NULL,'" + spinnerLoaiSP.getSelectedItem().toString() + "','" + spinnerNhanHieu.getSelectedItem().toString() + "','" + giatien + "','" + tonkho + "')");
                        Intent intent = new Intent();
                        intent.putExtra("SIGNAL", "SP_SUCCESS");
                        setResult(RESULT_OK, intent);
                        showMessage("Thành công", "Thêm sản phẩm thành công!");
                        db.close();
                    } catch (Exception e) {
                        showSimpleMessage("Lỗi", "Giá trị quá lớn!");
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
        spinnerLoaiSP = (Spinner) findViewById(R.id.spinnerLoaiSP);
        spinnerNhanHieu = (Spinner) findViewById(R.id.spinnerNhanHieu);
        edtGiaTien = (EditText) findViewById(R.id.edtGiaTien);
        edtTonKho = (EditText) findViewById(R.id.edtTonKho);
        btn_Finish = (ImageButton) findViewById(R.id.btn_finish);
        btn_Back = (ImageButton) findViewById(R.id.btn_back);
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

    public void showSimpleMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}