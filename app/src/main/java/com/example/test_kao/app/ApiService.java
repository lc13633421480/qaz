package com.example.test_kao.app;

import com.example.test_kao.bean.BrandBean;
import com.example.test_kao.bean.BrandIdBean;
import com.example.test_kao.bean.Brand_Tv_infoBean;
import com.example.test_kao.bean.CarBean;
import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.bean.HotGoodsBean;
import com.example.test_kao.bean.HotTopBean;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.bean.RelateBean;
import com.example.test_kao.bean.TypeBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    String BASE_url = "https://cdplay.cn/";

    //首页
    @GET("api/index")
    Flowable<HomeBean>getHome();

    //分类?id=1005000
    @GET("goods/category")
    Flowable<TypeBean>getType(@Query("id") int id);

    //5种选择的详情
    @GET()
    Flowable<InfoBean>getCate(@Url String url);

   // https://cdplay.cn/api/brand/list?page=1&size=1000
    //品牌制造商详情
    @GET("api/brand/list/{p}")
    Flowable<BrandBean>getBrand(@Path("p") int p, @Query("size") int size);

    //https://cdplay.cn/api/brand/detail?id=1001000
    //对应的品牌制造商
    @GET("api/brand/detail")
    Flowable<Brand_Tv_infoBean>getBrandtvinfo(@Query("id") int id);

    //品牌制造商的商品列表
    //https://cdplay.cn/api/goods/list?brandId=1001007&page=1&size=1000
    @GET("api/goods/list")
    Flowable<BrandIdBean>getBrandId(@Query("brandId") int id);

    //新品顶部
    //https://cdplay.cn/api/goods/hot
    @GET("api/goods/hot")
    Flowable<HotTopBean> getHotTop();


    //https://cdplay.cn/api/goods/list?isNew=1&page=1&size =1000&order=asc&sort=default&categoryId=0
    //新品页面的商品列表
    @GET("api/goods/list")
    Flowable<HotGoodsBean>getHotGoods(@QueryMap HashMap<String,String> map);

    //https://cdplay.cn/api/goods/detail?id=1009024
    //商品购买详情页
    @GET("api/goods/detail")
    Flowable<CarBean>getCar(@Query("id") int id);

    //https://cdplay.cn/api/goods/related?id=1155000
    //底部商品列表
    @GET("api/goods/related")
    Flowable<RelateBean>getrelated(@Query("id") int id);
}
