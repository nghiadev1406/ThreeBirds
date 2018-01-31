package com.example.nghia.threebirds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nghia on 1/30/2018.
 */

public class DonHangAdapter extends BaseAdapter {
    private DonHangActivity context;
    private int layout;
    private List<DonHang> donhangList;

    public DonHangAdapter(DonHangActivity context, int layout, List<DonHang> donhangList) {
        this.context = context;
        this.layout = layout;
        this.donhangList = donhangList;
    }

    @Override
    public int getCount() {
        return donhangList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtTenSP, txtTenKH;
        ImageView img_delete, img_modify;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(DonHangActivity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtTenSP = (TextView) view.findViewById(R.id.txtTenSP);
            viewHolder.txtTenKH = (TextView) view.findViewById(R.id.txtTenKH);
            viewHolder.img_delete = (ImageView) view.findViewById(R.id.imgDelete);
            viewHolder.img_modify = (ImageView) view.findViewById(R.id.imgModify);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final DonHang donhang = donhangList.get(i);
        viewHolder.txtTenSP.setText(donhang.getTenSanPham());

        viewHolder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.img_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.DialogEditInfo(sinhvien.getMssv(), sinhvien.getTen(), sinhvien.getDiem());
                // Toast.makeText(context, "Đã Sửa", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
