package com.example.test_kao.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.test_kao.bean.TypeBean;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter extends FragmentStatePagerAdapter {
    private List<TypeBean.DataBean.BrotherCategoryBean> lists;
    private ArrayList<Fragment> fragments;

    public VpAdapter(@NonNull FragmentManager fm,
                     List<TypeBean.DataBean.BrotherCategoryBean> lists, ArrayList<Fragment> fragments) {
        super(fm);
        this.lists = lists;
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lists.get(position).getName();
    }
}
