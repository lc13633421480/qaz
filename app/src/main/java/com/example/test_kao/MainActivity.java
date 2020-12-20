package com.example.test_kao;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.test_kao.ui.home.HomeFrag;
import com.example.test_kao.ui.me.MeFrag;
import com.example.test_kao.ui.shop.ShopFrag;
import com.example.test_kao.ui.sort.SortFrag;
import com.example.test_kao.ui.topic.TopicFrag;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private RelativeLayout frag;
    private TabLayout tab;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {
        frag = (RelativeLayout) findViewById(R.id.frag);
        tab = (TabLayout) findViewById(R.id.tab);
    }

    private void initData() {
        HomeFrag homeFrag = new HomeFrag();
        TopicFrag zhuanFrag = new TopicFrag();
        ShopFrag shopFrag = new ShopFrag();
        SortFrag sortFrag = new SortFrag();
        MeFrag meFrag = new MeFrag();
        manager = getSupportFragmentManager();
        FragmentTransaction t = manager.beginTransaction();
        t.add(R.id.frag,homeFrag).add(R.id.frag,zhuanFrag).add(R.id.frag,shopFrag).add(R.id.frag,sortFrag)
                .add(R.id.frag,meFrag).hide(zhuanFrag).hide(shopFrag).hide(sortFrag).hide(meFrag).commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction t = manager.beginTransaction();
                if(tab.getPosition() == 0){
                    t.show(homeFrag).hide(zhuanFrag).hide(shopFrag).hide(sortFrag).hide(meFrag);
                }else if(tab.getPosition() == 1){
                    t.show(zhuanFrag).hide(homeFrag).hide(shopFrag).hide(sortFrag).hide(meFrag);
                }else if(tab.getPosition() == 2){
                    t.show(sortFrag).hide(homeFrag).hide(shopFrag).hide(zhuanFrag).hide(meFrag);
                }else if(tab.getPosition() == 3){
                    t.show(shopFrag).hide(homeFrag).hide(zhuanFrag).hide(sortFrag).hide(meFrag);
                }else{
                    t.show(meFrag).hide(homeFrag).hide(shopFrag).hide(zhuanFrag).hide(sortFrag);
                }
                t.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.home_sel));
        tab.addTab(tab.newTab().setText("专题").setIcon(R.drawable.topic_sel));
        tab.addTab(tab.newTab().setText("分类").setIcon(R.drawable.topic_sel));
        tab.addTab(tab.newTab().setText("购物车").setIcon(R.drawable.topic_sel));
        tab.addTab(tab.newTab().setText("我的").setIcon(R.drawable.topic_sel));
    }
}