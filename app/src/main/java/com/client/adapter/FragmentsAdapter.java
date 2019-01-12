package com.client.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 文 件 名: MainAdapter
 * 创 建 人: 何庆
 * 创建日期: 2018/12/29 19:44
 * 修改备注：
 */

public class FragmentsAdapter extends FragmentPagerAdapter {
    List<Fragment> lists;

    public FragmentsAdapter(FragmentManager fm, List<Fragment> lists) {
        super(fm);
        this.lists = lists;
    }

    @Override
    public Fragment getItem(int i) {
        return lists.get(i);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
