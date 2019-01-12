package com.client.fragment;

import android.os.Bundle;
import android.view.View;

import com.client.R;

/**
 * 文 件 名: FragmentC
 * 创 建 人: 何庆
 * 创建日期: 2018/12/29 19:37
 * 修改备注：
 */

public class FragmentC extends BaseFragment {
    public static FragmentC newInstance() {
        Bundle args = new Bundle();
        FragmentC fragment = new FragmentC();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int initViewId() {
        return R.layout.fragment_home;
    }
}
