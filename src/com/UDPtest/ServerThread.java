package com.UDPtest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 服务器线程处理类
 * 使用多线程，否则访问量大或者某个连接出错时会阻塞
 * Created By Cx On 2018/5/31 23:19
 */
public class ServerThread extends Thread{

    private DatagramPacket packet;

    public ServerThread(DatagramPacket packet) {
        this.packet = packet;
    }

    @Override
    public void run() {
        byte[] buf2 = "老子收到数据了，你滚吧！！！".getBytes();
        DatagramPacket packet2 = new DatagramPacket(buf2,buf2.length,packet.getAddress(),packet.getPort());
        try {
            System.out.println("客户端："+new String(packet.getData()));
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet2);
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
