package com.TCPtest;

import java.io.*;
import java.net.Socket;

/**
 * 服务器线程处理类
 * 使用多线程，否则访问量大或者某个连接出错时会阻塞
 * Created By Cx On 2018/5/30 12:44
 */
public class ServerThread extends Thread {

    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            //获取通信所需的字节输入流
            in = socket.getInputStream();
            //将字节流转换成字符流再转换为缓冲字符流
            reader = new BufferedReader(new InputStreamReader(in));
            String ans = reader.readLine();
            System.out.println("客户端输入结果为：");
            while (ans != null) {
                System.out.println(ans);
                ans = reader.readLine();
            }
            //关闭客户端输入,必须写在reader关闭之前,且一次只能一个流开启，所以在获取输出流之前必须先关闭输入流
            socket.shutdownInput();
            out = socket.getOutputStream();
            //自动刷新缓冲（不需要flush0
            writer = new PrintWriter(out, true);
            writer.println("I have got your info thanks");
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
