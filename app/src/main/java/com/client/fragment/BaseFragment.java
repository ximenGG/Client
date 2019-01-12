package com.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.client.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 文 件 名: BaseFragment
 * 创 建 人: 何庆
 * 创建日期: 2018/12/29 19:31
 * 修改备注：
 */

public abstract class BaseFragment extends Fragment {
    private View view;
    Context context;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(container.getContext(), initViewId(), null);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public abstract @LayoutRes
    int initViewId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
