package com.example.nghia.threebirds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nghia on 2/1/2018.
 */

public class SanPhamAdapter extends BaseAdapter {
    private SanPhamActivity context;
    private int layout;
    private List<SanPham> productList;

    public SanPhamAdapter(SanPhamActivity context, int layout, List<SanPham> productList) {
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
        ImageView img_delete, img_modify;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SanPhamAdapter.ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new SanPhamAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(SanPhamActivity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtLoaiSP = (TextView) view.findViewById(R.id.txt_SP_LoaiSP);
            viewHolder.txtNhanHieu = (TextView) view.findViewById(R.id.txt_SP_NhanHieu);
            viewHolder.txtTonKho = (TextView) view.findViewById(R.id.txt_SP_TonKho);
            viewHolder.img_delete = (ImageView) view.findViewById(R.id.imgDelete);
            viewHolder.img_modify = (ImageView) view.findViewById(R.id.imgModify);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SanPhamAdapter.ViewHolder) view.getTag();
        }
        final SanPham sp = productList.get(i);
        viewHolder.txtLoaiSP.setText(sp.getLoaiSanPham());
        viewHolder.txtNhanHieu.setText(sp.getNhanHieu());
        viewHolder.txtTonKho.setText(String.valueOf(sp.getTonKho()));

        viewHolder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDeleteInfo(sp);
            }
        });

        viewHolder.img_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogEditInfo(sp);
            }
        });

        return view;
    }
}
