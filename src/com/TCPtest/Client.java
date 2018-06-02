package com.TCPtest;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 * Created By Cx On 2018/5/30 9:12
 */
public class Client {
    public static void main(String[] args) {
        OutputStream out = null;
        InputStream in = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        Socket socket = null;
        try {
            //创建请求socket
            socket = new Socket("localhost", 6543);
            //获取通信所需输出字节流
            out = socket.getOutputStream();
            //转换为缓冲字符流
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write("hello world\nI'm fine Thanks");
            writer.flush();
            socket.shutdownOutput();
            in = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            String ans = null;
            System.out.println("服务器返回值：");
            while ((ans = reader.readLine())!=null){
                System.out.println(ans);
            }
            socket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源,reader/writer等不用关闭，因为关闭后响应的socket也会关闭，所以一般直接关闭socket就行
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
