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

public class Select_SP_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SanPham> productList;

    public Select_SP_Adapter(ThemDonHangActivity context, int layout, List<SanPham> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    public Select_SP_Adapter(DonHangActivity context, int layout, List<SanPham> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
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
        TextView txtLoaiSP, txtNhanHieu, txtTonKho;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Select_SP_Adapter.ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new Select_SP_Adapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(SanPhamActivity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtLoaiSP = (TextView) view.findViewById(R.id.select_txt_SP_LoaiSP);
            viewHolder.txtNhanHieu = (TextView) view.findViewById(R.id.select_txt_SP_NhanHieu);
            viewHolder.txtTonKho = (TextView) view.findViewById(R.id.select_txt_SP_TonKho);
            view.setTag(viewHolder);
        } else {
            viewHolder = (Select_SP_Adapter.ViewHolder) view.getTag();
        }
        final SanPham sp = productList.get(i);
        viewHolder.txtLoaiSP.setText(sp.getLoaiSanPham());
        viewHolder.txtNhanHieu.setText(sp.getNhanHieu());
        viewHolder.txtTonKho.setText(String.valueOf(sp.getTonKho()));
        return view;
    }
}
