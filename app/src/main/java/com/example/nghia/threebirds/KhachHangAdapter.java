package com.example.nghia.threebirds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nghia on 1/31/2018.
 */

public class KhachHangAdapter extends BaseAdapter {
    private KhachHangActivity context;
    private int layout;
    private List<KhachHang> customerList;

    public KhachHangAdapter(KhachHangActivity context, int layout, List<KhachHang> customerList) {
        this.context = context;
        this.layout = layout;
        this.customerList = customerList;
    }

    @Override
    public int getCount() {
        return customerList.size();
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
        TextView txtTenKH, txtDiaChi;
        ImageView img_delete, img_modify;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(KhachHangActivity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtTenKH = (TextView) view.findViewById(R.id.txt_KH_TenKH);
            viewHolder.txtDiaChi = (TextView) view.findViewById(R.id.txt_KH_DiaChi);
            viewHolder.img_delete = (ImageView) view.findViewById(R.id.imgDelete);
            viewHolder.img_modify = (ImageView) view.findViewById(R.id.imgModify);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final KhachHang kh = customerList.get(i);
        viewHolder.txtTenKH.setText(kh.getTenKhachHang());
        viewHolder.txtDiaChi.setText(kh.getDiaChi());

        viewHolder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDeleteInfo(kh);
            }
        });

        viewHolder.img_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogEditInfo(kh);
            }
        });

        return view;
    }
}
