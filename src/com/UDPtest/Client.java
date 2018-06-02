package com.UDPtest;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 客户端
 * Created By Cx On 2018/5/31 23:19
 */
public class Client {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] buf = "我是xxx，收到请回复！！！".getBytes();
            DatagramPacket packet = new DatagramPacket(buf,buf.length,InetAddress.getByName("localhost"),4234);
            socket.send(packet);
            byte[] buf2 = new byte[1024];
            DatagramPacket packet2 = new DatagramPacket(buf2,buf2.length);
            socket.receive(packet2);
            String s = new String(packet2.getData());
            System.out.println(s);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
