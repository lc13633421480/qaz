package com.example.test_kao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.base.BaseAdapter;
import com.example.test_kao.bean.HomeBean;

import java.util.List;

public class CategoryListAdapter extends BaseAdapter {
    private Context context;
    public CategoryListAdapter(Context context, List<HomeBean.DataBean.CategoryListBean.GoodsListBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_cate;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.CategoryListBean.GoodsListBean bean = (HomeBean.DataBean.CategoryListBean.GoodsListBean) data;
        TextView name_cate = (TextView) vh.getViewById(R.id.name_cate);
        TextView pri_cate = (TextView) vh.getViewById(R.id.pri_cate);
        ImageView img_cate = (ImageView) vh.getViewById(R.id.img_cate);

        Glide.with(context).load(bean.getList_pic_url()).into(img_cate);
        name_cate.setText(bean.getName());
        pri_cate.setText("￥"+bean.getRetail_price());
        String s = pri_cate.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")),0,
                s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        pri_cate.setText(builder);
    }
}
