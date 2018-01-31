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

public class ThemKhachHangActivity extends AppCompatActivity {
    Spinner spinnerLoaiKhach;
    EditText edtTenKH, edtSDT, edtEmail, edtDiaChi;
    ImageButton btn_Finish, btn_Back;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khach_hang);
        Mapping();

        db = new Database(this, "quan_ly_ban_hang.sqlite", null, 1);

        String[] array_loaiKH = new String[]{"Mới", "Member", "VIP"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, array_loaiKH);
        spinnerLoaiKhach.setAdapter(adapter);

        btn_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTenKH.getText().toString().length() == 0 || edtSDT.getText().toString().length() == 0 || edtEmail.getText().toString().length() == 0 || edtDiaChi.getText().toString().length() == 0) {
                    Toast.makeText(ThemKhachHangActivity.this, "Xin hãy nhập đầy đủ tất cả các trường!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        db.QueryData("INSERT INTO KhachHang VALUES(NULL,'" + edtTenKH.getText().toString() + "','" + spinnerLoaiKhach.getSelectedItem().toString() + "','" + edtSDT.getText().toString() + "','" + edtEmail.getText().toString() + "','" + edtDiaChi.getText().toString() + "')");
                        Intent intent = new Intent();
                        intent.putExtra("SIGNAL", "KH_SUCCESS");
                        setResult(RESULT_OK, intent);
                        showMessage("Thành công", "Tạo khách hàng thành công!");
                        db.close();
                    /*KhachHang kh = new KhachHang(0, edtTenKH.getText().toString(), spinnerLoaiKhach.getSelectedItem().toString(), edtSDT.getText().toString(), edtEmail.getText().toString(), edtDiaChi.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("KH", (Serializable) kh);
                    Intent intent = new Intent();
                    intent.putExtra("BUNDLE", bundle);
                    setResult(RESULT_OK, intent);
                    showMessage("Thành công", "Tạo khách hàng thành công!");*/
                    } catch (SQLException e) {
                        showMessage("Thất bại", "Không thể thêm khách hàng!");
                    }
                }
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                finish();
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
                db.close();
                finish();
            }
        });
        builder.show();
    }
}
