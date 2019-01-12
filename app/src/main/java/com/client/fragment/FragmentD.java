package com.client.fragment;

import android.os.Bundle;
import android.view.View;

import com.client.R;

/**
 * 文 件 名: FragmentD
 * 创 建 人: 何庆
 * 创建日期: 2018/12/29 19:38
 * 修改备注：
 */

public class FragmentD extends BaseFragment {

    public static FragmentD newInstance() {
        Bundle args = new Bundle();
        FragmentD fragment = new FragmentD();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initViewId() {
        return R.layout.fragment_home;
    }
}
