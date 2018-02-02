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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ThemDonHangActivity extends AppCompatActivity {
    Spinner spin_TrangThai;
    EditText edt_SanPham, edt_NhanHieu, edt_soluong, edt_TenKH, edt_SDT, edt_DiaChi;
    ImageButton btn_Finish, btn_Back, btn_ChonKH, btn_ChonSP;
    Database db;
    int count_tonkho;

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
                    if (Integer.parseInt(edt_soluong.getText().toString()) > count_tonkho) {
                        showSimpleMessage("Không hợp lệ", "Số lượng đặt vượt quá số lượng trong kho!");
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
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_ChonSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogSelectSP();
            }
        });

        btn_ChonKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogSelectKH();
            }
        });
    }

    public void DialogSelectKH() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_khach_hang);
        final ArrayList<KhachHang> array_KH = new ArrayList<>();
        Cursor dataCur = db.GetData("SELECT * FROM KhachHang");
        while (dataCur.moveToNext()) {
            int idKH = dataCur.getInt(0);
            String tenKH = dataCur.getString(1);
            String loaiKH = dataCur.getString(2);
            String sdt = dataCur.getString(3);
            String email = dataCur.getString(4);
            String diachi = dataCur.getString(5);
            array_KH.add(new KhachHang(idKH, tenKH, loaiKH, sdt, email, diachi));
        }
        Select_KH_Adapter adapter = new Select_KH_Adapter(this, R.layout.select_danh_sach_khach_hang, array_KH);
        ListView lv = (ListView) dialog.findViewById(R.id.dialog_lv_chon_KH);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KhachHang kh = array_KH.get(position);
                edt_TenKH.setText(kh.getTenKhachHang());
                edt_DiaChi.setText(kh.getDiaChi());
                edt_SDT.setText(kh.getSoDienThoai());
                dialog.dismiss();
            }
        });
        dataCur.close();
        dialog.show();
    }

    public void DialogSelectSP() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_san_pham);
        final ArrayList<SanPham> array_SP = new ArrayList<>();
        Cursor dataCur = db.GetData("SELECT * FROM SanPham");
        while (dataCur.moveToNext()) {
            int idSP = dataCur.getInt(0);
            String loaiSP = dataCur.getString(1);
            String nhanHieu = dataCur.getString(2);
            double giatien = dataCur.getDouble(3);
            int tonkho = dataCur.getInt(4);
            array_SP.add(new SanPham(idSP, loaiSP, nhanHieu, giatien, tonkho));
        }
        Select_SP_Adapter adapter = new Select_SP_Adapter(this, R.layout.select_danh_sach_san_pham, array_SP);
        ListView lv = (ListView) dialog.findViewById(R.id.dialog_lv_chon_SP);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sp = array_SP.get(position);
                edt_SanPham.setText(sp.getLoaiSanPham());
                edt_NhanHieu.setText(sp.getNhanHieu());
                count_tonkho = sp.getTonKho();
                dialog.dismiss();
            }
        });
        dataCur.close();
        dialog.show();
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
        btn_ChonSP = (ImageButton) findViewById(R.id.btnChonSanPham);
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
