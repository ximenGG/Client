package com.client.listener;

/**
 * 文 件 名: TcpListener
 * 创 建 人: 何庆
 * 创建日期: 2018/12/31 01:59
 * 修改备注：
 */

public interface TcpListener {
    void onConnected(String ip);

    void onMessage(String msg);

    void onDisConnected();
}
