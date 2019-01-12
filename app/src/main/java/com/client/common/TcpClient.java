package com.client.common;

import com.client.common.Constants;
import com.client.listener.TcpListener;
import com.client.utils.AppExecutors;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

/**
 * 文 件 名: TcpClient
 * 创 建 人: 何庆
 * 创建日期: 2018/12/31 01:54
 * 修改备注：
 */

public class TcpClient {
    Socket s = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;
    private boolean bConnected = false;
    private List<TcpListener> listeners = new LinkedList<>();

    public void connect(String host) {
        try {
            s = new Socket(host, Constants.TCP_PORT);
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
            for (int i = 0; i < listeners.size(); i++) {
                if (listeners.get(i) != null) {
                    listeners.get(i).onConnected(host);
                }
            }
            bConnected = true;
            AppExecutors.getInstance().networkIO().execute(new TcpThread());
        } catch (UnknownHostException e) {
            bConnected = false;
            e.printStackTrace();
        } catch (IOException e) {
            bConnected = false;
            e.printStackTrace();
        }

    }

    public TcpClient() {
    }

    public void disconnect() {
        bConnected = false;
        try {
            dos.close();
            dis.close();
            s.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        for (int i = 0; i < listeners.size(); i++) {
            if (listeners.get(i) != null) {
                listeners.get(i).onDisConnected();
            }
        }
    }

    public void sendMsg(final String str) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (dos != null) {
                        dos.writeUTF(str);
                        dos.flush();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    bConnected = false;
                }
            }
        });

    }

    private class TcpThread implements Runnable {
        public void run() {
            try {
                while (bConnected) {
                    String msg = dis.readUTF();
                    for (int i = 0; i < listeners.size(); i++) {
                        if (listeners.get(i) != null) {
                            listeners.get(i).onMessage(msg);
                        }
                    }
                }
            } catch (SocketException e) {
                bConnected = false;
                for (int i = 0; i < listeners.size(); i++) {
                    if (listeners.get(i) != null) {
                        listeners.get(i).onDisConnected();
                    }
                }
            } catch (EOFException e) {
                bConnected = false;
                for (int i = 0; i < listeners.size(); i++) {
                    if (listeners.get(i) != null) {
                        listeners.get(i).onDisConnected();
                    }
                }
            } catch (IOException e) {
                bConnected = false;
                e.printStackTrace();
            }
        }
    }

    public boolean isbConnected() {
        return bConnected;
    }

    public void addListeners(TcpListener listener) {
        this.listeners.add(listener);
    }

    public void removeListeners(TcpListener listener) {
        this.listeners.remove(listener);
    }
}
