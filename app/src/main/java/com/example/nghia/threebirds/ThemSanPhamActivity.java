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

public class ThemSanPhamActivity extends AppCompatActivity {

    Spinner spinnerLoaiSP, spinnerNhanHieu;
    EditText edtTenSP, edtGiaTien;
    ImageButton btn_Finish, btn_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        Mapping();
        btn_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("goihang", "nghia");
                setResult(RESULT_OK, intent);
                showMessage("Thành công", "Tạo sản phẩm thành công!");
            }
        });
    }

    public void Mapping() {
        spinnerLoaiSP = (Spinner) findViewById(R.id.spinnerLoaiSP);
        spinnerNhanHieu = (Spinner) findViewById(R.id.spinnerNhanHieu);
        edtTenSP = (EditText) findViewById(R.id.edtTenSP);
        edtGiaTien = (EditText) findViewById(R.id.edtGiaTien);
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
}
