package com.example.nghia.threebirds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DongAnh on 2/3/2018.
 */

public class Select_KH_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<KhachHang> customerList;

    public Select_KH_Adapter(ThemDonHangActivity context, int layout, List<KhachHang> customerList) {
        this.context = context;
        this.layout = layout;
        this.customerList = customerList;
    }

    public Select_KH_Adapter(DonHangActivity context, int layout, List<KhachHang> customerList) {
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
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Select_KH_Adapter.ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new Select_KH_Adapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(KhachHangActivity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtTenKH = (TextView) view.findViewById(R.id.select_txt_kh);
            viewHolder.txtDiaChi = (TextView) view.findViewById(R.id.select_txt_diachi);
            view.setTag(viewHolder);
        } else {
            viewHolder = (Select_KH_Adapter.ViewHolder) view.getTag();
        }
        final KhachHang kh = customerList.get(i);
        viewHolder.txtTenKH.setText(kh.getTenKhachHang());
        viewHolder.txtDiaChi.setText(kh.getDiaChi());
        return view;
    }
}
