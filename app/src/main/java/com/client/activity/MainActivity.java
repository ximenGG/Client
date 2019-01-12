package com.client.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.client.R;
import com.client.common.BroadCastHelper;
import com.client.event.ActivityEvent;
import com.client.event.FragmentEvent;
import com.client.fragment.FragmentA;
import com.client.listener.OnBroadCastListener;
import com.client.utils.AppExecutors;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends BasicActivity {

    private Toolbar toolbar;
    private TextView textView;
    private TabLayout tabLayout;
    private BottomNavigationViewEx navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        BroadCastHelper.broadcast(new OnBroadCastListener() {
            @Override
            public void onBroadCast(String ip) {
                EventBus.getDefault().post(
                        new ActivityEvent.Builder()
                                .type(0)
                                .text(ip)
                                .build()
                );
            }
        });
        toolbar = findView(R.id.toolbar);
        textView = findView(R.id.tv_debug);
        tabLayout = findView(R.id.tablayout);
        navigation = findView(R.id.navigation);
        initToolBar();
        initTablayout();
        initNavigation();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, FragmentA.newInstance()).commit();

    }

    private void initTablayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.device));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.scenes));
    }

    public void initNavigation() {
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
        navigation.setTextVisibility(true);
    }


    private void initToolBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FragmentEvent event) {
        if (event.getType() == 0) {
            debug(event.getText());
        }
    }

    public void debug(final String msg) {
        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                if (textView != null) {
                    textView.setText(msg);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
