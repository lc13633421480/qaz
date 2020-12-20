package com.example.test_kao.ui.home.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test_kao.R;
import com.example.test_kao.adapter.Car_Product_Adapter;
import com.example.test_kao.base.BaseActivity;
import com.example.test_kao.bean.CarBean;
import com.example.test_kao.bean.RelateBean;
import com.example.test_kao.interfaces.ICar;
import com.example.test_kao.presenter.CarPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarActivity extends BaseActivity<CarPresenter> implements ICar.View {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.car_name)
    TextView carName;
    @BindView(R.id.car_brief)
    TextView carBrief;
    @BindView(R.id.car_retail_price)
    TextView carRetailPrice;
    @BindView(R.id.txt_assess)
    TextView txtAssess;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.layout_collect)
    FrameLayout layoutCollect;
    @BindView(R.id.img_car)
    ImageView imgCar;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.layout_car)
    FrameLayout layoutCar;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.txt_addCar)
    TextView txtAddCar;
    @BindView(R.id.layout_shop)
    ConstraintLayout layoutShop;
    @BindView(R.id.car_linee)
    LinearLayout carLinee;
    @BindView(R.id.ll_iss)
    LinearLayout llIss;
    @BindView(R.id.rlv_product)
    RecyclerView rlvProduct;

    @Override
    protected int getLayout() {
        return R.layout.activity_car;
    }

    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";

    @Override
    protected CarPresenter createPrenter() {
        return new CarPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        presenter.getCar(id);
    }

    @Override
    public void getResult(CarBean carBean) {
        //banner图
        initBanner(carBean.getData().getGallery());

        // 商品信息
        initInfo(carBean.getData().getInfo());

        //h5 商品详情
        initGoodDetail(carBean.getData().getInfo().getGoods_desc());

        //商品参数
        initattribute(carBean.getData().getAttribute());

        //常见问题
        initIssue(carBean.getData().getIssue());
        //底部数据列表
        initProduct(carBean.getData().getProductList());
    }


    /**
     * 商品详情数据  h5
     *
     * @param webData
     */
    private void initGoodDetail(String webData) {
        String content = h5.replace("word", webData);
        Log.i("TAG", content);
        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }


    private void initBanner(List<CarBean.DataBeanX.GalleryBean> gallery) {
        banner.setImages(gallery).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                CarBean.DataBeanX.GalleryBean bean = (CarBean.DataBeanX.GalleryBean) path;
                String img_url = bean.getImg_url();
                Glide.with(context).load(img_url).into(imageView);
            }
        }).start();
    }

    private void initInfo(CarBean.DataBeanX.InfoBean info) {
        carName.setText(info.getName());
        carBrief.setText(info.getGoods_brief());
        carRetailPrice.setText("￥" + info.getRetail_price());
        String s = carRetailPrice.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(s);
        builder.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        carRetailPrice.setText(builder);
    }


    private void initattribute(List<CarBean.DataBeanX.AttributeBean> attribute) {
        for (int i = 0; i < attribute.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.car_item, null);
            TextView text = view.findViewById(R.id.name_c);
            TextView text1 = view.findViewById(R.id.name_cv);
            text.setText(attribute.get(i).getName());
            text1.setText(attribute.get(i).getValue());
            carLinee.addView(view);
        }
    }

    private void initIssue(List<CarBean.DataBeanX.IssueBean> issue) {
        for (int i = 0; i < issue.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.car_item_issue, null);
            TextView text = view.findViewById(R.id.question);
            TextView text1 = view.findViewById(R.id.answer);
            text.setText(issue.get(i).getQuestion());
            text1.setText(issue.get(i).getAnswer());
            llIss.addView(view);
        }
    }

    private void initProduct(List<CarBean.DataBeanX.ProductListBean> productList) {
        //获取要传的id
        for (int i = 0; i < productList.size(); i++) {
            int goods_id = productList.get(i).getGoods_id();
            presenter.getrelated(goods_id);
        }
    }
    @Override
    public void getResult(RelateBean relateBean) {
        List<RelateBean.DataBean.GoodsListBean> goodsList = relateBean.getData().getGoodsList();
        rlvProduct.setLayoutManager(new GridLayoutManager(this,2));
        Car_Product_Adapter car_product_adapter = new Car_Product_Adapter(this, goodsList);
        rlvProduct.setAdapter(car_product_adapter);
        car_product_adapter.notifyDataSetChanged();
    }
}
