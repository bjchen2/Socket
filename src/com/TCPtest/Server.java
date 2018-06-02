package com.TCPtest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信，实现用户登录
 * 服务端
 * Created By Cx On 2018/5/30 9:01
 */
public class Server {
    public static void main(String[] args) {
        try {
            //创建服务端,绑定并监听8888端口
            ServerSocket serverSocket = new ServerSocket(6543);
            //开始监听，一旦有客户端请求，立即响应，否则阻塞
            System.out.println("=======服务端已开启=======");
            while (true){
                Socket socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
//            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
