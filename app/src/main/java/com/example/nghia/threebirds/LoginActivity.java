package com.example.nghia.threebirds;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    SQLiteDatabase db;
    EditText edtLog_Name, edtLog_Pass;
    Cursor curMaster, curPDT, curGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = openOrCreateDatabase("Account", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Account(username VARCHAR(10) PRIMARY KEY,password VARCHAR(8),permission BIT);");
        curMaster = db.rawQuery("SELECT * FROM Account WHERE username='master';", null);
        if (!curMaster.moveToFirst()) {
            db.execSQL("INSERT INTO Account VALUES('master','master',0);");
            db.execSQL("INSERT INTO Account VALUES('pdt','pdt',1);");
            db.execSQL("INSERT INTO Account VALUES('teacher','teacher',2);");
        }
        edtLog_Name = (EditText) findViewById(R.id.loginName);
        edtLog_Pass = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                curMaster = db.rawQuery("SELECT * FROM Account WHERE permission=0 AND username='" + edtLog_Name.getText() + "' AND password='" + edtLog_Pass.getText() + "';", null);
                curPDT = db.rawQuery("SELECT * FROM Account WHERE permission=1 AND username='" + edtLog_Name.getText() + "' AND password='" + edtLog_Pass.getText() + "';", null);
                curGV = db.rawQuery("SELECT * FROM Account WHERE permission=2 AND username='" + edtLog_Name.getText() + "' AND password='" + edtLog_Pass.getText() + "';", null);
                if (curMaster.moveToFirst())
                    bundle.putInt("permission", 0);
                else if (curPDT.moveToFirst())
                    bundle.putInt("permission", 1);
                else if (curGV.moveToFirst())
                    bundle.putInt("permission", 2);
                else {
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu bị sai! Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                    return;
                }
                bundle.putString("username", edtLog_Name.getText().toString());
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }
}
