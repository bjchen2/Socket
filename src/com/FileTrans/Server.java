package com.FileTrans;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created By Cx On 2018/6/1 18:51
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(6533);
            System.out.println("===========服务器已开启==========");
            while (true) {
                Socket socket = server.accept();
                SeverThread thread = new SeverThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
