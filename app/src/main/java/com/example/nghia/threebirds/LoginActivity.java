package com.example.nghia.threebirds;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView txtSignUp;
    EditText edtLog_Name, edtLog_Pass;
    SQLiteDatabase db;
    Cursor curMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = openOrCreateDatabase("Account", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Account(username VARCHAR(10) PRIMARY KEY,password VARCHAR(8));");
        mapping();
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogEditInfo();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DonHangActivity.class);
                Bundle bundle = new Bundle();
                curMaster = db.rawQuery("SELECT * FROM Account WHERE username='" + edtLog_Name.getText() + "' AND password='" + edtLog_Pass.getText() + "';", null);
                if (curMaster.moveToFirst()) {
                    bundle.putString("username", edtLog_Name.getText().toString());
                    intent.putExtra("data", bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu bị sai! Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void DialogEditInfo() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_register);
        final EditText edt_username = (EditText) dialog.findViewById(R.id.dialog_register_username);
        final EditText edt_password = (EditText) dialog.findViewById(R.id.dialog_register_password);
        final EditText edt_confirmPass = (EditText) dialog.findViewById(R.id.dialog_register_confirmPass);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.dialog_register_btnOK);
        Button btnHuy = (Button) dialog.findViewById(R.id.dialog_register_btnCancel);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_password.getText().toString().equals(edt_confirmPass.getText().toString())) {
                    db.execSQL("INSERT INTO Account VALUES('" + edt_username.getText().toString() + "','" + edt_password.getText().toString() + "');");
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(LoginActivity.this, "Mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
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

    public void mapping() {
        edtLog_Name = (EditText) findViewById(R.id.loginName);
        edtLog_Pass = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtSignUp = (TextView) findViewById(R.id.txtRegister);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
