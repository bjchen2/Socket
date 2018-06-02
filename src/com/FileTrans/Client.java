package com.FileTrans;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 * 因能力有限，不会服务器部署，所以只能做到，本地上传下载，
 * 输入绝对路径上传文件
 * 服务器自动将文件保存到程序同级目录下的resource目录，下载自动保存到download目录
 *
 * 配置服务器后，把Socket改为服务器域名和服务器端口，url改为服务器存放数据目录即可。
 *
 * 注意：一定要先开启服务端再开启客户端
 * Created By Cx On 2018/6/1 18:49
 */
public class Client {

    static Scanner in = new Scanner(System.in);

    private static TransCmd menu(Socket socket){
        //开始页面
        int ans;
        TransCmd cmd = new TransCmd();
        System.out.print("菜单：\n    1.上传文件(输入绝对路径，如：D:\\words\\1.txt)\n    2.下载文件（仅输入文件名，如1.txt）\n    3.获取服务器文件列表\n请输入您要执行的操作：");
        try{
            ans = in.nextInt();
            in.nextLine();
        }catch (Exception e){
            System.out.println("【错误】:输入有误，请重新输入！！！");
            return menu(socket);
        }
        if (ans > 3 || ans < 1){
            System.out.println("【错误】:输入有误，请重新输入！！！");
            return menu(socket);
        }
        cmd.setCmd(ans);
        if (ans == 2){
            System.out.println("请输入您要获取的文件名称：");
            cmd.setFileName(in.nextLine());
        }
        else if (ans == 1){
            cmd = upload(socket);
            while (cmd == null){
                //如果文件不存在，重新执行选择界面
                System.out.println("文件路径有误，请确认输入的是绝对路径！！！");
                return menu(socket);
            }
        }
        return cmd;
    }

    private static TransCmd upload(Socket socket){
        //上传操作
        System.out.println("请输入您要上传的文件（注意：需要绝对路径）：");
        String url = in.nextLine();
        File file = new File(url);
        TransCmd cmd = null;
        if (!file.exists()) return null;
        else {
            //如果文件存在
            try {
                cmd = new TransCmd();
                cmd.setFileName(url.substring(url.lastIndexOf("\\")+1));
                cmd.setFile(file);
                cmd.setCmd(1);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
            return cmd;
        }
    }

    private static void getReply(Socket socket){
        TransCmd cmd = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            cmd = (TransCmd) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务器连接失败！！！");
        }
        if (cmd == null){
            System.out.println("服务器连接失败");
            return;
        }
        else{
            //成功获取回复
            if (cmd.getCmd() == 2){
                //开始下载文件
                String url = System.getProperty("user.dir")+"\\download\\"+cmd.getFileName();
                Utils.downLoad(cmd.getFile(),url);
            }
            else if (cmd.getCmd() == -1){
                System.out.println("该文件不存在！！！");
            }
            else{
                //非下载文件指令，遍历返回结果并输出
                String[] reply = cmd.getReplyList();
                for (String s : reply){
                    System.out.println(s);
                }
            }
        }
    }

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost",6533);
            //进入菜单，并获取需要传输的对象
            TransCmd ans = menu(socket);
            //开始向服务器传送指令
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(ans);
            //关闭输出流，准备开启输入流，接收服务器响应信息
            socket.shutdownOutput();
            //获取服务器信息
            getReply(socket);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("失败了，好像发生了一些未知问题。。。。");
        } finally {
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
