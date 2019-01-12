package com.client.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import com.zhy.autolayout.AutoLayoutActivity;


public abstract class BasicActivity extends AutoLayoutActivity {
    /**
     * 使状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(Color.parseColor("#FF4F5C7A"));
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transparentStatusBar();
    }

    /**
     * 通过类名启动并且传递参数
     *
     * @param className
     */
    protected void startActivity(String className, Bundle bundle) {
        try {
            startActivity(Class.forName(className), bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过类名启动
     *
     * @param className
     */
    protected void startActivity(String className) {
        try {
            startActivity(Class.forName(className), null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 通过字节码对象并传递参数启动
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    // // 通过字节码对象启动
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    // startActivityForResult
    protected void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    // startActivityForResult
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    // startActivityForResult
    protected void startActivityForResult(String className, int requestCode) {
        try {
            startActivityForResult(Class.forName(className), null, requestCode);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // startActivityForResult
    protected void startActivityForResult(String className, Bundle bundle, int requestCode) {
        try {
            startActivityForResult(Class.forName(className), bundle, requestCode);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // getIntent
    protected Bundle getIntentExtra() {
        Intent intent = getIntent();
        Bundle bundle = null;
        if (null != intent) bundle = intent.getExtras();
        return bundle;
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }



}
