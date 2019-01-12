package com.client.common;

import com.client.listener.OnBroadCastListener;
import com.client.utils.AppExecutors;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 文 件 名: BroadCastHelper
 * 创 建 人: 何庆
 * 创建日期: 2018/12/30 14:57
 * 修改备注：
 */

public class BroadCastHelper {
    public static void broadcast(final OnBroadCastListener listener) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        DatagramSocket dgSocket = null;
                        if (dgSocket == null) {
                            dgSocket = new DatagramSocket(null);
                            dgSocket.setReuseAddress(true);
                            dgSocket.bind(new InetSocketAddress(Constants.UDP_PORT));
                        }
                        byte[] by = new byte[64];
                        DatagramPacket packet = new DatagramPacket(by, by.length);
                        dgSocket.receive(packet);
                        String str = new String(packet.getData(), 0, packet.getLength());
                        if (listener != null) {
                            listener.onBroadCast(str);
                        }
                    } catch (Exception e) {
                        listener.onBroadCast("");
                    }
                }
            }
        });
    }

}
