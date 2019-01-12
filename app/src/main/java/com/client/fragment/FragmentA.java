package com.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.client.R;
import com.client.common.Constants;
import com.client.common.TcpClient;
import com.client.event.ActivityEvent;
import com.client.event.FragmentEvent;
import com.client.listener.TcpListener;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: FragmentA
 * 创 建 人: 何庆
 * 创建日期: 2018/12/29 19:36
 * 修改备注：
 */

public class FragmentA extends BaseFragment {
    private TcpClient client = new TcpClient();
    private TcpListener listener = new TcpListener() {
        @Override
        public void onConnected(String ip) {
            EventBus.getDefault().post(
                    new FragmentEvent.Builder()
                            .type(0)
                            .text("connected "+ip)
                            .build()
            );
        }

        @Override
        public void onMessage(String msg) {
            EventBus.getDefault().post(
                    new FragmentEvent.Builder()
                            .type(0)
                            .text("服务端消息:" + msg)
                            .build()
            );
        }

        @Override
        public void onDisConnected() {
            EventBus.getDefault().post(
                    new FragmentEvent.Builder()
                            .type(0)
                            .text("disconnected")
                            .build()
            );
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
        client.addListeners(listener);

    }

    public static FragmentA newInstance() {
        Bundle args = new Bundle();
        FragmentA fragment = new FragmentA();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int initViewId() {
        return R.layout.fragment_home;
    }


    @OnClick({R.id.button, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5/*, R.id.button6, R.id.button7*/})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                client.sendMsg(Constants.MODE_1);
                break;
            case R.id.button1:
                client.sendMsg(Constants.MODE_2);
                break;
            case R.id.button2:
                client.sendMsg(Constants.MODE_3);
                break;
            case R.id.button3:
                client.sendMsg(Constants.MODE_4);
                break;
            case R.id.button4:
                client.sendMsg(Constants.MODE_5);
                break;
            case R.id.button5:
                client.sendMsg(Constants.MODE_6);
                break;
            /*case R.id.button6:
                client.sendMsg(Constants.MODE_7);
                break;
            case R.id.button7:
                client.sendMsg(Constants.MODE_8);
                break;*/
        }
    }

    @Subscribe
    public void onEvent(ActivityEvent event) {
        System.out.println("fragment"+event.getText());
        if (event.getType() == 0) {
            if (!client.isbConnected()) {
                client.connect(event.getText());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
