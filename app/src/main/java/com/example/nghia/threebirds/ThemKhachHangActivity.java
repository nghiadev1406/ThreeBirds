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

public class ThemKhachHangActivity extends AppCompatActivity {
    Spinner spinnerLoaiKhach;
    EditText edtTenKH, edtSDT, edtEmail, edtDiaChi;
    ImageButton btn_Finish, btn_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khach_hang);
        Mapping();
        btn_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("goihang", "nghia");
                setResult(RESULT_OK, intent);
                showMessage("Thành công", "Tạo khách hàng thành công!");
            }
        });
    }

    public void Mapping() {
        spinnerLoaiKhach = (Spinner) findViewById(R.id.spinnerLoaiKH);
        edtTenKH = (EditText) findViewById(R.id.edtTenKH);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
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
