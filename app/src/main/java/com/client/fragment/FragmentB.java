package com.client.fragment;

import android.os.Bundle;
import android.view.View;

import com.client.R;

/**
 * 文 件 名: FragmentB
 * 创 建 人: 何庆
 * 创建日期: 2018/12/29 19:37
 * 修改备注：
 */

public class FragmentB extends BaseFragment {

    public static FragmentB newInstance() {
        Bundle args = new Bundle();
        FragmentB fragment = new FragmentB();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public int initViewId() {
        return R.layout.fragment_home;
    }
}
