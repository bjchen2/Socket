package com.FileTrans;

import java.io.File;
import java.io.Serializable;

/**
 * Created By Cx On 2018/6/2 12:42
 */
public class TransCmd implements Serializable {

    private File file;
    private String fileName;
    private Integer cmd;
    //存放服务器回复结果
    private String[] replyList;

    public TransCmd() {
    }

    public TransCmd(File file, String fileName, Integer cmd) {
        this.file = file;
        this.fileName = fileName;
        this.cmd = cmd;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getCmd() {
        return cmd;
    }

    public void setCmd(Integer cmd) {
        this.cmd = cmd;
    }

    public String[] getReplyList() {
        return replyList;
    }

    public void setReplyList(String[] replyList) {
        this.replyList = replyList;
    }
}
