package com.wuhulala.dubbo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 功能
 *
 * @author xueah20964 2018/11/18 Create 1.0  <br>
 * @version 1.0
 */
public class SocketConnectDubbo {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(20880));
            socket.setKeepAlive(true);
            //socket.set
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //DubboCodec.
            //out.writeUTF("");

        } catch (IOException e) {

        }finally {
            socket.close();
        }
    }
}
