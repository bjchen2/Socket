package com.FileTrans;

import java.io.*;
import java.net.Socket;

/**
 * Created By Cx On 2018/6/1 18:52
 */
public class SeverThread extends Thread {

    private Socket socket;

    public SeverThread(Socket socket) {
        this.socket = socket;
    }


    private TransCmd result(TransCmd cmd){
        int ans = cmd.getCmd();
        String url = System.getProperty("user.dir")+"\\resource\\";
        String[] reply = new String[]{};
        if (ans == 1){
            //如果是上传指令，保存到resource中
            url += cmd.getFileName();
            Utils.downLoad(cmd.getFile(),url);
            reply = new String[]{"上传成功！"};
        }
        else if (ans == 2){
            //如果是下载指令
            url += cmd.getFileName();
            File file = new File(url);
            if (file.exists()) cmd.setFile(file);
            else cmd.setCmd(-1);
        }
        else if (ans == 3){
            //如果是查询列表指令
            File file = new File(url);
            reply = file.list();
        }
        cmd.setReplyList(reply);
        return cmd;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos;
            try {
                TransCmd cmd = (TransCmd) ois.readObject();
                cmd = result(cmd);
                socket.shutdownInput();
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(cmd);
                oos.flush();
                socket.shutdownOutput();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("数据操作失败！！！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
