package com.UDPtest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 基于UDP协议的Socket通信，实现用户登录
 * 服务端
 * Created By Cx On 2018/5/31 23:19
 */
public class Server {

    public static void main(String[] args) {
        try {
            while(true){
                //也可以不设置InetAddress，这样默认为localhost。
                DatagramSocket socket = new DatagramSocket(4234, InetAddress.getByName("localhost"));
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf,buf.length);
                socket.receive(packet);
                ServerThread thread = new ServerThread(packet);
                thread.start();
                socket.close();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
